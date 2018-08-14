package com.wordmaster.service;

public interface LanguageCorpusAPIReader {
	
	
	/**
	 * Gets Oxford/Longman/etc. data (in json) for a given word
	 * @param 
	 * @return data about the paramter word 
	 */
	String getLanguageData(String word);

}
