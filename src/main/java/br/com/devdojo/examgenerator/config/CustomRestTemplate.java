package br.com.devdojo.examgenerator.config;

import org.springframework.web.client.RestTemplate;


//Sem essa classe, o @inject for resttemplate não funcionará
public class CustomRestTemplate extends RestTemplate {

}
