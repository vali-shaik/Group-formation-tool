package dal.asd.catme.config;

import java.util.Collection;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import dal.asd.catme.util.CatmeUtil;

@Configuration
@EnableGlobalMethodSecurity(
		  prePostEnabled = true, 
		  securedEnabled = true, 
		  jsr250Enabled = true)
@EnableWebSecurity
public class CatmeSecurityConfig extends WebSecurityConfigurerAdapter{
	private static final Logger log = LoggerFactory.getLogger(CatmeSecurityConfig.class);
	@Autowired
	DataSource dataSource;
	//overrides the default security
	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		log.info("CatmeSecurity.class");
//		http
//		.csrf().disable();
//			.authorizeRequests()
//        			.antMatchers("/login","/register").permitAll()
//        			.anyRequest().authenticated()
//        .and()
//        	.formLogin()
////        	.loginPage("/login")//this url should match with action url in login.html
//            .loginProcessingUrl("/catme")//url to submit username and password
//            .successForwardUrl("/home")//landing page after successful login
//            .and()
//            .logout()
//            .logoutUrl("/logout")
//            .logoutSuccessUrl("/login")
//            .permitAll()
//            .and()
//            .csrf().disable();
	}
	 @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	 
	 @Autowired
	 protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		 auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery(
				"select BannerId,password, enabled from User where BannerId=?")
			.authoritiesByUsernameQuery(
				"select UR.BannerId, R.RoleName from UserRole UR,Role R where BannerId=? and UR.RoleId=R.RoleId");
		 log.info("Configuring authentication");
	 }
	 
	 
	//Method for displaying home page of application
		public String fetchRolesHomePage()
		{
			String page="";
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Collection<GrantedAuthority> currentRoles = (Collection<GrantedAuthority>) authentication.getAuthorities();
			for(GrantedAuthority s: currentRoles)
			{
				if(s.getAuthority().equalsIgnoreCase(CatmeUtil.TA_ROLE)||
						s.getAuthority().equalsIgnoreCase(CatmeUtil.GUEST_ROLE)||
						s.getAuthority().equalsIgnoreCase(CatmeUtil.STUDENT_ROLE)||
						s.getAuthority().equalsIgnoreCase(CatmeUtil.INSTRUCTOR_ROLE))
				{
					page= CatmeUtil.HOME_PAGE;
				}
			}
			return page;
		}
}