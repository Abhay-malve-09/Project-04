package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;

import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.bean.TimeTableBean;
import in.co.rays.proj4.model.SubjectModel;
import in.co.rays.proj4.model.TimeTableModel;
import in.co.rays.proj4.model.UserModel;

public class TestTimeTableModel {

	public static void main(String[] args) {

		testAdd();
		testDelete();

	}

	public static void testAdd() {

		TimeTableBean t = new TimeTableBean();
		TimeTableModel model = new TimeTableModel();

		t.setSemester(null);
		t.setDescription(null);
		t.setExam_date(null);
		t.setExam_time(null);
		t.setCourse_id(0);
		t.setCourse_name(null);
		t.setSubject_id(0);
		t.setSubject_name(null);
		t.setCreatedBy("Abhay");
		t.setModifiedBy("Abhay");
		t.setCreatedDatetime(new Timestamp(new Date(0).getTime()));
		t.setModifiedDatetime(new Timestamp(new Date(0).getTime()));

		model.add(t);
	}

	public static void testDelete() {

		TimeTableModel model = new TimeTableModel();

		model.delete(0);
	}

}
