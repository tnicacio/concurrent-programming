package com.tnicacio.produtorconsumidor.produtor;

import com.tnicacio.produtorconsumidor.buffer.Buffer;
import com.tnicacio.produtorconsumidor.utils.RandomUtils;
import jdk.swing.interop.SwingInterOpUtils;

public class Produtor implements Runnable {

    private final int delay;
    private final Buffer buffer;
    private final int repetitions;

    public Produtor(Buffer buffer, int repetitions, int delay) {
        this.buffer = buffer;
        this.repetitions = repetitions;
        this.delay = delay;
    }

    public Produtor(Buffer buffer, int repetitions) {
        this.buffer = buffer;
        this.repetitions = repetitions;
        this.delay = 0;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < repetitions && !Thread.interrupted(); i++) {
                System.out.println("Inserindo - for loop " + i);
                buffer.insere(RandomUtils.nextInt());
                if (delay > 0) {
                    Thread.sleep(delay);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Produtor - InterruptedException");
            e.printStackTrace();
        }
    }

}
