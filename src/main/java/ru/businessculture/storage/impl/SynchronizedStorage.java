package ru.businessculture.storage.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.businessculture.storage.Storage;

import java.util.ArrayList;
import java.util.List;

@Component
@PropertySource("classpath:application.properties")
public class SynchronizedStorage implements Storage {

    private static final Logger logger = LoggerFactory.getLogger(SynchronizedStorage.class);

    private final List<Integer> randoms = new ArrayList<>();

    @Value("${value.sleep.time}")
    private int sleepTime;

    @Override
    public synchronized void put(int value) {
        while (randoms.size() == 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        randoms.add(value);

        logger.info("Data wrote, value: {}", value);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notify();
    }

    @Override
    public synchronized void get() {
        while (randoms.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        logger.info("Data read, value: {}", randoms.remove(0));
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        notify();
    }
}
