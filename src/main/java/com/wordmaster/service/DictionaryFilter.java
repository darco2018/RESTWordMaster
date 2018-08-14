package com.wordmaster.service;

import java.nio.file.Path;

/**
 * Compares the headlines with a file of 1000-5000 most common English words.
 * Leaves only those not present in the file
 * Creates a map where the key is a given word and the value the headline in which it appears
 * 
 * @author dariusz
 *
 */
public interface DictionaryFilter {
	
	// could be a List
	String[] method  (String[] headlines, Path mostCommonWords);

}
