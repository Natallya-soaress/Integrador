package com.oobj.integrador.mensageria;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Scanner;

public class RecptorMensagem {

    public static void recebeMensagensProcessadas() throws NamingException, JMSException {

        InitialContext context = new InitialContext();

        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination fila = (Destination) context.lookup("impressao");
        MessageConsumer consumer = session.createConsumer(fila);

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

    }
}
