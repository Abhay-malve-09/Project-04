
package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.model.BaseModel;
import jakarta.servlet.annotation.WebServlet;

/**
 * WelcomeCtl is a controller class used to display
 * the welcome page of the application after login.
 *
 * @author Abhay Malve
 * @version 1.0
 */
@WebServlet("/WelcomeCtl")
public class WelcomeCtl extends BaseCtl {

	private static final Logger log = Logger.getLogger(WelcomeCtl.class);

	/**
	 * Returns the BaseModel object.
	 *
	 * @return null as no model is required
	 */
	@Override
	public BaseModel getModel() {

		log.debug("No model is required for WelcomeCtl");
		return null;
	}

	/**
	 * Returns the welcome view.
	 *
	 * @return the welcome view name
	 */
	@Override
	public String getView() {

		log.debug("Returning welcome view");
		return ORSView.WELCOME_VIEW;
	}

}
