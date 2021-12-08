package com.tnicacio.produtorconsumidor.pilha;

import com.tnicacio.produtorconsumidor.exception.ServiceException;

public interface Pilha {

    void push(Integer value) throws ServiceException;

    Integer pop() throws ServiceException;

    boolean isEmpty();

    boolean isFull();

    void reset();

}
