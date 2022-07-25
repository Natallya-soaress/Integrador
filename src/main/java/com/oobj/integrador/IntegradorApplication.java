package com.oobj.integrador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.jms.JMSException;
import javax.naming.NamingException;

import java.io.FileNotFoundException;

import static com.oobj.integrador.mensageria.ReceptorMensagem.recebeMensagensProcessadas;

@SpringBootApplication
public class IntegradorApplication {

	public static void main(String[] args) throws NamingException, JMSException, FileNotFoundException {
		SpringApplication.run(IntegradorApplication.class, args);

		recebeMensagensProcessadas();

	}
}
