package com.oobj.integrador.mensageria;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Scanner;

public class RecpçãoParalelaMensagens implements Runnable{


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

                @Override
                public void onMessage(Message message){
                    TextMessage textMessage  = (TextMessage)message;
                    try{
                        System.out.println(textMessage.getText());
                    } catch(JMSException e){
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
