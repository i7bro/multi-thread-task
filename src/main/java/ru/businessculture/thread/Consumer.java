package ru.businessculture.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.businessculture.storage.Storage;

@Component
@PropertySource("classpath:application.properties")
public class Consumer implements Runnable {

    private final Storage storage;

    @Value(value = "${value.times}")
    private int times;

    @Autowired
    public Consumer(Storage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        while (times > 0) {
            storage.get();
            times--;
        }
    }
}
