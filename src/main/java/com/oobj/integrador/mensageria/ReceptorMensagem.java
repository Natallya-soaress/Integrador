package com.oobj.integrador.mensageria;

import javax.jms.*;
import javax.naming.NamingException;
import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.oobj.integrador.IO.EscritorArquivo.nomearArquivoImpresso;

public class ReceptorMensagem {

    public static void recebeMensagensProcessadas() throws NamingException, JMSException, FileNotFoundException {

        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        String nomeArquivo = nomearArquivoImpresso();
        OutputStream arquivo = new FileOutputStream(nomeArquivo);
        Writer escritorArquivo = new OutputStreamWriter(arquivo);
        BufferedWriter bufferedWriter = new BufferedWriter(escritorArquivo);

        threadPool.execute(new RecepçãoParalelaMensagens(bufferedWriter));

    }
}
