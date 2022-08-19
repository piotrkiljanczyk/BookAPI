package pl.coderslab;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.coderslab.utils.DbUtil;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "pl.coderslab")

public class AppConfig implements WebMvcConfigurer {

    @Bean
    public DbUtil dbUtil(){
        DbUtil db = new DbUtil();
        db.setDB_USER("root");
        db.setDB_PASS("iPiotr87#");
        db.setDB_SERVER_URL("jdbc:mysql://localhost:3306/BookAPI");
        db.setDB_PARAMS("?useSSL=false&serverTimezone=UTC&characterEncoding=utf8&allowPublicKeyRetrieval=true");
        return db;
    }
}
