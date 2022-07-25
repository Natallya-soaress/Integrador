package com.oobj.integrador.IO;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class EscritorArquivo {

    public static void  escreveArquivo(String entrada, String nomeArquivo) throws IOException {

        Scanner scanner = new Scanner(entrada);
        OutputStream arquivo = new FileOutputStream(nomeArquivo);
        Writer escritorArquivo = new OutputStreamWriter(arquivo);
        BufferedWriter bufferedWriter = new BufferedWriter(escritorArquivo);

        while(scanner.hasNextLine()){
            String linha = scanner.nextLine();
            bufferedWriter.write(linha);
            bufferedWriter.newLine();
        }
    }

    public static void escreveArquivoProcessado(String nomeArquivoEntrada, String nomeArquivoProcessado) throws IOException {

        Scanner scanner = new Scanner(new File(nomeArquivoEntrada));
        OutputStream arquivoEntrada = new FileOutputStream(nomeArquivoProcessado);
        Writer escritorArquivo = new OutputStreamWriter(arquivoEntrada);
        BufferedWriter bufferedWriter = new BufferedWriter(escritorArquivo);

        while(scanner.hasNextLine()){
            String linha = scanner.nextLine();
            bufferedWriter.write(linha);
            bufferedWriter.newLine();
        }
    }

    public static String nomearArquivoEntrada(){

        LocalDateTime data = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String dataTexto = data.format(formatter);
        String nomeArquivo = "Entrada\\pre-impressao-" + dataTexto + ".txt";

        return nomeArquivo;
    }

    public static String nomearArquivoProcessado(String nomeArquivo){

        String dataTexto = nomeArquivo.substring(22, 39);
        String nome = "Processados\\pre-processamento-" + dataTexto + ".txt";

        return nome;
    }

    public static String nomearArquivoImpresso(){

        LocalDateTime data = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String dataTexto = data.format(formatter);
        String nomeArquivo = "Saida\\pre-impressao -" + dataTexto + "-retorno.txt";

        return nomeArquivo;
    }

}
