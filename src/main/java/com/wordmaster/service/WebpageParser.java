package com.wordmaster.service;

import java.net.URI;

public interface WebpageParser {
	
	//TODO Leave only one option
	
	//-----------------OPTION 1: if headers are already parsed into an array by a third-party library
	
	/**
	 * The method gets the headlines for the given URL
	 * @param uri url of the website from which the headlines are to be taken
	 * @headerTag the tag which is the delimits the headlines on a given webpage
	 * @return
	 */
	String getHeadlines(URI uri, String headerTag);
	
	String[] splitHeadlines(String headlines);
	
	
	
	//-----------------OPTION 2: if headers are already parsed into an array by a third-party library
	
	/**
	 * Should have the functionality of the two methods above
	 * @param uri
	 * @return
	 */
	String[] getHeadlinesAsArray(URI uri, String headerTag);

}
