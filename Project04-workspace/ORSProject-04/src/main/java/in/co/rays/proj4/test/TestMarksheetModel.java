package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.model.MarksheetModel;
import in.co.rays.proj4.model.TimeTableModel;

public class TestMarksheetModel {

	public static void main(String[] args) {

		testAdd();
		testDelete();

	}

	public static void testAdd() {

		MarksheetBean m = new MarksheetBean();
		MarksheetModel model = new MarksheetModel();

		m.setRoll_no("101");
		m.setStudent_id(2);
		m.setName("Shriyansh");
		m.setPhysics(87);
		m.setChemistry(86);
		m.setMaths(75);
		m.setCreatedBy("Abhay");
		m.setModifiedBy("Abhay");
		m.setCreatedDatetime(new Timestamp(new Date(0).getTime()));
		m.setModifiedDatetime(new Timestamp(new Date(0).getTime()));

		model.add(m);
	}
	
	public static void testDelete() {

		MarksheetModel model = new MarksheetModel();

		model.delete(0);
	}
}
