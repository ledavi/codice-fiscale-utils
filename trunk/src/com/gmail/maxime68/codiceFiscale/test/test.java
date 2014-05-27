package com.gmail.maxime68.codiceFiscale.test;

import java.io.IOException;

import org.jdom2.JDOMException;

import com.gmail.maxime68.codiceFiscale.BadFormatException;
import com.gmail.maxime68.codiceFiscale.CodiceFiscaleWebService;
import com.gmail.maxime68.codiceFiscale.PersonaFisica;
import com.gmail.maxime68.codiceFiscale.Sesso;

public class test {

	public static void main(String[] args) throws JDOMException, IOException, BadFormatException {
		
		CodiceFiscaleWebService cfws = CodiceFiscaleWebService.getInstance();
		
		PersonaFisica pf = new PersonaFisica("manco", "massimo", "Agliè", "15/07/1968", Sesso.M);
		
		System.out.println(cfws.calculateGet(pf));
		
		System.out.println(cfws.validate("RSSPLA90A01F205X"));

	}

}
