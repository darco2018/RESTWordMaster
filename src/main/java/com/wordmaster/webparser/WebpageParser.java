package com.wordmaster.webparser;

import java.net.URI;
import java.util.List;

public interface WebpageParser {
	
	
	/**
	 * The method gets DOM elements for the given URL and tag
	 * @param uri uri of the website from which the headlines are to be taken
	 * @headerTag the tag which delimits the DOM elements on a given webpage
	 * @return
	 */
	String getHTMLElementsAsString(URI uri, String delimitingTag);
	
	List<String> getHTMLElementsList(URI uri, String delimitingTag);
	
}
