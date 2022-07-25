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

import static com.oobj.integrador.IO.EscritorArquivo.escreveArquivo;
import static com.oobj.integrador.IO.EscritorArquivo.nomearArquivoEntrada;
import static com.oobj.integrador.IO.LeitorArquivo.leArquivoEntrada;
import static com.oobj.integrador.mensageria.EmissorMensagem.enviaMensagensPreImpressao;


@RequestMapping("api")
@RestController
public class EntradaController {

    @PostMapping("/pre-impressao")
    public ResponseEntity<String> recebeRequisição(@RequestBody String entrada) throws IOException, NamingException, JMSException {

        String nomeArquivo = nomearArquivoEntrada();
        escreveArquivo(entrada, nomeArquivo);
        List<String> mensagens = leArquivoEntrada(nomeArquivo);
        enviaMensagensPreImpressao(mensagens);

        return new ResponseEntity<String>("preImpressaoSolicitada : true", HttpStatus.OK);
    }

}
