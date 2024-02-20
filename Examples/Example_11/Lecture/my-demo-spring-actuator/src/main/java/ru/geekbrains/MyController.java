package ru.geekbrains;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    private final MeterRegistry meterRegistry;

    public MyController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;

    }
    @GetMapping("/hello")
    public String sayHello() {
        meterRegistry.counter("requests_to_hello").increment();
        // ваша логика
        return "Hello, World!";
    }
}
