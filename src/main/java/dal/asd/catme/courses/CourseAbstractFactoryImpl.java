package dal.asd.catme.courses;

public class CourseAbstractFactoryImpl implements ICourseAbstractFactory
{
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
        enrollStudentService = new EnrollStudentServiceImpl(roleDao,studentDao);
    }

    @Override
    public ICourseService makeCourseService()
    {
        return courseService;
    }

    @Override
    public IEnrollStudentService makeEnrollmentService()
    {
        return enrollStudentService;
    }

    @Override
    public IRoleService makeRoleService()
    {
        return roleService;
    }

    @Override
    public ICourseDao makeCourseDao()
    {
        return courseDao;
    }

    @Override
    public IStudentDao makeStudentDao()
    {
        return studentDao;
    }

    @Override
    public IRoleDao makeRoleDao()
    {
        return roleDao;
    }
}
