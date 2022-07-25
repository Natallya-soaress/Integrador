package com.oobj.integrador.mensageria;

import javax.jms.*;
import javax.naming.NamingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReceptorMensagem {

    public static void recebeMensagensProcessadas() throws NamingException, JMSException {

        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.execute(new RecepçãoParalelaMensagens());

    }
}
