package dal.asd.catme.courses;

import dal.asd.catme.accesscontrol.IRoleDao;
import dal.asd.catme.accesscontrol.IStudentDao;

public interface ICourseAbstractFactory
{
    ICourseService createCourseService(ICourseDao courseDao);
    IEnrollStudentService createEnrollmentService(IRoleDao roleDao, IStudentDao studentDao);
    IRoleService createRoleService(IRoleDao roleDao);

    ICourseDao createCourseDao();
    IStudentDao createStudentDao();
    IRoleDao createRoleDao();
}
