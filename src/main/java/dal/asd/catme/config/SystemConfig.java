package dal.asd.catme.config;

import dal.asd.catme.accesscontrol.AdminDaoImpl;
import dal.asd.catme.accesscontrol.AdminServiceImpl;
import dal.asd.catme.accesscontrol.IAdminDao;
import dal.asd.catme.accesscontrol.IAdminService;
import dal.asd.catme.accesscontrol.IListUserDao;
import dal.asd.catme.accesscontrol.IListUserService;
import dal.asd.catme.accesscontrol.IMailSenderService;
import dal.asd.catme.accesscontrol.IRoleDao;
import dal.asd.catme.accesscontrol.IStudentDao;
import dal.asd.catme.accesscontrol.IUserDao;
import dal.asd.catme.accesscontrol.IUserService;
import dal.asd.catme.accesscontrol.ListDetailsDaoImpl;
import dal.asd.catme.accesscontrol.ListDetailsServiceImpl;
import dal.asd.catme.accesscontrol.MailSenderServiceImpl;
import dal.asd.catme.accesscontrol.UserDaoImpl;
import dal.asd.catme.accesscontrol.UserServiceImpl;
import dal.asd.catme.courses.CourseDaoImpl;
import dal.asd.catme.courses.CourseServiceImpl;
import dal.asd.catme.courses.EnrollStudentService;
import dal.asd.catme.courses.ICourseDao;
import dal.asd.catme.courses.ICourseService;
import dal.asd.catme.courses.IEnrollStudentService;
import dal.asd.catme.courses.IListCourseDao;
import dal.asd.catme.courses.IListCourseService;
import dal.asd.catme.courses.IRoleService;
import dal.asd.catme.courses.RoleDaoImpl;
import dal.asd.catme.courses.RoleServiceImpl;
import dal.asd.catme.courses.StudentDaoImpl;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.password.IPasswordDao;
import dal.asd.catme.password.IPasswordPolicyCheckerService;
import dal.asd.catme.password.IPasswordResetService;
import dal.asd.catme.password.IPasswordRulesConfig;
import dal.asd.catme.password.PasswordDaoImpl;
import dal.asd.catme.password.PasswordPolicyCheckerImpl;
import dal.asd.catme.password.PasswordResetService;
import dal.asd.catme.password.PasswordRulesConfigImpl;
import dal.asd.catme.questionmanager.IListQuestionsService;
import dal.asd.catme.questionmanager.IQuestionDao;
import dal.asd.catme.questionmanager.IQuestionManagerService;
import dal.asd.catme.questionmanager.ListQuestionsServiceImpl;
import dal.asd.catme.questionmanager.QuestionDaoImpl;
import dal.asd.catme.questionmanager.QuestionManagerServiceImpl;
import dal.asd.catme.studentlistimport.CSVReader;
import dal.asd.catme.studentlistimport.ICSVReader;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SystemConfig
{
    private static SystemConfig uniqueInstance = null;
    private IAdminDao adminDao;
    private IAdminService adminServie;
    private ICourseDao courseDao;
    private ICourseService courseService;
    private IRoleDao roleDao;
    private IRoleService roleService;
    private IMailSenderService mailSenderService;
    private IStudentDao studentDao;
    private IUserDao userDao;
    private IPasswordDao passwordDao;
    private IUserService userService;
    private IPasswordResetService passwordResetService;
    private IEnrollStudentService enrollStudentService;
    private DatabaseAccess databaseAccess;
    private CatmeSecurityConfig catmeServiceConfig;
    private PasswordEncoder passwordEncoder;
    private ICSVReader csvReaderImpl;
    private IListCourseDao listCourseDao;
    private IListUserDao listUserDao;
    private IListCourseService listCourseService;
    private IListUserService listUserService;
    private IQuestionDao questionDao;
    private IListQuestionsService listQuestionsService;
    private IQuestionManagerService questionManagerService;
    private IPasswordRulesConfig passwordEnforcementConfig;
    private IPasswordPolicyCheckerService passwordPolicyCheckerService;

    public SystemConfig()
    {
        questionManagerService = new QuestionManagerServiceImpl();
        passwordEncoder = new BCryptPasswordEncoder();
        catmeServiceConfig = new CatmeSecurityConfig();
        databaseAccess = new DatabaseAccess();
        adminDao = new AdminDaoImpl();
        adminServie = new AdminServiceImpl(adminDao);
        courseDao = new CourseDaoImpl();
        courseService = new CourseServiceImpl(courseDao);
        roleDao = new RoleDaoImpl();
        roleService = new RoleServiceImpl(roleDao);
        listCourseDao = new ListDetailsDaoImpl();
        listUserService = new ListDetailsServiceImpl(listUserDao,listCourseDao);
        listCourseService = new ListDetailsServiceImpl(listUserDao,listCourseDao);
        mailSenderService = new MailSenderServiceImpl();
        studentDao = new StudentDaoImpl();
        userDao = new UserDaoImpl();
        passwordDao = new PasswordDaoImpl();
        userService = new UserServiceImpl(userDao,passwordPolicyCheckerService);
        passwordResetService = new PasswordResetService(userDao, passwordDao);
        enrollStudentService = new EnrollStudentService(roleDao, studentDao);
        questionDao = new QuestionDaoImpl();
        listQuestionsService = new ListQuestionsServiceImpl(questionDao);
        enrollStudentService = new EnrollStudentService(roleDao, studentDao);
        passwordEnforcementConfig = new PasswordRulesConfigImpl();
        passwordPolicyCheckerService = new PasswordPolicyCheckerImpl();
        csvReaderImpl = new CSVReader();
    }

    public static SystemConfig instance()
    {
        if (null == uniqueInstance)
        {
            uniqueInstance = new SystemConfig();
        }
        return uniqueInstance;
    }

    public IQuestionManagerService getQuestionManagerService()
    {
        return questionManagerService;
    }

    public void setQuestionManagerService(IQuestionManagerService questionManagerService)
    {
        this.questionManagerService = questionManagerService;
    }

    public ICSVReader getCsvReaderImpl()
    {
        return csvReaderImpl;
    }

    public void setCsvReaderImpl(ICSVReader csvReaderImpl)
    {
        this.csvReaderImpl = csvReaderImpl;
    }

    public PasswordEncoder getPasswordEncoder()
    {
        return passwordEncoder;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder)
    {
        this.passwordEncoder = passwordEncoder;
    }

    public CatmeSecurityConfig getCatmeServiceConfig()
    {
        return catmeServiceConfig;
    }

    public void setCatmeServiceConfig(CatmeSecurityConfig catmeServiceConfig)
    {
        this.catmeServiceConfig = catmeServiceConfig;
    }

    public DatabaseAccess getDatabaseAccess()
    {
        return databaseAccess;
    }

    public void setDatabaseAccess(DatabaseAccess databaseAccess)
    {
        this.databaseAccess = databaseAccess;
    }

    public static SystemConfig getUniqueInstance()
    {
        return uniqueInstance;
    }

    public static void setUniqueInstance(SystemConfig uniqueInstance)
    {
        SystemConfig.uniqueInstance = uniqueInstance;
    }

    public IAdminDao getAdminDao()
    {
        return adminDao;
    }

    public void setAdminDao(IAdminDao adminDao)
    {
        this.adminDao = adminDao;
    }

    public IAdminService getAdminServie()
    {
        return adminServie;
    }

    public void setAdminServie(IAdminService adminServie)
    {
        this.adminServie = adminServie;
    }

    public IListCourseDao getListCourseDao()
    {
        return listCourseDao;
    }

    public void setListCourseDao(IListCourseDao listCourseDao)
    {
        this.listCourseDao = listCourseDao;
    }

    public IListUserDao getListUserDao()
    {
        return listUserDao;
    }

    public void setListUserDao(IListUserDao listUserDao)
    {
        this.listUserDao = listUserDao;
    }

    public IListCourseService getListCourseService()
    {
        return listCourseService;
    }

    public void setListCourseService(IListCourseService listCourseService)
    {
        this.listCourseService = listCourseService;
    }

    public IListUserService getListUserService()
    {
        return listUserService;
    }

    public void setListUserService(IListUserService listUserService)
    {
        this.listUserService = listUserService;
    }

    public ICourseDao getCourseDao()
    {
        return courseDao;
    }

    public void setCourseDao(ICourseDao courseDao)
    {
        this.courseDao = courseDao;
    }

    public ICourseService getCourseService()
    {
        return courseService;
    }

    public void setCourseService(ICourseService courseService)
    {
        this.courseService = courseService;
    }

    public IRoleDao getRoleDao()
    {
        return roleDao;
    }

    public void setRoleDao(IRoleDao roleDao)
    {
        this.roleDao = roleDao;
    }

    public IRoleService getRoleService()
    {
        return roleService;
    }

    public void setRoleService(IRoleService roleService)
    {
        this.roleService = roleService;
    }

    public IMailSenderService getMailSenderService()
    {
        return mailSenderService;
    }

    public void setMailSenderService(IMailSenderService mailSenderService)
    {
        this.mailSenderService = mailSenderService;
    }

    public IStudentDao getStudentDao()
    {
        return studentDao;
    }

    public void setStudentDao(IStudentDao studentDao)
    {
        this.studentDao = studentDao;
    }

    public IUserDao getUserDao()
    {
        return userDao;
    }

    public void setUserDao(IUserDao userDao)
    {
        this.userDao = userDao;
    }

    public IPasswordDao getPasswordDao()
    {
        return passwordDao;
    }

    public void setPasswordDao(IPasswordDao passwordDao)
    {
        this.passwordDao = passwordDao;
    }

    public IUserService getUserService()
    {
        return userService;
    }

    public void setUserService(IUserService userService)
    {
        this.userService = userService;
    }

    public IPasswordResetService getPasswordResetService()
    {
        return passwordResetService;
    }

    public void setPasswordResetService(IPasswordResetService passwordResetService)
    {
        this.passwordResetService = passwordResetService;
    }

    public IEnrollStudentService getEnrollStudentService()
    {
        return enrollStudentService;
    }

    public void setEnrollStudentService(IEnrollStudentService enrollStudentService)
    {
        this.enrollStudentService = enrollStudentService;
    }

    public IListQuestionsService getListQuestionsService()
    {
        return listQuestionsService;
    }

    public void setListQuestionsService(IListQuestionsService listQuestionsService)
    {
        this.listQuestionsService = listQuestionsService;
    }

    public IPasswordRulesConfig getPasswordEnforcementConfig()
    {
        return passwordEnforcementConfig;
    }

    public void setPasswordEnforcementConfig(IPasswordRulesConfig passwordEnforcementConfig)
    {
        this.passwordEnforcementConfig = passwordEnforcementConfig;
    }

    public IQuestionDao getQuestionDao()
    {
        return questionDao;
    }

    public void setQuestionDao(IQuestionDao questionDao)
    {
        this.questionDao = questionDao;
    }

    public IPasswordPolicyCheckerService getPasswordPolicyCheckerService()
    {
        return passwordPolicyCheckerService;
    }

    public void setPasswordPolicyCheckerService(IPasswordPolicyCheckerService passwordPolicyCheckerService)
    {
        this.passwordPolicyCheckerService = passwordPolicyCheckerService;
    }
}
