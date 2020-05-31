package dal.asd.catme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dal.asd.catme.beans.Course;
import dal.asd.catme.dao.ICatmeDao;

@Component
public class CatmeServiceImpl implements ICatmeService{

	@Autowired
	ICatmeDao catmeDao;
	@Override
	public List<Course> getAllCourses() {
		return catmeDao.getAllCourses();
	}

}
