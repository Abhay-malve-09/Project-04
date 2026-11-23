
package in.co.rays.proj4.controller;

import java.io.IOException;
import java.net.Authenticator.RequestorType;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.model.BaseModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * BaseListCtl is an abstract controller class used to handle
 * list operations such as displaying records, searching records,
 * deleting records and navigating between pages.
 *
 * @param <B> the bean type extending BaseBean
 * @param <M> the model type extending BaseModel
 *
 * @author Abhay Malve
 * @version 1.0
 */
public abstract class BaseListCtl<B extends BaseBean, M extends BaseModel> extends BaseCtl<B, M> {

	private static final Logger log = Logger.getLogger(BaseListCtl.class);

	/**
	 * Handles HTTP GET requests.
	 * Retrieves records from the model and forwards them to the view.
	 *
	 * @param request the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @throws ServletException if a servlet error occurs
	 * @throws IOException if an input or output error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		log.debug("doGet() method started");

		M model = getModel();
		B bean = populateBean(request);
		int pageNo = 1;
		int pageSize = 5;
	
		List<B> list = model.search(bean, pageNo, pageSize);
		List<B> nextList = model.search(bean, pageNo + 1, pageSize);
	
//		request.setAttribute("nextList", nextList);
		request.setAttribute("nextList", nextList.size());
		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);

		log.info("Records fetched successfully");
	
		ServletUtility.forward(getView(), request, response);

		log.debug("doGet() method completed");
	}
	
	/**
	 * Handles HTTP POST requests.
	 * Performs operations such as delete, next page, previous page
	 * and search based on the operation parameter.
	 *
	 * @param request the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @throws ServletException if a servlet error occurs
	 * @throws IOException if an input or output error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		log.debug("doPost() method started");

		String op = DataUtility.getString(request.getParameter("operation"));
		
		M model = getModel();
		B bean = populateBean(request);
		
		int pageNo = 1;
		int pageSize = 5;
		
		if (OP_DELETE.equalsIgnoreCase(op)) {

			log.debug("Delete operation started");
			
			pageNo = DataUtility.getInt(request.getParameter("pageNo"));
			
			String[] ids = request.getParameterValues("ids");
			
			if (ids != null && ids.length > 0) {
				for (String id : ids) {
					
					model.delete(DataUtility.getInt(id));
					ServletUtility.setSuccessMessage("record deleted successfully", request);

					log.info("Record deleted successfully");
				}
			} else {
				
				log.warn("No records selected for deletion");
				ServletUtility.setErrorMessage("select at least one record to delete", request);
			}
		}
		
		if (OP_NEXT.equalsIgnoreCase(op)) {

			log.debug("Next page operation started");

			pageNo = DataUtility.getInt(request.getParameter("pageNo"));
			pageNo++;
		}
	
		if (OP_PREVIOUS.equalsIgnoreCase(op)) {

			log.debug("Previous page operation started");

			pageNo = DataUtility.getInt(request.getParameter("pageNo"));
			pageNo--;
		}
		
		if (OP_SEARCH.equalsIgnoreCase(op)) {

			log.debug("Search operation started");

			pageNo = 1;
		}
		
		List<B> list = model.search(bean, pageNo, pageSize);
		List<B> nextList = model.search(bean, pageNo + 1, pageSize);
		
//		request.setAttribute("nextList", nextList);
		request.setAttribute("nextList", nextList.size());
		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);

		log.info("List operation completed successfully");
		
		ServletUtility.forward(getView(), request, response);

		log.debug("doPost() method completed");
	}
}
