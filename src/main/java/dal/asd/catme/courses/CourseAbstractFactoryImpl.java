package dal.asd.catme.courses;

import dal.asd.catme.accesscontrol.IRoleDao;
import dal.asd.catme.accesscontrol.IStudentDao;

public class CourseAbstractFactoryImpl implements ICourseAbstractFactory
{
    private static ICourseAbstractFactory courseAbstractFactory = null;
    public static ICourseAbstractFactory instance()
    {
        if(courseAbstractFactory==null)
        {
            courseAbstractFactory = new CourseAbstractFactoryImpl();
        }
        return courseAbstractFactory;
    }

    @Override
    public ICourseService createCourseService(ICourseDao courseDao)
    {
        return new CourseServiceImpl(courseDao);
    }

    @Override
    public IEnrollStudentService createEnrollmentService(IRoleDao roleDao, IStudentDao studentDao)
    {
        return new EnrollStudentService(roleDao,studentDao);
    }

    @Override
    public IRoleService createRoleService(IRoleDao roleDao)
    {
        return new RoleServiceImpl(roleDao);
    }

    @Override
    public ICourseDao createCourseDao()
    {
        return new CourseDaoImpl();
    }

    @Override
    public IStudentDao createStudentDao()
    {
        return new StudentDaoImpl();
    }

    @Override
    public IRoleDao createRoleDao()
    {
        return new RoleDaoImpl();
    }
}
