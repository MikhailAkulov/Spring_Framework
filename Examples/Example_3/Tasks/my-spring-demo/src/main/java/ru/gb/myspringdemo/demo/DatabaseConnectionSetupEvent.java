package ru.gb.myspringdemo.demo;

import org.springframework.context.ApplicationEvent;

import java.time.Clock;

public class DatabaseConnectionSetupEvent extends ApplicationEvent {

    public DatabaseConnectionSetupEvent(Object source) {
        super(source);
    }
}
