package com.oobj.integrador.enfileirador;

import javax.jms.JMSException;
import javax.naming.NamingException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.oobj.integrador.enfileirador.EscritorArquivo.escreveArquivoProcessado;
import static com.oobj.integrador.Broker.EmissorMensagem.enviaMensagensPreImpressao;

public class LeitorArquivo {

    public static void leArquivoEntrada(String nomeArquivo) throws IOException, NamingException, JMSException {

        List<String> mensagens = new ArrayList<>();
        String mensagem = "";
        Scanner scanner = new Scanner(new FileReader(nomeArquivo));
        StringBuffer stringBuffer = new StringBuffer();

        while(scanner.hasNextLine()){
            String linha = scanner.nextLine();
            stringBuffer.append(linha);
            stringBuffer.append("\n");

            if(linha.equals("25000;STAPLE_TOP_LEFT")){
                mensagem = stringBuffer.toString();
                mensagens.add(mensagem);
                stringBuffer.setLength(0);
            }
        }

        escreveArquivoProcessado(nomeArquivo);
        enviaMensagensPreImpressao(mensagens);
    }
}
