package com.oobj.integrador.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.JMSException;
import javax.naming.NamingException;
import java.io.*;
import java.util.List;

import static com.oobj.integrador.IO.EscritorArquivoEntrada.escreveArquivoEntrada;
import static com.oobj.integrador.IO.EscritorArquivoEntrada.nomearArquivoEntrada;
import static com.oobj.integrador.IO.LeitorArquivoEntrada.leArquivoEntrada;
import static com.oobj.integrador.mensageria.EmissorMensagem.enviaMensagensPreImpressao;
import static com.oobj.integrador.mensageria.RecptorMensagem.recebeMensagensProcessadas;


@RequestMapping("api")
@RestController
public class EntradaController {

    @PostMapping("/pre-impressao")
    public ResponseEntity<String> recebeRequisição(@RequestBody String entrada) throws IOException, NamingException, JMSException {

        String nomeArquivo = nomearArquivoEntrada();
        escreveArquivoEntrada(entrada, nomeArquivo);
        List<String> mensagens = leArquivoEntrada(nomeArquivo);
        enviaMensagensPreImpressao(mensagens);
        recebeMensagensProcessadas();

        return new ResponseEntity<String>("preImpressaoSolicitada : true", HttpStatus.OK);
    }

}
