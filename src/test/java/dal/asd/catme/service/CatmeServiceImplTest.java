package dal.asd.catme.service;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import dal.asd.catme.beans.Course;
import dal.asd.catme.dao.CatmeDaoImpl;

//@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CatmeServiceImplTest {
	
	@Mock
	CatmeDaoImpl catmeDaoMock;
	@InjectMocks
	CatmeServiceImpl catmeServiceMock;
	@Test
	public void testGetAllCourses()
	{
		Course course=new Course();
		course.setCourseId("5309");
		course.setCourseName("Advanced Web Services");
		List<Course> courseList=new ArrayList<>();
		courseList.add(course);
		when(catmeDaoMock.getAllCourses()).thenReturn(courseList);
		assertEquals("5309",catmeServiceMock.getAllCourses().get(0).getCourseId());
		
	}
	
	@Test
	public void testGetAllCoursesBySize()
	{
		List<Course> courseList=new ArrayList<>();
		int i=0;
		while(i<5)
		{
			Course course=new Course();
			course.setCourseId("5309");
			course.setCourseName("Advanced Web Services");
			courseList.add(course);
			i++;
		}
		when(catmeDaoMock.getAllCourses()).thenReturn(courseList);
		assertEquals(5,catmeServiceMock.getAllCourses().size());
		
	}
	

}
