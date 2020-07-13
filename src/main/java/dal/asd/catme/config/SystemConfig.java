package dal.asd.catme.config;

import dal.asd.catme.accesscontrol.*;
import dal.asd.catme.courses.*;
import dal.asd.catme.database.DatabaseAccess;
import dal.asd.catme.password.IPasswordDao;
import dal.asd.catme.password.IPasswordPolicyCheckerService;
import dal.asd.catme.password.IPasswordResetService;
import dal.asd.catme.password.IPasswordRulesConfig;
import dal.asd.catme.password.PasswordDaoImpl;
import dal.asd.catme.password.PasswordPolicyCheckerImpl;
import dal.asd.catme.password.PasswordResetService;
import dal.asd.catme.password.PasswordRulesConfigImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SystemConfig
{
    private static SystemConfig uniqueInstance = null;
    private DatabaseAccess databaseAccess;
    private CatmeSecurityConfig catmeServiceConfig;
    private PasswordEncoder passwordEncoder;

    public SystemConfig()
    {
        passwordEncoder = new BCryptPasswordEncoder();
        catmeServiceConfig = new CatmeSecurityConfig();
        databaseAccess = new DatabaseAccess();
    }

    public static SystemConfig instance()
    {
        if (null == uniqueInstance)
        {
            uniqueInstance = new SystemConfig();
        }
        return uniqueInstance;
    }

    public PasswordEncoder getPasswordEncoder()
    {
        return passwordEncoder;
    }

    public CatmeSecurityConfig getCatmeServiceConfig()
    {
        return catmeServiceConfig;
    }

    public DatabaseAccess getDatabaseAccess()
    {
        return databaseAccess;
    }
}
