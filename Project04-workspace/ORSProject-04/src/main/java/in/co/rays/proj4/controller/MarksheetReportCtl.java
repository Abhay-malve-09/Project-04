
package in.co.rays.proj4.controller;

import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.model.MarksheetModel;
import jakarta.servlet.annotation.WebServlet;

/**
 * Report servlet that generates a Marksheet list report in PDF or DOC format.
 * Mapped to {@code /ctl/MarksheetReportCtl}; add {@code ?type=doc} for Word output.
 *
 * @author Abhay Malve
 * @version 1.0
 * @see BaseReportCtl
 */
@WebServlet("/ctl/MarksheetReportCtl")
public class MarksheetReportCtl extends BaseReportCtl<MarksheetBean> {

	private static final Logger log = Logger.getLogger(MarksheetReportCtl.class);

	/**
	 * Fetches all marksheets from the database.
	 *
	 * @return list of all {@link MarksheetBean} records
	 */
	public List<MarksheetBean> getList() {

		log.info("Fetching marksheet records for report");

		MarksheetModel model = new MarksheetModel();

		MarksheetBean bean = new MarksheetBean();
		List<MarksheetBean> marksheets = model.search(bean, 1, 0);

		log.info("Marksheet report records fetched successfully");
		log.debug("Total marksheet records: " + marksheets.size());

		return marksheets;
	}

	/**
	 * Returns the JRXML template path for the marksheet list report.
	 *
	 * @return {@link ORSView#MARKSHEET_REPORT_VIEW}
	 */
	public String getView() {

		log.debug("Returning marksheet report view");
		return ORSView.MARKSHEET_REPORT_VIEW;
	}

	/**
	 * Returns the ServletContext cache key for the compiled marksheet report.
	 *
	 * @return {@code "MARKSHEET_LIST_COMPILED_REPORT"}
	 */
	public String getCompiledReportKey() {

		log.debug("Returning compiled marksheet report cache key");
		return "MARKSHEET_LIST_COMPILED_REPORT";
	}

}
