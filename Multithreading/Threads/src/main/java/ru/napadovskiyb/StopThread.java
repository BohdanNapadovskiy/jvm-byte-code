package ru.napadovskiyb;

/**
 * Package of CollectionPro finalTask.
 * Main class for order book.
 * @author Napadovskiy Bohdan
 * @version 1.0
 * @since 09.08.2017
 */
public class StopThread {

    /**
     * Main mathod.
     * @param args argument.
     * @throws InterruptedException exception.
     */
    public static void main(String[] args) throws InterruptedException {

        String checkString = "Недостатки такого подхода. Во-первых, потоки в состоянии ожидания таким способом не расшевелить. И это серьезный минус по сравнению с первым методом. А во-вторых, выставление флага одним потоком совсем не означает, что второй поток тут же его увидит. Для увеличения производительности виртуальная машина использует кеш данных потока, в результате чего обновление переменной у второго потока может произойти через неопределенный промежуток времени. Можно, конечно, объявить эту переменную volatile, но это поможет только в случае примитивных типов данных. Подробнее этого вопроса мы коснемся ниже.";

        final long timeLimit = 1000;

        CountChar countChar = new CountChar(checkString);
        Thread countCharThread = new Thread(countChar);
        Time time = new Time(timeLimit, countCharThread);
        Thread threadTime = new Thread(time);
        threadTime.start();
    }
 }
