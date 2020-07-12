package dal.asd.catme.courses;


public interface ICourseAbstractFactory
{
    ICourseService getCourseService();
    IEnrollStudentService getEnrollmentService();
    IRoleService getRoleService();

    ICourseDao getCourseDao();
    IStudentDao getStudentDao();
    IRoleDao getRoleDao();
}
