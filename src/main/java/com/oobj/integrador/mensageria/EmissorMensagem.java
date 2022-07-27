package com.oobj.integrador.mensageria;


import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;
import java.util.Properties;

public class EmissorMensagem {


    public static void enviaMensagensPreImpressao(List<String> mensagens) throws NamingException, JMSException {

        InitialContext context = new InitialContext();
        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");

        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination fila = (Destination) context.lookup("impressao");
        MessageProducer producer = session.createProducer(fila);

        for(String msg : mensagens){
            Message message = session.createTextMessage(msg);
            producer.send(message);
        }

        session.close();
        connection.close();
        context.close();

    }
}
