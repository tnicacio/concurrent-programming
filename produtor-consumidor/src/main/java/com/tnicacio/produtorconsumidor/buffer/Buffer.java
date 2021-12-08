package com.tnicacio.produtorconsumidor.buffer;

import com.tnicacio.produtorconsumidor.exception.ServiceException;
import com.tnicacio.produtorconsumidor.pilha.Pilha;
import com.tnicacio.produtorconsumidor.pilha.PilhaVetor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {

    private final Pilha stack;
    private final Lock stackChangingLocker;
    private final Condition fullStack;

    public Buffer(int maxLimit) {
        stack = new PilhaVetor(maxLimit);
        stackChangingLocker = new ReentrantLock();
        fullStack = stackChangingLocker.newCondition();
    }

    public synchronized void insere(int valor) {
        try {
            stackChangingLocker.lock();
            System.out.println("INSERE - Adicionado lock");

            while (isFull()) {
                System.out.println("INSERE - Aguardando espaço ser liberado!");
                fullStack.await();
            }

            System.out.println("INSERE - Inserindo valor: " + valor);
            stack.push(valor);
            System.out.println("INSERE - Buffer: " + stack);
            fullStack.signalAll();
        } catch (ServiceException | InterruptedException e) {
            System.out.println("Erro ao inserir valor: " + valor);
            e.printStackTrace();
        } finally {
            stackChangingLocker.unlock();
            System.out.println("INSERE - Removido lock");
        }
    }

    public Integer retira() {
        Integer poppedValue = null;
        try {
            stackChangingLocker.lock();
            System.out.println("RETIRA - Adicionado lock");

            while (isEmpty()) {
                System.out.println("RETIRA - Buffer vazio!");
                fullStack.await();
            }

            System.out.println("RETIRA - Removendo o último valor inserido");
            poppedValue = stack.pop();
            System.out.println("RETIRA - Removido o valor: " + poppedValue);
            System.out.println("RETIRA - Buffer: " + stack);
            fullStack.signalAll();
        } catch (ServiceException | InterruptedException e) {
            System.out.println("Erro ao remover o último valor inserido");
            e.printStackTrace();
        } finally {
            stackChangingLocker.unlock();
            System.out.println("RETIRA - Removido lock");
        }
        return poppedValue;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public boolean isFull() {
        return stack.isFull();
    }

}
