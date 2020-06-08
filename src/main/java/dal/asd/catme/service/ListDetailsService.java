package dal.asd.catme.service;

import java.util.List;

import org.springframework.stereotype.Component;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.User;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.dao.IListDetailsDao;

@Component
public class ListDetailsService implements IListDetails{
	IListDetailsDao listDetails;
	
	public List<Course> getAllCourses() {
		listDetails=SystemConfig.instance().getListDetailsDao();
		return listDetails.getAllCourses();
		
	}

	@Override
	public List<User> getUsers(Course course) {
		listDetails=SystemConfig.instance().getListDetailsDao();
		return listDetails.getUsers(course);
		
	}

}
