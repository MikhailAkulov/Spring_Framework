package ru.gb.myspringdemo.aspect;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@AllArgsConstructor
public class TestController {

    TestService testService;

    @EventListener(ApplicationReadyEvent.class)
    public void getData() {
        try {
            testService.testMethod();
        } catch (Exception e) {
            log.error(e.getClass().getName() + " " + e.getMessage());
        }
        try {
            testService.catchIllegalArgumentException();
        } catch (Throwable e) {
            log.error(e.getClass().getName() + " " + e.getMessage());
        }
        try {
            testService.catchNullPointerException();
        } catch (Throwable e) {
            log.error(e.getClass().getName() + " " + e.getMessage());
        }
    }
}
