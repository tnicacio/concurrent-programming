package com.tnicacio.produtorconsumidor.pilha;

import com.tnicacio.produtorconsumidor.exception.ServiceException;

public class PilhaVetor implements Pilha {

    private final int maxCapacity;

    private int currentNumberOfElements;

    private final Integer[] stack;

    public PilhaVetor(int maxCapacity) {
        this.currentNumberOfElements = 0;
        this.maxCapacity = maxCapacity;
        this.stack = new Integer[maxCapacity];
    }

    @Override
    public synchronized void push(Integer value) throws ServiceException {
        if (currentNumberOfElements == maxCapacity) {
            throw new ServiceException("Stack overflow!");
        }
        this.stack[currentNumberOfElements] = value;
        this.currentNumberOfElements++;
    }

    @Override
    public synchronized Integer pop() throws ServiceException {
        if (this.currentNumberOfElements == 0) {
            throw new ServiceException("Erro: pilha vazia!!");
        }
        int valueToPop = this.stack[this.currentNumberOfElements - 1];
        this.currentNumberOfElements--;
        return valueToPop;
    }

    @Override
    public synchronized boolean isEmpty() {
        return this.currentNumberOfElements == 0;
    }

    @Override
    public boolean isFull() {
        return currentNumberOfElements == maxCapacity;
    }

    @Override
    public synchronized void reset() {
        this.currentNumberOfElements = 0;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Pilha vazia.";
        }
        StringBuilder s = new StringBuilder("Pilha: ");
        for (int i = 0; i < this.currentNumberOfElements; i++) {
            s.append(this.stack[i]).append(" ");
        }
        return s.toString();
    }

}
