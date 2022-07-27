package com.oobj.integrador.receiver;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import java.util.ArrayList;
import java.util.List;

import static com.oobj.integrador.enfileirador.EscritorArquivo.escreveArquivoImpresso;

@Component
public class ReceptorMensagens implements MessageListener {

    private List<String> mensagens = new ArrayList<>();
    private int quantidadeMensagens = 44;

    @JmsListener(destination="pre_impressao", concurrency="4")
    @Override
    public void onMessage(Message message) {
        try {
            String mensagem = ((TextMessage) message).getText();
            String linha = trataMensagensImpressas(mensagem);
            mensagens.add(linha);

            if(mensagens.size() == quantidadeMensagens){
                escreveArquivoImpresso(mensagens);
                quantidadeMensagens += 44;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String trataMensagensImpressas(String mensagem){

        String subItinerario = "";
        String seq = "";

        String[] linhasmensagem = mensagem.split("\n");

        for (String linhamgs : linhasmensagem) {
            if (linhamgs.startsWith("22002")) {
                subItinerario = linhamgs.substring(42, 49);
            }
            if (linhamgs.startsWith("22007")) {
                seq = linhamgs.substring(38, 41);
            }
        }

        String linha = subItinerario + "|" + seq;
        return linha;
    }
}
