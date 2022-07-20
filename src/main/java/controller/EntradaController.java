package controller;

import com.oobj.integrador.Entrada;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntradaController {

    @PostMapping("api/pre-impressao")
    public ResponseEntity<String> recebeRequisição(@RequestBody Entrada entrada){

        String arquivoEntrada = entrada.getEntrada();
        System.out.println(arquivoEntrada);
        return ResponseEntity.ok().build();
    }
}
