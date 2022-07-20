package controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;


@RequestMapping("api")
@RestController
public class EntradaController {

    @PostMapping("/pre-impressao")
    public ResponseEntity<String> recebeRequisição(@RequestBody String entrada){

        System.out.println(entrada);
        return ResponseEntity.ok().build();
    }

    public void EscreveArquivo(String entrada) throws IOException {

        FileWriter arquivoEntrada = new FileWriter("C:\\Users\\Nátallya-Oobj\\OneDrive\\Documentos\\entrada.txt");
        PrintWriter gravarArquivo = new PrintWriter(arquivoEntrada);

        

        }
    }
