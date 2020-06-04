package dal.asd.catme.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dal.asd.catme.beans.Course;
import dal.asd.catme.beans.Enrollment;
import dal.asd.catme.beans.Instructor;
import dal.asd.catme.beans.Role;
import dal.asd.catme.beans.TInstructor;
import dal.asd.catme.beans.User;
import dal.asd.catme.dao.ICourseDao;
import dal.asd.catme.dao.IRoleDao;
import dal.asd.catme.dao.IUserDao;

public class RoleDaoMock implements IRoleDao{
	
	IUserDao userDao;
	ICourseDao courseDao;
	ArrayList<User> users;
	ArrayList<Course> courses;
    Course c;

    public RoleDaoMock(ArrayList<User> users, Course c)
    {
        this.users = users;
        this.c = c;
        userDao = new UserDaoMock(users);

    }
    
    public RoleDaoMock(ArrayList<User> users, ArrayList<Course> courses)
    {
        this.users = users;
        this.courses = courses;
        this.c = courses.get(2);
		userDao = new UserDaoMock(users);
		courseDao = new CourseDaoMock(courses);
    }


	@Override
	public int assignRole(String bannerId, int roleId, Connection con) {
		// TODO Auto-generated method stub
		for(User u: users)
        {
            if(u.getBannerId().equalsIgnoreCase(bannerId))
            {
                Role l = new Role();
                l.setRoleId(String.valueOf(roleId));
                ArrayList<Role> roles = new ArrayList<>();
                roles.add(l);

                u.setRole(roles);
                return 1;
            }
        }
        return 0;
	}

	@Override
	public int addInstructor(String courseId, int userRoleId, Connection con) {
		// TODO Auto-generated method stub
		
        return 1;
	}

	@Override
	public String assignTa(Enrollment user, Connection con) {
		// TODO Auto-generated method stub
		if(0 != userDao.checkExistingUser(user.bannerId, con)){
			
			//If the course exists
			if (0 != courseDao.checkCourseExists(user.courseId, con)) {
				
				//If the user is not currently taking the course
				if(0 == courseDao.checkCourseRegistration(user.bannerId, user.courseId, con)){
					
					//If the user is not already registered as an instructor for the course
					if(0 == checkCourseInstructor(user.bannerId, user.courseId, con)){
						
						
						//Add the user to the CourseInstructor Table
						for(Course c: courses)
				        {
							if (c.getCourseId().equalsIgnoreCase(user.courseId)){
								List<TInstructor> tInstructors = c.gettInstructors();
								if (tInstructors == null) {
									tInstructors = new ArrayList<TInstructor>();
								}
								
								for(User u: users)
						        {
						            if(u.getBannerId().equalsIgnoreCase(user.bannerId)) {
						            	TInstructor t = new TInstructor();
										t.setBannerId(user.bannerId);
										t.setEmail(u.getEmail());
										t.setFirstName(u.getFirstName());
										t.setLastName(u.getLastName());

										return "Success";
										
										
						        	}
									System.out.println("User not found");
						        }
								System.out.println("No users");
							}
				        }
						System.out.println("Enrollemnt Does not exist");
					}
					System.out.println("Course Instructor not available");
				}
				System.out.println("Not Registered");
			}
			System.out.println("Course Does not exist");
		}
		System.out.println("User Does Not Exist");

		return null;
	}

	@Override
	public int checkCourseInstructor(String bannerId, String courseId, Connection con) {
		// TODO Auto-generated method stub
		for(Instructor i:c.getInstructors())
        {
            if(i.getBannerId().equalsIgnoreCase(bannerId))
                return 1;
        }
        return 0;

	}

	@Override
	public int checkUserRole(String bannerId, int roleId, Connection con) {
		// TODO Auto-generated method stub
		 for(User u: users)
	        {
	            if(u.getBannerId().equalsIgnoreCase(bannerId))
	            {
	                for(Role l:u.getRole())
	                {
	                    if(l.getRoleId().equals(String.valueOf(roleId)))
	                        return 1;
	                }
	            }
	        }
	        return 0;

	}

	@Override
	public int getUserRoleId(String bannerId, int roleId, Connection con) {
		// TODO Auto-generated method stub
		return 1;
	}

}
