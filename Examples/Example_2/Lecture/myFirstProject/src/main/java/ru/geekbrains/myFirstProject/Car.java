package ru.geekbrains.myFirstProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class Car {
    @Autowired
    Engine engine;

    /**
     * Внедрение зависимостей
     */
    //  Зависимости нужно внедрять через конструктор!!! (Первый и основной способ)
//    public Car(Engine engine) {
//        this.engine = engine;
//        engine.go();
//    }

    //  Внедрение зависимости через setter (Второй способ)
//    public Car() {
//    }
//
//    public void setEngine(Engine engine) {
//        this.engine = engine;
//        engine.go();
//    }

    // Внедрение зависимости через поле, с помощью аннотации @Autowired (Третий способ для ленивых разработчиков)

    /**
     * для CarController
     */
    public void start() {
        engine.go();
    }
}
