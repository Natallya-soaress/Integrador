package com.oobj.integrador.mensageria;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Component
public class ReceptorMensagem implements MessageListener {

    @JmsListener(destination="pre_impressao", concurrency="4")
    @Override
    public void onMessage(Message message) {
        try {
            String mensagem = ((TextMessage) message).getText();
            System.out.println(mensagem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static List<String> recebeMensagens() throws Exception {
//
//        InitialContext context = new InitialContext();
//
//        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
//        Connection connection = factory.createConnection();
//        connection.start();
//
//        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        Destination fila = (Destination) context.lookup("impressao");
//        MessageConsumer consumer = session.createConsumer(fila);
//
//        List<String> mensagens = new ArrayList<>();
//
//        consumer.setMessageListener(new MessageListener() {
//
//            @Override
//            public void onMessage(Message message) {
//                TextMessage textMessage = (TextMessage) message;
//                try {
//                    String mensagem = textMessage.getText();
//                    String subItinerario = "";
//                    String seq = "";
//
//                    String[] linhasmensagem = mensagem.split("\n");
//
//                    for (String linhamgs : linhasmensagem) {
//                        if (linhamgs.startsWith("22002")) {
//                            subItinerario = linhamgs.substring(42, 49);
//                        }
//                        if (linhamgs.startsWith("22007")) {
//                            seq = linhamgs.substring(38, 41);
//                        }
//                    }
//
//                    String linha = subItinerario + "|" + seq;
//                    mensagens.add(linha);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        TimeUnit.SECONDS.sleep(10);
//        session.close();
//        connection.close();
//        consumer.close();
//
//        return mensagens;
//    }
}
