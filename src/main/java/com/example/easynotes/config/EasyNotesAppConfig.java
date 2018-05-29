package com.example.easynotes.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class EasyNotesAppConfig extends WebSecurityConfigurerAdapter {

	private final DataSource dataSource;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public EasyNotesAppConfig(@Qualifier("dataSource") DataSource dataSource,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.dataSource = dataSource;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select email,password,active from users where email=?")
				.authoritiesByUsernameQuery(
						"select u.email, r.role from users u inner join user_role ur on(u.user_id=ur.user_id) inner join role r on(ur.role_id=r.role_id) where u.email=?")
				.passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/api/notes", "/api/notes/{id}", "/api/notes/search").authenticated().and()
				.formLogin().loginPage("/login").usernameParameter("email").passwordParameter("password")
				.defaultSuccessUrl("/");

	}

}
