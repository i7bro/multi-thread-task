package ru.businessculture.starter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.businessculture.storage.Storage;
import ru.businessculture.storage.impl.SynchronizedStorage;
import ru.businessculture.thread.Consumer;
import ru.businessculture.thread.Producer;

@Component
@RequiredArgsConstructor
public class Starter {

    private final Producer producer;
    private final Consumer consumer;

    public void startThreads() {
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
