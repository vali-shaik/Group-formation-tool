package dal.asd.catme;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import dal.asd.catme.beans.Course;
import dal.asd.catme.mock.AdminServiceMock;
import dal.asd.catme.service.IAdminService;
import dal.asd.catme.util.CatmeUtil;

public class AdminServiceTest {

	@Test
	public void getAllCoursesTest() {
		List<Course> testData = formCourseList();
		IAdminService mockService = new AdminServiceMock();
		assertThat(testData, samePropertyValuesAs((mockService.getAllCourses())));
	 
	}
	
	
	private List<Course> formCourseList(){
		List<Course> courses = new ArrayList<Course>();
		Course course = new Course(CatmeUtil.ASDC_ID,CatmeUtil.ASDC);
		courses.add(course);
		return courses;
	}
}
