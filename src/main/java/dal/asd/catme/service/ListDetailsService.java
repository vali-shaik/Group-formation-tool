package dal.asd.catme.service;

import java.util.List;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.User;
import dal.asd.catme.config.SystemConfig;
import dal.asd.catme.dao.IListDetailsDao;


public class ListDetailsService implements IListDetails{
	IListDetailsDao listDetails;


	@Override
	public List<User> getUsers(Course course) {
		listDetails=SystemConfig.instance().getListDetailsDao();
		return listDetails.getUsers(course);

	}

	@Override
	public List<Course> getAllCourses() {
		listDetails=SystemConfig.instance().getListDetailsDao();
		return listDetails.getAllCourses();
	}

}
