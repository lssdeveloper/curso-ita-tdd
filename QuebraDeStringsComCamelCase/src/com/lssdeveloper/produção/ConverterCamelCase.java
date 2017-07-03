package com.lssdeveloper.produção;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class ConverterCamelCase {

	public ConverterCamelCase(String str) {
	}

	public static boolean isStringValida(String text) {
		return ((text != null) && (text.trim().length() > 0));
	}

	public int tamanhoString(String str) {
		return str.trim().length();
	}

	public static boolean temNumeroEntreAString(String str){
		return (str.matches("((.*)([0-9]+)(.*))"));
	}

	public static boolean iniciaComNumero(String str){
		return Character.isDigit(str.charAt(0));
	}
	public static boolean temCharEspecial(String str){
		boolean charEspecial = false;
		for (int i = 0; i < str.length(); i++) {
			if (!(Character.isLetter(str.charAt(i)) || Character.isDigit(str.charAt(i)))) {
				charEspecial = true;
				break;
			}			
		}
		return charEspecial;
	}
	
	public static List<String> converterCamelCase(String original){

		if (!isStringValida(original)) 
			throw new ConvertException("Não é possível String null ou vazia.");
		else if (temCharEspecial(original))
			throw new ConvertException("Caracteres especiais não são permitidos, somente letras e números.");
		else if (iniciaComNumero(original))
			throw new ConvertException("Não deve começar com números.");
		else if (temNumeroEntreAString(original))  
			  return numeroEntreStringCamelCase(original);
				
		String[] strSplit = original.split("(?<=[a-z])(?=[A-Z])|(?<=[A-Z])(?=[A-Z][a-z])");
		
		return convertParaLowerCase(strSplit);
	}
	public static List<String> numeroEntreStringCamelCase(String str) {
		
		Pattern ptt = Pattern.compile("[A-Za-z]+|[0-9]+");
		Matcher mtr = ptt.matcher(str);
		
		List<String> lstString = new ArrayList<String>();
		
		while (mtr.find())
			lstString.add(mtr.group().trim());

		String[] strSplit = new String[lstString.size()];
		return convertParaLowerCase(lstString.toArray(strSplit));
	}

	private static List<String> convertParaLowerCase(String[] array) {
		List<String> lstString = new ArrayList<String>();
		for (int i = 0; i < array.length; i++)
			if (!array[i].isEmpty())
				if (array[i].equals(array[i].toUpperCase()))
					lstString.add(array[i]);
				else
					lstString.add(array[i].toLowerCase());
		return lstString;
	}	
	public static void main(String[] args) {
		
		 //System.out.println(converterCamelCase(""));
		 //System.out.println(converterCamelCase("nome#Composto"));
		 //System.out.println(converterCamelCase("10Primeiros"));		 
		 System.out.println(converterCamelCase("nome"));
		 System.out.println(converterCamelCase("Nome"));
		 System.out.println(converterCamelCase("nomeComposto"));		
		 System.out.println(converterCamelCase("NomeComposto"));
		 System.out.println(converterCamelCase("CPF"));
		 System.out.println(converterCamelCase("numeroCPF"));
		 System.out.println(converterCamelCase("numeroCPFContribuinte"));
		 System.out.println(converterCamelCase("recupera10Primeiros"));		
	
 
		 
	}	
}
