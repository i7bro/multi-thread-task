package ru.businessculture.storage.impl;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.businessculture.storage.Storage;

import java.lang.reflect.Field;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SynchronizedStorageTest {

    private static final int PUT_VALUE = 10;

    @Autowired
    private Storage storage;

    @Test
    @SuppressWarnings("unchecked")
    @SneakyThrows
    void put() {
        TestThreadPut testThreadPut = new TestThreadPut(storage);
        testThreadPut.start();

        testThreadPut.join();
        try {
            Field randoms = storage.getClass().getDeclaredField("randoms");
            randoms.setAccessible(true);
            List<Integer> o = (List<Integer>) randoms.get(storage);

            assertThat(o.size()).isEqualTo(1);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    @SneakyThrows
    void get() {
        TestThreadPut testThreadPut = new TestThreadPut(storage);
        TestThreadGet testThreadGet = new TestThreadGet(storage);
        testThreadPut.start();
        testThreadGet.start();

        testThreadPut.join();
        testThreadGet.join();
        try {
            Field randoms = storage.getClass().getDeclaredField("randoms");
            randoms.setAccessible(true);
            List<Integer> o = (List<Integer>) randoms.get(storage);

            assertThat(o.size()).isEqualTo(0);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static class TestThreadPut extends Thread {
        private final Storage storage;

        public TestThreadPut(Storage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            this.storage.put(PUT_VALUE);
        }
    }

    private static class TestThreadGet extends Thread {
        private final Storage storage;

        public TestThreadGet(Storage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            this.storage.get();
        }
    }
}
