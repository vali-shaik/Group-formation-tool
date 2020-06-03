
  package dal.asd.catme;
  
  import static org.junit.Assert.assertEquals;
  import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
  import org.mockito.InjectMocks;
  import org.mockito.Mock;
  import org.springframework.boot.test.context.SpringBootTest;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.User;
import dal.asd.catme.dao.AdminDao;
import dal.asd.catme.dao.ListDetailsDao;
import dal.asd.catme.service.AdminService;
import dal.asd.catme.service.ListDetailsService;
import dal.asd.catme.util.CatmeUtil;
  
  @SpringBootTest
  public class AdminServiceTest {
  
		@Mock
		AdminDao adminDaoMock;
		
		@Mock
		ListDetailsDao listDetailDaoMock;
	  
		@InjectMocks
		AdminService adminServiceMock;
		
		@InjectMocks
		ListDetailsService listDetailService;
		
		@Test
		public void getAllCoursesTest()
		{
			List<Course> courseList=formCourseList();
			when(listDetailDaoMock.getAllCourses()).thenReturn(courseList);
			assertEquals(courseList,listDetailService.getAllCourses());
			assertEquals(CatmeUtil.WEB_ID,listDetailService.getAllCourses().get(CatmeUtil.ZERO).getCourseId());
			assertEquals(CatmeUtil.ADVANCED_WEB_SERVICES,listDetailService.getAllCourses().get(CatmeUtil.ZERO).getCourseName());
		}
		
		@Test
		public void getUsersTest()
		{
			List<User> users = formUserList();
			Course course = new Course(CatmeUtil.WEB_ID, CatmeUtil.ADVANCED_WEB_SERVICES);
			when(listDetailDaoMock.getUsers(course)).thenReturn(users);
			assertEquals(users,listDetailService.getUsers(course));
			assertEquals(CatmeUtil.BANNER_ID,listDetailService.getUsers(course).get(CatmeUtil.ZERO).getBannerId());
			assertEquals(CatmeUtil.FIRST_NAME,listDetailService.getUsers(course).get(CatmeUtil.ZERO).getFirstName());
			
		}
		
		
 
	  @Test 
	  public void deleteCourseTest(){
		  int result=CatmeUtil.ONE;
		  when(adminDaoMock.deleteCourse(CatmeUtil.ADVANCED_WEB_SERVICES)).thenReturn(result);
		  assertEquals(CatmeUtil.ONE,adminServiceMock.deleteCourse(CatmeUtil.ADVANCED_WEB_SERVICES)); 
	  }
	  
	  @Test
	  public void addCourseTest(){
		  Course course = new Course(CatmeUtil.WEB_ID, CatmeUtil.ADVANCED_WEB_SERVICES);
		  int result=CatmeUtil.ONE;
		  when(adminDaoMock.addCourse(course)).thenReturn(result);
		  assertEquals(CatmeUtil.ONE,adminServiceMock.addCourse(course)); 
		  
	  }
	  
	  @Test
	  public void addInstructorToCourseTest(){
		  int result=CatmeUtil.ONE;
		  when(adminDaoMock.addInstructorToCourse(CatmeUtil.FIRST_NAME, CatmeUtil.WEB_ID)).thenReturn(result);
		  assertEquals(CatmeUtil.ONE,adminServiceMock.addInstructorToCourse(CatmeUtil.FIRST_NAME, CatmeUtil.WEB_ID)); 
		  
	  }
	  
	  
	  
	  private List<Course> formCourseList(){ 
		  List<Course> courseList=new ArrayList<>();
			courseList.add(new Course(CatmeUtil.WEB_ID, CatmeUtil.ADVANCED_WEB_SERVICES));
			return courseList;
	  }
	  
	  private List<User> formUserList(){ 
		  List<User> users=new ArrayList<>();
		  users.add(new User(CatmeUtil.BANNER_ID,CatmeUtil.LAST_NAME,CatmeUtil.FIRST_NAME));
		  return users;
	  }
  }
 