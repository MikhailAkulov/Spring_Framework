package ru.geekbrains;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    /**
     * Cоздание Counter
     */
//    private final Counter myCounter;
//
//    public MyService(MeterRegistry meterRegistry) {
//        myCounter = Counter.builder("my_custom_counter")
//                .description("Counts something very important")
//                .register(meterRegistry);
//    }
//
//    public void doSomethingImportant() {
//        // Тут какая-то важная логика
//        myCounter.increment();
//    }

    /**
     * Cоздание Timer
     */
    private final Timer myTimer;

    public MyService(MeterRegistry meterRegistry) {
        myTimer = Timer.builder("my_custom_timer")
                .description("Timing something very important")
                .register(meterRegistry);
    }

    public void doSomethingTimed() {
        myTimer.record(() -> {
        // Тут какая-то важная логика, время выполнения которой мы хотим замерить
        });
    }
}
