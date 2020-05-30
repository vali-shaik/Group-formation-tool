package dal.asd.catme;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertEquals;

import dal.asd.catme.beans.Course;
import dal.asd.catme.mock.AdminServiceMock;
import dal.asd.catme.service.IAdminService;
import dal.asd.catme.util.CatmeUtil;

public class AdminServiceTest {

	IAdminService mockService = new AdminServiceMock();
	
	@Test
	public void getAllCoursesTest() {
		List<Course> testData = formCourseList();
		
		assertThat(testData, samePropertyValuesAs((mockService.getAllCourses())));
	 
	}
	
	@Test
	public void deleteCourseTest(){
		assertEquals(1,mockService.deleteCourse("5308"));
	}
	
	private List<Course> formCourseList(){
		List<Course> courses = new ArrayList<Course>();
		Course course = new Course(CatmeUtil.ASDC_ID,CatmeUtil.ASDC);
		courses.add(course);
		return courses;
	}
}
