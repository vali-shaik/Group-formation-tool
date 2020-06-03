package dal.asd.catme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.User;
import dal.asd.catme.dao.IListDetailsDao;

@Component
public class ListDetailsService implements IListDetails{
	@Autowired
	IListDetailsDao listDetails;
	
	public List<Course> getAllCourses() {
		return listDetails.getAllCourses();
		
	}

	@Override
	public List<User> getUsers(Course course) {
		
		return listDetails.getUsers(course);
		
	}

}
