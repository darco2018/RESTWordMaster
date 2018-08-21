package com.wordmaster.webparser;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__({ @Autowired }))
@RestController
public class HomeController {

	private static final Logger logger = LogManager.getLogger(JsoupParser.class);
	@Value("${onet.url}")
	private String defaultUrl; // "http://www.onet.pl/"
	@Value("${onet.headlines.tag}")
	private String defaultTag;
	private final @NotNull WebpageParser parser;

	@RequestMapping("/home")
	public String displayHomePage() {
		return "Home page";
	}

	@RequestMapping("/headlines/{url}/{tag}")
	public String getHeadlines(@PathVariable Optional<String> url, @PathVariable() Optional<String> tag) {

		URI uri = null;
		if (url.isPresent()) {
			uri = getURI(url.get());
		}
		
		if (uri == null) {
			logger.info("Creating URI with default value " + defaultUrl);
			uri = getURI(defaultUrl);
		}
		
		if (uri == null) {
			return "Invalid default url " + defaultUrl;
		}
		
		String tagValue = tag.isPresent() ? tag.get() : defaultTag;
		logger.info("Calling " + uri + " with tag " + tagValue);
		List<String> headlinesList = parser.getHTMLElementsList(uri, tagValue);
		return getFormatedHeadlines(headlinesList, uri.toString());

	}

	private String getFormatedHeadlines(List<String> headlines, String src) {

		StringBuilder sb = new StringBuilder();
		sb.append("HEADLINES from " + src + "<br>");
		sb.append("No of headlines: " + headlines.size() + "<br>");
		for (String headline : headlines) {
			sb.append(headline + "<br>");
		}
		return sb.toString();

	}

	/**
	 * @return valid URI or null if such a URI cannot be created
	 */
	private URI getURI(String url) {

		URL u = null;
		URI uri = null;
		try {
			u = new URL(url); // this would check for the protocol
			uri = u.toURI(); // // does the extra checking required for validation of URI
		} catch (URISyntaxException | MalformedURLException ex) {
			logger.info("Invalid url: " + url);
		}

		return uri;

	}

}
