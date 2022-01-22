package ru.avalon.javapp.devj130;

public class Reader extends AbstarctEntity {
    public Reader(Database database, String name) {
        super(database, name);
    }

    @Override
    public void run() {
        while (true) {
            database.readerEnters(this);
            emulateWork(500, 1_000);
            database.readerExits(this);
            emulateWork(2_000, 3_000);
        }
    }
}
