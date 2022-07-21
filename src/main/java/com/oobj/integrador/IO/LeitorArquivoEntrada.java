package com.oobj.integrador.IO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.oobj.integrador.IO.EscritorArquivoEntrada.escreveArquivoProcessado;
import static com.oobj.integrador.IO.EscritorArquivoEntrada.nomearArquivoProcessado;

public class LeitorArquivoEntrada {

    public static List<String> leArquivoEntrada(String nomeArquivo) throws IOException {

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
        String nome = nomearArquivoProcessado(nomeArquivo);
        escreveArquivoProcessado(nomeArquivo, nome);
        return mensagens;
    }

}
