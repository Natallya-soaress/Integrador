package com.oobj.integrador.enfileirador;

import javax.jms.JMSException;
import javax.naming.NamingException;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import static com.oobj.integrador.enfileirador.LeitorArquivo.leArquivoEntrada;


public class EscritorArquivo {

    public static void escreveArquivoEntrada(String entrada, String nomeArquivo) throws IOException, NamingException, JMSException {

        Scanner scanner = new Scanner(entrada);
        OutputStream arquivo = new FileOutputStream(nomeArquivo);
        Writer escritorArquivo = new OutputStreamWriter(arquivo);
        BufferedWriter bufferedWriter = new BufferedWriter(escritorArquivo);

        while(scanner.hasNextLine()){
            String linha = scanner.nextLine();
            bufferedWriter.write(linha);
            bufferedWriter.newLine();
        }

        scanner.close();
        bufferedWriter.close();

        leArquivoEntrada(nomeArquivo);
    }

    public static void escreveArquivoProcessado(String nomeArquivo) throws IOException {

        String nomeArquivoProcessado = "Processados\\" + nomeArquivo.substring(8, 43);

        Scanner scanner = new Scanner(new File(nomeArquivo));
        OutputStream arquivoEntrada = new FileOutputStream(nomeArquivoProcessado);
        Writer escritorArquivo = new OutputStreamWriter(arquivoEntrada);
        BufferedWriter bufferedWriter = new BufferedWriter(escritorArquivo);

        while(scanner.hasNextLine()){
            String linha = scanner.nextLine();
            bufferedWriter.write(linha);
            bufferedWriter.newLine();
        }

        scanner.close();
        bufferedWriter.close();
    }

    public static String nomearArquivoEntrada(){

        LocalDateTime data = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String dataTexto = data.format(formatter);
        String nomeArquivo = "Entrada\\pre-impressao-" + dataTexto + ".txt";

        return nomeArquivo;
    }

    public static String nomearArquivoImpresso(){

        File file = new File("Processados");
        File[] arquivos = file.listFiles();

        String nome = arquivos[arquivos.length - 1].getName();
        String data = nome.substring(14, 31);

        String nomeArquivo = "Saida\\pre-impressao-" + data + "-retorno.txt";

        return nomeArquivo;
    }

    public static void escreveArquivoImpresso(List<String> mensagens) throws IOException {

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(nomearArquivoImpresso()));

        for (String msg : mensagens) {
            bufferedWriter.write(msg);
            bufferedWriter.newLine();
        }
        bufferedWriter.close();
    }

}
