package com.tnas.moviesbattleapi.config;

import java.util.stream.Stream;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration  {
	
	public static final String USER_ROLE = "USER";
	private static final Integer USERNAME_REGISTER = 0;
	private static final Integer PASSWORD_REGISTER = 1;
	
	private static final String[][] defaultUsersData = { 
			{ "shiva", "{bcrypt}$2a$10$uaAnEplLKKtFqf1wj4YX.eW5aSEFJ9ugVKpD6hoExgIlBvZHO3lhm" }, 
			{ "brahma", "{bcrypt}$2a$10$BLQzG6MzB/TEXMXUpZf6Fu3ywPzcgOCHwEpcLkHDXGT6YdriSqeqm"},
			{ "vixenu", "{bcrypt}$2a$10$Odljo1DgweoS4pwSHYNTpe.aA8qkYdrxqte02NVSIN2QXEZ3zF5kO"}, 
			{ "krishna", "{bcrypt}$2a$10$q10YmsTFiKsTIuJIdbA1X.x/JMmix/cWRma6J8Uk6OgcttUJGdi9e"}, 
			{ "devi", "{bcrypt}$2a$10$1ivNWVglyystzwBgDAz3.O8uC8E5Mbypc3owB0yu3zZ8YJ0XQyp9q" }
	};
	
	@Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
            .build();
    }
	
    @Bean
    public UserDetailsManager users(DataSource dataSource) {
    	JdbcUserDetailsManager usersManager = new JdbcUserDetailsManager(dataSource);
    	this.loadDefaultUsers(usersManager);
        return usersManager;
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        	.antMatchers("/match/**")
        	.authenticated()
        	.and()
        	.authorizeRequests()
            .antMatchers("/signup")
            .permitAll()
            .and().csrf().disable()
            .httpBasic();
        return http.build();
    }
    
    private void loadDefaultUsers(UserDetailsManager usersManager) {
    	Stream.of(defaultUsersData)
    		.map(register -> User.builder()
    			.username(register[USERNAME_REGISTER])
    			.password(register[PASSWORD_REGISTER])
    			.roles(USER_ROLE)
    			.build())
    		.forEach(usersManager::createUser); 
    }
}
