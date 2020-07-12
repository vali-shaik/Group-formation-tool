package dal.asd.catme.courses;

public class CourseAbstractFactoryImpl implements ICourseAbstractFactory
{
    private static ICourseAbstractFactory courseAbstractFactory = null;

    private ICourseDao courseDao;
    private ICourseService courseService;
    private IRoleDao roleDao;
    private IRoleService roleService;
    private IStudentDao studentDao;
    private IEnrollStudentService enrollStudentService;

    public CourseAbstractFactoryImpl()
    {
        courseDao = new CourseDaoImpl();
        roleDao = new RoleDaoImpl();
        studentDao = new StudentDaoImpl();

        courseService = new CourseServiceImpl(courseDao);
        roleService = new RoleServiceImpl(roleDao);
        enrollStudentService = new EnrollStudentService(roleDao,studentDao);
    }

    public static ICourseAbstractFactory instance()
    {
        if(courseAbstractFactory==null)
        {
            courseAbstractFactory = new CourseAbstractFactoryImpl();
        }
        return courseAbstractFactory;
    }

    @Override
    public ICourseService getCourseService()
    {
        return courseService;
    }

    @Override
    public IEnrollStudentService getEnrollmentService()
    {
        return enrollStudentService;
    }

    @Override
    public IRoleService getRoleService()
    {
        return roleService;
    }

    @Override
    public ICourseDao getCourseDao()
    {
        return courseDao;
    }

    @Override
    public IStudentDao getStudentDao()
    {
        return studentDao;
    }

    @Override
    public IRoleDao getRoleDao()
    {
        return roleDao;
    }
}
