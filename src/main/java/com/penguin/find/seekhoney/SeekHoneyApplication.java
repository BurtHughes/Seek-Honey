package com.penguin.find.seekhoney;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @Auther: Burt Hughes
 * @Date: 2018/6/7 22:38
 * @Description:
 */
@SpringBootApplication(exclude={
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
@ServletComponentScan
public class SeekHoneyApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(SeekHoneyApplication.class, args);
    }
}
