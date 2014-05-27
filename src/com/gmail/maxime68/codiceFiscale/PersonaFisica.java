package com.gmail.maxime68.codiceFiscale;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class PersonaFisica implements Serializable {

	private String cognome;
	private String nome;
	private String comuneNascita;
	private String dataNascita;
	private Sesso sesso;

	public PersonaFisica() {

	}

	public PersonaFisica(String cognome, String nome, String comuneNascita,
			String dataNascita, Sesso sesso) throws BadFormatException {
		
		super();
	
		validate(dataNascita);
		
		this.cognome = cognome;
		this.nome = nome;
		this.comuneNascita = comuneNascita;
		this.dataNascita = dataNascita;
		this.sesso = sesso;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getComuneNascita() {
		return comuneNascita;
	}

	public void setComuneNascita(String comuneNascita) {
		this.comuneNascita = comuneNascita;
	}

	public String getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(String dataNascita) throws BadFormatException {
		
		validate(dataNascita);
		
		this.dataNascita = dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		
		this.dataNascita = String.format("%1$td/%1$tm/%1$tY", dataNascita);
	}
	
	public Sesso getSesso() {
		
		return sesso;
	}

	public void setSesso(Sesso sesso) {
		
		this.sesso = sesso;
	}
	
	private void validate(String dataNascita) throws BadFormatException{
		
		if (dataNascita != null && !dataNascita.matches("^\\d{2}/\\d{2}/\\d{4}$")){
			
			throw new BadFormatException("DataNascita must be in the format 'dd/mm/yyyy'");
			
		}
	}
}
