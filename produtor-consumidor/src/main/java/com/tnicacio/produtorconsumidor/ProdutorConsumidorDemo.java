package com.tnicacio.produtorconsumidor;

import com.tnicacio.produtorconsumidor.buffer.Buffer;
import com.tnicacio.produtorconsumidor.consumidor.Consumidor;
import com.tnicacio.produtorconsumidor.produtor.Produtor;

public class ProdutorConsumidorDemo {

    public static void main(String... args) {
        System.out.println("Produtor-Consumidor");

        final int BUFFER_MAX_SIZE = 2;
        final int REPETITIONS = 5;

        Buffer buffer = new Buffer(BUFFER_MAX_SIZE);

        Produtor produtor = new Produtor(buffer, REPETITIONS);
        Consumidor consumidor = new Consumidor(buffer, REPETITIONS, 1000);

        Thread produtorThread = new Thread(produtor);
        Thread consumidorThread = new Thread(consumidor);

        produtorThread.start();
        consumidorThread.start();
    }

}
