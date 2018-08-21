package com.wordmaster.webparser;

import java.io.IOException;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class JsoupParser implements WebpageParser {

	private static final Logger logger = LogManager.getLogger(JsoupParser.class);

	@Override
	public String getHTMLElementsAsString(URI uri, String delimitingTag) {
		
		logger.info("Getting the HTML Elements...");

		Elements htmlElements = getElements(uri, delimitingTag);
		
		if (htmlElements == null) {
			logger.info("HTML Elements is null!");
			return null;
		} else {
			logger.info("Returning the HTML Elements as string");
			return htmlElements.toString();
		}
	}

	@Override
	public List<String> getHTMLElementsList(URI uri, String delimitingTag) {
		
		logger.info("Getting the HTML Elements...");

		Elements htmlElements = getElements(uri, delimitingTag);

		if (htmlElements == null) {
			logger.info("HTML Elements is null!");
			return null;
		} else {
			
			List<String> list = new LinkedList<>();

			for (Element elem : htmlElements) {
				list.add(elem.text());
			}
			
			logger.info("Returning the list of HTML Elements. The size of the list is " + htmlElements.size());

			return list;
		}
	}
  // Creates a new Connection to a URL. Use to fetch and parse a HTML page.
	// returns the connection. You can add data, cookies, and headers; set the user-agent, referrer, method; and then execute.
	private Document getHTMLDocument(URI uri) {
		
		logger.info("Establishing the connection to " + uri +  " ...");
		
		try {
			
			Connection connection = Jsoup.connect(uri.toString()); // "http://www.onet.pl/"
			if(connection == null) {
				throw new IOException("The connection is null!");
			}
			
			logger.info("Connection established. Retrieving the HTML Document...");	
			
			return connection.get(); // Execute the request as a GET, and parse the result.
			
		} catch (IOException ex) {
			logger.info("Cannot establish the connection...", ex);
		}

		return null;
	}

	private Elements getElements(URI uri, String delimitingTag) {
		
		logger.info("Getting the HTMLDocument for tag " + delimitingTag + " ..." );

		Document document = this.getHTMLDocument(uri);
		
		if (document == null) {
			return null;
		} else {
			logger.info("Retrieved the HTMLDocument from " + document.location() );
			return document.select(delimitingTag);
		}
	}

}
