package ru.avalon.javapp.devj130;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Database d = new Database();
        AbstarctEntity[] entities = new AbstarctEntity[] {
                new Writer(d, "w1"),
                new Writer(d, "w2"),
                new Writer(d, "w3"),
                new Reader(d, "r1"),
                new Reader(d, "r2"),
                new Reader(d, "r3"),
                new Reader(d, "r4"),
                new Reader(d, "r5")
        };
        for (AbstarctEntity e : entities) {
            e.start();
            entities[0].join();
        }
    }
}
