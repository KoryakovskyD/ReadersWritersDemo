package ru.avalon.javapp.devj130;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class Database {
    /*
    private int readersIn;
    private boolean writerIn;

    public synchronized void readerEnters(Reader r) {
            System.out.printf("%s wants to connect to the database.%n", r);
            while (writerIn) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
            readersIn++;

            System.out.printf("%s connected to the database." +
                    "%d readers in the database now.%n", r, readersIn);
    }

    public synchronized void readerExits(Reader r) {
            readersIn--;
            if (readersIn == 0) {
                writerIn = false;
                notifyAll();
            }
        System.out.printf("%s leaves the database.%n", r);
    }

    public synchronized void writerEnters(Writer w) {
        System.out.printf("%s wants to connect to the database.%n", w);
            while (readersIn > 0 || writerIn) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
            writerIn = true;
        System.out.printf("%s connected to the database." +
                "%d readers in the database now.%n", w, readersIn);
    }

    public synchronized void writerExits(Writer w) {
            writerIn=false;
            notifyAll();
        System.out.printf("%s leaves the database.%n", w);
    }
     */

    private static final int PERMITS = 100;
    private final Semaphore sem = new Semaphore(PERMITS);
    private final AtomicInteger readerIn = new AtomicInteger();

    public void readerEnters(Reader r) {
        System.out.printf("%s wants to connect to the database.%n", r);
        try {
            sem.acquire();
        } catch (InterruptedException e) {
        }
        int rIn = readerIn.incrementAndGet();
        System.out.printf("%s connected to the database. %d readers in the database now.%n", r, rIn);
    }

    public void readerExits(Reader r) {
        sem.release();
        System.out.printf("%s leaves the database.%n", r);
    }

    public void writerEnters(Writer w) {
        System.out.printf("%s wants to connect to the database.%n", w);
        try {
            sem.acquire(PERMITS);
        } catch (InterruptedException e) {
        }
        int rIn = readerIn.decrementAndGet();
        System.out.printf("%s connected to the database.%d readers in the database now.%n", w, rIn);
    }

    public void writerExits(Writer w) {
        sem.release();
        System.out.printf("%s leaves the database.%n", w);
    }
}
