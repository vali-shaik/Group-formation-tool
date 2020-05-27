package dal.asd.catme.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CatmeSecurityConfig extends WebSecurityConfigurerAdapter{
	private static final Logger log = LoggerFactory.getLogger(CatmeSecurityConfig.class);
	
	//overrides the default security
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("CatmeSecurity.class");
		
	}

}