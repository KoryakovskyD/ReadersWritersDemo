package ru.avalon.javapp.devj130;

public class Writer extends AbstarctEntity {
    public Writer(Database database, String name) {
        super(database, name);
    }

    @Override
    public void run() {
        while (true) {
            database.writerEnters(this);
            emulateWork(1_000, 2_000);
            database.writerExits(this);
            emulateWork(2_000, 3_000);
        }
    }
}
