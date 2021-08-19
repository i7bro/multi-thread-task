# multi-thread-task

To start the application, need to invoke main method in MultiThreadTaskApplication class.
In appliation.properties is values value.times = repeat mites, value.range = reange of random, value.sleep.time

Expected output of application:

23:58:43.974 [Thread-1] INFO ru.businessculture.storage.impl.SynchronizedStorage - Data wrote, value: 1

23:58:44.477 [Thread-2] INFO ru.businessculture.storage.impl.SynchronizedStorage - Data read, value: 1

23:58:44.979 [Thread-1] INFO ru.businessculture.storage.impl.SynchronizedStorage - Data wrote, value: 2

23:58:45.480 [Thread-2] INFO ru.businessculture.storage.impl.SynchronizedStorage - Data read, value: 2

23:58:45.981 [Thread-1] INFO ru.businessculture.storage.impl.SynchronizedStorage - Data wrote, value: 0

23:58:46.482 [Thread-2] INFO ru.businessculture.storage.impl.SynchronizedStorage - Data read, value: 0

23:58:46.983 [Thread-1] INFO ru.businessculture.storage.impl.SynchronizedStorage - Data wrote, value: 0

23:58:47.483 [Thread-2] INFO ru.businessculture.storage.impl.SynchronizedStorage - Data read, value: 0

23:58:47.986 [Thread-1] INFO ru.businessculture.storage.impl.SynchronizedStorage - Data wrote, value: 6

...
