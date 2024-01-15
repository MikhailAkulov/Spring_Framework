package ru.gb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.RestController;

/**
 * класс описывающий конфигурацию бинов
 */
//@Configuration
public class ApplicationConfiguration {

    @Bean
//    @Primary
    StudentRepository myStudentRepository() {
        return new StudentRepository();
    }

}
