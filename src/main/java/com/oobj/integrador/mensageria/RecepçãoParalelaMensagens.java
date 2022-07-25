package com.oobj.integrador.mensageria;


import javax.jms.*;
import javax.naming.InitialContext;
import java.io.*;
import java.util.Scanner;

import static com.oobj.integrador.IO.EscritorArquivo.*;


public class RecepçãoParalelaMensagens implements Runnable{


    @Override
    public void run() {

        InitialContext context = null;
        ConnectionFactory factory = null;
        Connection connection = null;
        Session session = null;
        Destination fila = null;
        MessageConsumer consumer = null;


        try {
            context = new InitialContext();
            factory = (ConnectionFactory) context.lookup("ConnectionFactory");
            connection = factory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            fila = (Destination) context.lookup("impressao");
            consumer = session.createConsumer(fila);

            consumer.setMessageListener(new MessageListener(){

                String nomeArquivo = nomearArquivoImpresso();
                OutputStream arquivo = new FileOutputStream(nomeArquivo);
                Writer escritorArquivo = new OutputStreamWriter(arquivo);
                BufferedWriter bufferedWriter = new BufferedWriter(escritorArquivo);

                @Override
                public void onMessage(Message message){
                    TextMessage textMessage  = (TextMessage)message;
                    try{

                        String mensagem = textMessage.getText();
                        String subItinerario = "";
                        String seq = "";

                        String[] linhasmensagem = mensagem.split("\n");

                        for(String linhamgs : linhasmensagem){
                            if(linhamgs.startsWith("22002")){
                                subItinerario = linhamgs.substring(42, 49);
                            }
                            if(linhamgs.startsWith("22007")){
                                seq = linhamgs.substring(38, 41);
                            }
                        }

                        String linha = subItinerario + "|" + seq;

                        System.out.println(linha);
                        bufferedWriter.write(linha);
                        bufferedWriter.newLine();

                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
            });

            new Scanner(System.in).nextLine();
            session.close();
            connection.close();
            consumer.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
