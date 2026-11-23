
package in.co.rays.proj4.controller;

import java.io.IOException;

import org.apache.log4j.Logger;

import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.MessageSource;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

/**
 * LanguageFilter is a filter class used to manage
 * the application language based on the language
 * parameter received in the HTTP request.
 *
 * It updates the application locale using MessageSource
 * and passes the request to the next filter in the chain.
 *
 * @author Abhay Malve
 * @version 1.0
 */
@WebFilter("/*")
public class LanguageFilter implements Filter {

	private static final Logger log = Logger.getLogger(LanguageFilter.class);

	/**
	 * Initializes the language filter.
	 *
	 * @param filterConfig the FilterConfig object
	 * @throws ServletException if an error occurs during filter initialization
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("LanguageFilter initialized");
	}

	/**
	 * Processes the incoming request and checks whether
	 * a language parameter is provided.
	 * If a valid language is provided, updates the application locale.
	 *
	 * @param request the ServletRequest object
	 * @param response the ServletResponse object
	 * @param chain the FilterChain object
	 * @throws IOException if an input or output error occurs
	 * @throws ServletException if a servlet error occurs
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		log.debug("LanguageFilter doFilter started");

		HttpServletRequest req = (HttpServletRequest) request;
		MessageSource messagesource = MessageSource.getInstance();

		String lang = DataUtility.getString(req.getParameter("lang"));
		if (DataValidator.isNotNull(lang)) {
			messagesource.setLocale(lang);
			log.info("Application language updated");
		}

		chain.doFilter(req, response);

		log.debug("LanguageFilter doFilter completed");
	}

	/**
	 * Destroys the language filter and releases any resources.
	 */
	@Override
	public void destroy() {
		log.info("LanguageFilter destroyed");
	}

}
