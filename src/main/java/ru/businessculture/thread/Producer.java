package ru.businessculture.thread;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.businessculture.storage.Storage;

import java.util.Random;

@Component
@PropertySource("classpath:application.properties")
public class Producer implements Runnable {

    private final Storage storage;

    @Value(value = "${value.times}")
    private int times;

    @Value(value = "${value.range}")
    private int range;

    @Autowired
    public Producer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        var random = new Random();

        while (times > 0) {
            storage.put(random.nextInt(range));
            times--;
        }
    }
}
