package dal.asd.catme.beans;

import java.util.List;
import java.util.Map;

public class CourseIntructor {
	Map<String,Map<String,List<User>>> courseInstructor;
	
	//Key => course Id
	/* Value=> {
						Key=> role
						Value=> user1,user2,user3
	}
*/
}
