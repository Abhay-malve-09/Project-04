
package in.co.rays.proj4.controller;

import java.io.IOException;

import org.apache.log4j.Logger;

import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * FrontCtl is a filter class that implements the Front Controller
 * design pattern. It prevents unauthorized users from accessing
 * application resources without logging in.
 *
 * It checks whether a valid user session exists before allowing
 * access to the requested resource.
 *
 * @author Abhay Malve
 * @version 1.0
 */
@WebFilter("/ctl/*")
public class FrontCtl implements Filter {

	private static final Logger log = Logger.getLogger(FrontCtl.class);

	/**
	 * Initializes the filter.
	 *
	 * @param filterConfig the FilterConfig object
	 * @throws ServletException if an error occurs during filter initialization
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		log.info("FrontCtl filter initialized");

	}

	/**
	 * Intercepts incoming requests and checks whether the user
	 * is logged in. If the session is expired, forwards the user
	 * to the login page. Otherwise, passes the request to the
	 * next filter or controller in the chain.
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

		log.debug("FrontCtl doFilter method started");

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession();

		if (session.getAttribute("user") == null) {

			log.warn("User session not found or expired. Redirecting to login view");

			ServletUtility.setErrorMessage("session has been expired please re-login", req);

			ServletUtility.forward(ORSView.LOGIN_VIEW, req, res);

			return;
		}

		log.debug("Valid user session found. Passing request to next filter/controller");

		chain.doFilter(request, response);

		log.debug("FrontCtl doFilter method completed");
	}

	/**
	 * Destroys the filter and releases any resources.
	 */
	@Override
	public void destroy() {

		log.info("FrontCtl filter destroyed");

	}
}
