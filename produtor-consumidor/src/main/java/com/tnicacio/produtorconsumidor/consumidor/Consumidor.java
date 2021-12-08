package com.tnicacio.produtorconsumidor.consumidor;

import com.tnicacio.produtorconsumidor.buffer.Buffer;

public class Consumidor implements Runnable {

    private final int delay;
    private final Buffer buffer;
    private final int repetitions;

    public Consumidor(Buffer buffer, int repetitions, int delay) {
        this.buffer = buffer;
        this.repetitions = repetitions;
        this.delay = delay;
    }

    public Consumidor(Buffer buffer, int repetitions) {
        this.buffer = buffer;
        this.repetitions = repetitions;
        this.delay = 0;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < repetitions && !Thread.interrupted(); i++) {
                System.out.println("Retirando - for loop " + i);
                buffer.retira();
                if (delay > 0) {
                    Thread.sleep(delay);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Consumidor - InterruptedException");
            e.printStackTrace();
        }
    }

}
