package ${projectData.basePackage};

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ${projectData.basePackage}.utils.IdWorker;

/**
 * 微服务启动类（引导类）
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    //初始化工作
    @Bean
    public IdWorker idWorker(){
        return new IdWorker();
    }


}
