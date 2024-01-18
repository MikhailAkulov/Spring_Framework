package ru.gb.myspringdemo.demo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Slf4j
@Component
public class DatabaseConnector {

    private final ApplicationEventPublisher eventPublisher;

    @Autowired
    public DatabaseConnector(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public String getData() {
        // connect to DB and select
        return "data";
    }

    // init-method
    @SneakyThrows // лучше не применять
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        log.info("подключаемся к БД...");
        Thread.sleep(Duration.ofSeconds(1));
        log.info("подключение к БД успешно");

        eventPublisher.publishEvent(new DatabaseConnectionSetupEvent(this));
    }
}
