package com.zzw.metadata;

import com.zzw.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MySQLMetaData implements Metadata {


    private String driverClass = "com.mysql.jdbc.Driver";
    private String jdbcUrlPrefix = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306";
    private String user = "root";
    private String host = "127.0.0.1";
    private String port = "3306";
    private String password = "root";
    private LinkedHashMap<String, ArrayList<ColumnPoJo>> columns;
    private static String dbSql = "SELECT TABLE_SCHEMA FROM `TABLES` GROUP BY TABLE_SCHEMA";
    private static final String QUERY_SQL = "SELECT table_name FROM information_schema.TABLES WHERE table_schema = ?";


    public MySQLMetaData(String user, String host, String port, String password) {
        this.user = user;
        this.host = host;
        this.port = port;
        this.password = password;
        this.jdbcUrlPrefix = "jdbc:mysql://" + this.host + ":" + this.port;
        this.driverClass = "com.mysql.jdbc.Driver";
    }


    /**
     * 获取数据库的连接对象
     */
    private Connection getConnection(String dbName) {
        try {
            Class.forName(this.driverClass);
            return DriverManager.getConnection(this.jdbcUrlPrefix
                    + "/" + dbName, this.user, this.password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取MySQL中所有的用户自己创建的数据库名称
     */
    public List<String> getAllUserDataBaseNames() {
        return deleteOriginDB(getAllDataBaseNames());
    }


    /**
     * 获取MySQL中所有的数据库的名称
     */
    private List<String> getAllDataBaseNames() {
        try {
            Connection connection = getConnection("information_schema");
            PreparedStatement pstmt = connection.prepareStatement(dbSql);
            ResultSet rs = pstmt.executeQuery();
            // 保存所有的数据库名称
            List<String> dbList = new ArrayList<>();
            while (rs.next()) {
                dbList.add(rs.getString(1));
            }
            // 关闭数据连接对象
            pstmt.close();
            connection.close();
            return dbList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 排除系统自带的数据库，返回用户自己创建的数据库
     *
     * @param listA 第一次查出来的数据库
     * @return 自己创建的数据库
     */
    private List<String> deleteOriginDB(List<String> listA) {
        ArrayList<Object> originDBs = new ArrayList<Object>();
        ArrayList<String> strings = new ArrayList<String>();
        originDBs.add("information_schema");
        originDBs.add("mysql");
        originDBs.add("performance_schema");
        originDBs.add("sys");
        for (String s : listA) {
            if (!originDBs.contains(s)) {
                strings.add(s);
            }
        }
        return strings;
    }

    /**
     * 根据数据库查找用户表
     *
     * @param dbName
     * @return
     */
    public List<String> findTablesName(String dbName) {
        List<String> tables = new ArrayList<String>();
        try {
            Connection conn = getConnection("information_schema");
            PreparedStatement pstmt = conn.prepareStatement(QUERY_SQL);
            pstmt.setString(1, dbName);
            ResultSet rs = pstmt.executeQuery();
            // 保存该数据库中所有表名
            while (rs.next()) {
                tables.add(rs.getString(1));
            }
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tables;
    }

    /**
     * 根据数据库的一张数据表查找列信息
     *
     * @param dbName
     * @param tableName
     * @return
     */
    public LinkedHashMap<String, ArrayList<ColumnPoJo>> findColumnsFromOne(String dbName, String tableName) {
        //使用columnPoJos接受结果
        ArrayList<ColumnPoJo> columnPoJos = new ArrayList<ColumnPoJo>();
        LinkedHashMap<String, ArrayList<ColumnPoJo>> map = new LinkedHashMap<>();
        try {
            Connection connection = getConnection(dbName);
            PreparedStatement pstmt = connection.prepareStatement("show full columns from " + dbName + "." + tableName);
            ResultSet resultSet = pstmt.executeQuery();

            //保存列信息
            while (resultSet.next()) {
                ColumnPoJo columnPoJo = new ColumnPoJo();
                columnPoJo.setSqlField(resultSet.getString("Field"));
                columnPoJo.setUpperClassField(StringUtils.transferUpper(resultSet.getString("Field")));
                columnPoJo.setLowerClassField(StringUtils.transferLower(resultSet.getString("Field")));
                columnPoJo.setSqlType(resultSet.getString("Type"));
                columnPoJo.setClassType(StringUtils.transferSqlType(resultSet.getString("Type")));
                columnPoJo.setCollation(resultSet.getString("Collation"));
                columnPoJo.setCanNull(resultSet.getString("Null").equals("YES") ? "true" : "false");
                columnPoJo.setIsKey(resultSet.getString("Key").equals("PRI") ? "true" : "false");
                columnPoJo.setDefaultValue(resultSet.getString("Default"));
                columnPoJo.setExtra(resultSet.getString("Extra"));
                columnPoJo.setPrivileges(resultSet.getString("Privileges"));
                columnPoJo.setComment(resultSet.getString("Comment"));
                columnPoJo.setIsFR("false");
                columnPoJos.add(columnPoJo);
            }
            // 查找是否有外键
            DatabaseMetaData metaData = connection.getMetaData();
            String catalog = connection.getCatalog();
            ResultSet foreignKeyResultSet = metaData.getImportedKeys(catalog, null, tableName);
            while (foreignKeyResultSet.next()) {
                String fkColumnName = foreignKeyResultSet.getString("FKCOLUMN_NAME");
                String pkTablenName = foreignKeyResultSet.getString("PKTABLE_NAME");
                String pkColumnName = foreignKeyResultSet.getString("PKCOLUMN_NAME");
                //保存外键信息
                for (ColumnPoJo columnPoJo : columnPoJos) {
                    if (columnPoJo.getSqlField().equals(fkColumnName)) {
                        columnPoJo.setIsFR("true");
                        columnPoJo.setFRTableName(pkTablenName);
                        columnPoJo.setFRColumnName(pkColumnName);
                    }
                }

            }
            pstmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        map.put(tableName, columnPoJos);
        return map;
    }

    /**
     * 根据数据库的多张数据表查找列信息
     *
     * @param dbName
     * @param tableNames
     * @return
     */
    public LinkedHashMap<String, ArrayList<ColumnPoJo>> findColumnsFromMany(String dbName, List<String> tableNames) {
        LinkedHashMap<String, ArrayList<ColumnPoJo>> map = new LinkedHashMap<>();
        for (String tableName : tableNames) {
            LinkedHashMap<String, ArrayList<ColumnPoJo>> columnsFromOne = findColumnsFromOne(dbName, tableName);
            map.putAll(columnsFromOne);
        }
        return map;
    }

}
