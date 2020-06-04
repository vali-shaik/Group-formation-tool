package dal.asd.catme.dao;

import java.sql.Connection;
import java.util.ArrayList;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.User;
import dal.asd.catme.dao.ICourseDao;

public class CourseDaoMock implements ICourseDao{
	
	ArrayList<Course> courses;
	
	public CourseDaoMock() {
		
	}

    public CourseDaoMock(ArrayList<Course> courses)
    {
        this.courses = courses;
    }


	@Override
	public int checkCourseRegistration(String bannerId, String courseId, Connection con) {
		// TODO Auto-generated method stub
		for(Course c: courses)
        {
            if(c.getCourseId().equalsIgnoreCase(courseId))
            {
                for(User u: c.getStudents())
                {
                    if(u.getBannerId().equalsIgnoreCase(bannerId))
                    {
                        return 1;
                    }
                }
            }
        }
        return 0;
	}

	@Override
	public int checkCourseExists(String courseId, Connection con) {
		// TODO Auto-generated method stub
		for(Course c: courses)
        {
            if(c.getCourseId().equalsIgnoreCase(courseId))
                return 1;
        }
        return 0;
	}

}
