package dal.asd.catme.config;

import dal.asd.catme.dao.*;
import dal.asd.catme.service.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import dal.asd.catme.database.DatabaseAccess;

public class SystemConfig 
{
	private static SystemConfig uniqueInstance = null;
	private IAdminDao adminDao;
	private IAdminService adminServie;
	private ICourseDao courseDao;
	private ICourseService courseService;
	private IRoleDao roleDao;
	private IRoleService roleService;
	private IListDetailsDao listDetailsDao;
	private IListDetails listDetails;
	private IMailSenderService mailSenderService;
	private ICatmeDao catmeDao;
	private ICatmeService catmeService;
	private IStudentDao studentDao;
	private IUserDao userDao;
	private IUserService userService;
	private IPasswordResetService passwordResetService;
	private IEnrollStudentService enrollStudentService;
	private DatabaseAccess databaseAccess;
	private CatmeSecurityConfig catmeServiceConfig;
	private PasswordEncoder passwordEncoder;

	//question manager
	private IQuestionDao questionDao;
	private IListQuestionsService listQuestionsService;



	private IPasswordRulesConfig passwordEnforcementConfig;
	

	public SystemConfig()
	{
		passwordEncoder=new BCryptPasswordEncoder();
		catmeServiceConfig=new CatmeSecurityConfig();
		databaseAccess=new DatabaseAccess();
		adminDao=new AdminDao();
		adminServie=new AdminService();
		courseDao=new CourseDaoImpl();
		courseService=new CourseServiceImpl(courseDao);
		roleDao=new RoleDaoImpl();
		roleService=new RoleServiceImpl();
		listDetailsDao=new ListDetailsDao();
		listDetails=new ListDetailsService();
		mailSenderService=new MailSenderService();
		catmeDao=new CatmeDaoImpl();
		catmeService=new CatmeServiceImpl();
		studentDao=new StudentDaoImpl();
		userDao=new UserDaoImpl();
		userService=new UserServiceImpl();
		passwordResetService=new PasswordResetService(userDao);
		enrollStudentService=new EnrollStudentService(roleDao,studentDao);

		//question manager

		questionDao = new QuestionDaoImpl();
		listQuestionsService = new ListQuestionsServiceImpl(questionDao);
		passwordResetService= new PasswordResetService(userDao);
		enrollStudentService=new EnrollStudentService(roleDao,studentDao);
		passwordEnforcementConfig=new PasswordRulesConfigImpl();
	}
	
	public static SystemConfig instance()
	{
		// Using lazy initialization, this is the one and only place that the System
		// object will be instantiated.
		if (null == uniqueInstance)
		{
			uniqueInstance = new SystemConfig();
		}
		return uniqueInstance;
	}
	


	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public CatmeSecurityConfig getCatmeServiceConfig() {
		return catmeServiceConfig;
	}

	public void setCatmeServiceConfig(CatmeSecurityConfig catmeServiceConfig) {
		this.catmeServiceConfig = catmeServiceConfig;
	}

	public DatabaseAccess getDatabaseAccess() {
		return databaseAccess;
	}

	public void setDatabaseAccess(DatabaseAccess databaseAccess) {
		this.databaseAccess = databaseAccess;
	}

	public static SystemConfig getUniqueInstance() {
		return uniqueInstance;
	}

	public static void setUniqueInstance(SystemConfig uniqueInstance) {
		SystemConfig.uniqueInstance = uniqueInstance;
	}

	public IAdminDao getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(IAdminDao adminDao) {
		this.adminDao = adminDao;
	}

	public IAdminService getAdminServie() {
		return adminServie;
	}

	public void setAdminServie(IAdminService adminServie) {
		this.adminServie = adminServie;
	}

	public ICourseDao getCourseDao() {
		return courseDao;
	}

	public void setCourseDao(ICourseDao courseDao) {
		this.courseDao = courseDao;
	}

	public ICourseService getCourseService() {
		return courseService;
	}

	public void setCourseService(ICourseService courseService) {
		this.courseService = courseService;
	}

	public IRoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public IRoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}

	public IListDetailsDao getListDetailsDao() {
		return listDetailsDao;
	}

	public void setListDetailsDao(IListDetailsDao listDetailsDao) {
		this.listDetailsDao = listDetailsDao;
	}

	public IListDetails getListDetails() {
		return listDetails;
	}

	public void setListDetails(IListDetails listDetails) {
		this.listDetails = listDetails;
	}

	public IMailSenderService getMailSenderService() {
		return mailSenderService;
	}

	public void setMailSenderService(IMailSenderService mailSenderService) {
		this.mailSenderService = mailSenderService;
	}

	public ICatmeDao getCatmeDao() {
		return catmeDao;
	}

	public void setCatmeDao(ICatmeDao catmeDao) {
		this.catmeDao = catmeDao;
	}

	public ICatmeService getCatmeService() {
		return catmeService;
	}

	public void setCatmeService(ICatmeService catmeService) {
		this.catmeService = catmeService;
	}

	public IStudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(IStudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public IPasswordResetService getPasswordResetService() {
		return passwordResetService;
	}

	public void setPasswordResetService(IPasswordResetService passwordResetService) {
		this.passwordResetService = passwordResetService;
	}

	public IEnrollStudentService getEnrollStudentService() {
		return enrollStudentService;
	}

	public void setEnrollStudentService(IEnrollStudentService enrollStudentService) {
		this.enrollStudentService = enrollStudentService;
	}


	//question manager
	public IListQuestionsService getListQuestionsService()
	{
		return listQuestionsService;
	}

	public void setListQuestionsService(IListQuestionsService listQuestionsService)
	{
		this.listQuestionsService = listQuestionsService;
	}
	public IPasswordRulesConfig getPasswordEnforcementConfig() {
		return passwordEnforcementConfig;
	}

	public void setPasswordEnforcementConfig(IPasswordRulesConfig passwordEnforcementConfig) {
		this.passwordEnforcementConfig = passwordEnforcementConfig;
	}


	
}
