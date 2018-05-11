package main.java.model.render;

import java.util.ArrayList;

public class RenderHandler {
    public static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors() * 2;

    private ArrayList<Thread> workers = new ArrayList<>();

    public void addWorker(Thread worker) {
        workers.add((Thread)worker);
    }

    public void start() {
        for (Thread worker : workers) worker.run();

        for (Thread worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                System.err.println("Couldn`t run worker.");
            }
        }
        workers.clear();
    }
}
