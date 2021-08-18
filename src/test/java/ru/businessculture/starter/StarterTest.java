package ru.businessculture.starter;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.businessculture.storage.Storage;
import ru.businessculture.thread.Consumer;
import ru.businessculture.thread.Producer;

import static org.junit.jupiter.api.Assertions.*;

class StarterTest {

    private static Starter starter;
    private static Producer producer;
    private static Consumer consumer;
    private static Storage storage;

    @BeforeAll
    static void setUp() {
        producer = new Producer(new TestStorage());
        consumer = new Consumer(new TestStorage());
        starter = new Starter(producer, consumer);
    }

    @Test
    void startThreads() {
        assertDoesNotThrow(() -> starter.startThreads());
    }

    private static class TestStorage implements Storage {

        @Override
        public void put(int value) {

        }

        @Override
        public void get() {

        }
    }
}
