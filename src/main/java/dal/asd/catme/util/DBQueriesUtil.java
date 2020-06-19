
package dal.asd.catme.util;

public class DBQueriesUtil
{

    public static final String SELECT_USER_SECURITY_QUERY="select BannerId,password, enabled from User where BannerId=?";
    //public static final String SELECT_USER_SECURITY_QUERY="select BannerId,password from User where BannerId=?";
    public static final String SELECT_ROLE_SECURITY_QUERY="select UR.BannerId, R.RoleName from UserRole UR,Role R where BannerId=? and UR.RoleId=R.RoleId";
    //SQL queries
    public static final String SELECT_GUEST_COURSES_QUERY = "Select * from Course";
    public static final String SELECT_INSTRUTOR_COURSES_QUERY = "SELECT c.CourseId,c.CourseName FROM Course c,CourseInstructor ci,UserRole ur where c.CourseId=ci.CourseId and ci.UserRoleId=ur.UserRoleId and ur.BannerId=";
    public static final String SELECT_COURSE_ROLE_QUERY ="SELECT r.RoleName FROM Course c,CourseInstructor ci,UserRole ur,Role r where c.CourseId=ci.CourseId and ci.UserRoleId=ur.UserRoleId and r.RoleId=ur.RoleId and ur.BannerId=";
    public static final String SELECT_COURSE_QUERY = "Select * from Course where courseId=";
    public static final String SELECT_STUDENT_COURSES_QUERY = "SELECT c.CourseId,c.CourseName FROM Course c,Enrollment e where c.CourseId=e.CourseId and e.BannerId=";

    //Queries
    public static final String SELECT_COURSE = "Select * from Course;";
    public static final String SELECT_COURSE_INSTRUCTOR_BY_USER_ROLE_COURSEID="select * from CourseInstructor where userRoleId=? and courseId=?";
    public static final String DELETE_COURSE_INSTRUCTOR_QUERY = "delete from CourseInstructor where CourseId=?";
    public static final String DELETE_ENROLLMENT_QUERY = "delete from Enrollment where CourseId=?";
    public static final String DELETE_COURSE_QUERY = "delete from Course where CourseId=?";
    public static final String ADD_COURSE_QUERY="insert into Course values(?,?)";
    public static final String SELECT_ROLE_BY_ROLENAME="select * from Role where roleName=?";
    public static final String SELECT_USER_ROLE_BY_BANNERID="select * from UserRole where bannerId=? and RoleId=?";
    public static final String INSERT_INTO_USER_ROLE="insert into UserRole(RoleId,BannerId) values(?,?)";
    public static final String INSERT_INTO_COURSE_INSTRUCTOR="insert into CourseInstructor(CourseId,UserRoleId) values(?,?)";
    public static final String LIST_USER_QUERY="select * from User where BannerId not in (select BannerId from UserRole where RoleId in (select RoleId from Role where RoleName=?))";
    public static final String CHECK_INSTRUCTOR="select * from Course where CourseId=?";
    

    //CourseDaoImpl
    public static String SEELCT_ENROLLED_STUDENTS_QUERY = "select BannerId, FirstName, LastName from Enrollment join(`User`) using(BannerId) where CourseId=?";
    public static String CHECK_COURSE_QUERY = "SELECT EXISTS(SELECT * FROM Course WHERE CourseId  = ?);";
    public static String CHECK_COURSE_REGISTRATION_QUERY = "SELECT EXISTS(SELECT * FROM Enrollment WHERE BannerId = ? AND CourseId = ?);";

    //RoleDaoImpl
    public static String ASSIGN_ROLE_QUERY = "INSERT IGNORE INTO UserRole (BannerId, RoleId) VALUES ( ? ,? );";
    public static String INSERT_COURSE_INSTRUCTOR_QUERY = "INSERT IGNORE INTO CourseInstructor (CourseId, UserRoleId) VALUES (?,?);";
    public static String CHECK_COURSE_INSTRUCTOR_QUERY = "SELECT EXISTS(WITH temp AS ( SELECT ci.UserRoleId,ci.CourseId, ur.BannerId FROM CourseInstructor ci INNER JOIN UserRole ur ON ci.UserRoleId = ur.UserRoleId ) SELECT * FROM temp WHERE temp.BannerId = ? AND temp.CourseId = ?);";
    public static String CHECK_USER_ROLE = "SELECT EXISTS(SELECT * FROM UserRole WHERE BannerId = ? AND RoleId = ? );";
    public static String GET_USER_ROLEID_QUERY = "SELECT UserRoleId FROM UserRole WHERE BannerId = ? AND RoleId = ?;";

    //StudentDaoImpl
    public static String STUDENT_ENROLL_QUERY = "INSERT INTO Enrollment (BannerId, CourseId) VALUES(?,?);";

    //UserDaoImpl
    public static String CHECK_EXISTING_USER_QUERY = "SELECT EXISTS(SELECT * FROM User WHERE BannerId = ? );";
    public static String ADD_USER_QUERY = "INSERT IGNORE INTO User (BannerId, FirstName, LastName, EmailId, Password) VALUES ( ? , ? , ? , ? , ? );";
    public static String GET_USER_QUERY = "SELECT * FROM User WHERE BannerId=?";
    public static String RESET_PASSWORD_QUERY = "UPDATE `User` SET `Password`=? WHERE `BannerId`=?";
    public static String GENERATE_PASSWORD_RESET_TOKEN = "insert into PasswordResetTokens (BannerId ,Token ) values(?,?);";
    public static String READ_BANNERID_FROM_TOKEN = "select BannerId from PasswordResetTokens where Token =?;";
    public static String REMOVE_TOKEN = "delete from PasswordResetTokens where BannerId =?;";
    
    //QuestionTitle
    public static final String CHECK_QUESTION_TITLE="select * from QuestionTitle where QuestionTitleText=? and UserRoleId=?";
    public static final String INSERT_QUESTION_TITLE="insert into QuestionTitle(QuestionTitleText,UserRoleId) values(?,?)";
    
    //Question
    public static final String CHECK_QUESTION="select * from Question where QuestionTitleId=? and QuestionText=?";
    public static final String INSERT_QUESTION = "insert into Question(QuestionTitleId,QuestionText,QuestionType) values(?,?,?)";
    
    //QuestionOption
    public static final String CHECK_QUESTION_OPTION = "select * from QuestionOption where QuestionId=? and QuestionOption=?";
    public static final String INSERT_QUESTION_OPTION = "insert into QuestionOption(QuestionId,QuestionOption,OptionOrder) values(?,?,?)";

	public static String GET_QUESTIONS = "CALL GetQuestionsList(?)";
	
	//QuestionDaoImpl
    public static String CHECK_EXISTING_QUESTION_QUERY = "SELECT EXISTS(SELECT * FROM Question WHERE QuestionId = ? );";
    public static String DELETE_QUESTION_QUERY = "DELETE FROM Question WHERE QuestionId = ? ;";



}

