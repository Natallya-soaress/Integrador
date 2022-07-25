package com.oobj.integrador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.jms.JMSException;
import javax.naming.NamingException;

import static com.oobj.integrador.mensageria.ReceptorMensagem.recebeMensagensProcessadas;

@SpringBootApplication
public class IntegradorApplication {

	public static void main(String[] args) throws NamingException, JMSException {
		SpringApplication.run(IntegradorApplication.class, args);
		recebeMensagensProcessadas();
	}
}
