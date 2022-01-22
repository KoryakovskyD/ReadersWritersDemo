package ru.avalon.javapp.devj130;

import java.util.Random;

public class AbstarctEntity extends Thread {
    protected final Database database;
    protected final Random rnd = new Random();

    public AbstarctEntity(Database database, String name) {
        super(name);
        this.database = database;
    }

    protected void emulateWork(int minMs, int maxMs) {
        try {
            Thread.sleep((long) (minMs + rnd.nextFloat() * (maxMs-minMs)));
        } catch (InterruptedException e) {
        }
    }

    @Override
    public String toString() {
        return getName();
    }
}
