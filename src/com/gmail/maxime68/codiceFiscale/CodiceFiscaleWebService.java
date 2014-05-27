package com.gmail.maxime68.codiceFiscale;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class CodiceFiscaleWebService {

	private static CodiceFiscaleWebService instance = null;

	private final String host = "http://webservices.dotnethell.it/codicefiscale.asmx/";

	private URL webServiceUrl;

	private CodiceFiscaleWebService() throws MalformedURLException {

		instance = this;
	}

	public static CodiceFiscaleWebService getInstance()
			throws MalformedURLException {

		if (instance == null)
			instance = new CodiceFiscaleWebService();

		return instance;
	}

	public String calculateGet(PersonaFisica personaFisica) throws JDOMException, IOException {

		webServiceUrl = new URL(host + Services.CalcolaCodiceFiscale.toString() + parametersUrlEncode(personaFisica));

		return getXMLResponse(webServiceUrl);

	}

	public boolean validate (String codiceFiscale) throws JDOMException, IOException{
		
		webServiceUrl = new URL(host + Services.ControllaCodiceFiscale.toString() + "?CodiceFiscale="+URLEncoder.encode(codiceFiscale, "UTF-8"));

		return getXMLResponse(webServiceUrl).equals("Il codice è valido!");
	}
	
	private String getXMLResponse(URL webServiceUrl) throws JDOMException, IOException{
		
		SAXBuilder builder = new SAXBuilder();

		Document document = (Document) builder.build(webServiceUrl);
		Element rootNode = document.getRootElement();
		return rootNode.getValue();
	}
	
	private String parametersUrlEncode(PersonaFisica personaFisica)
			throws UnsupportedEncodingException {

		String parameters = "?";
		parameters += Parameters.Cognome + "="
				+ URLEncoder.encode(personaFisica.getCognome(), "UTF-8");
		parameters += "&" + Parameters.Nome + "="
				+ URLEncoder.encode(personaFisica.getNome(), "UTF-8");
		parameters += "&" + Parameters.Comunenascita + "="
				+ URLEncoder.encode(sanitize(personaFisica.getComuneNascita()), "UTF-8");
		parameters += "&" + Parameters.DataNascita + "="
				+ URLEncoder.encode(personaFisica.getDataNascita(), "UTF-8");
		parameters += "&" + Parameters.Sesso + "=" + personaFisica.getSesso();

		return parameters;
	}

	public enum Parameters {
		Nome, Cognome, Comunenascita, DataNascita, Sesso
	}

	private String sanitize(String text){
		
		Map<String,String> replacements = new HashMap<String, String>(6,0.75f);
		
		replacements.put("à", "a'");
		replacements.put("è", "e'");
		replacements.put("é", "e'");
		replacements.put("ì", "i'");
		replacements.put("ò", "o'");
		replacements.put("ù", "u'");
		
		for (String target : replacements.keySet()){
			
			text = text.replace(target, replacements.get(target));
			
		}
		
		return text;
	}
	
}
