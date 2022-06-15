package com.tnas.moviesbattleapi;

import java.util.stream.Stream;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

/**
 * As senhas dos usuários cadastrados seguem o formato: username@username.
 * Por exemplo, a senha do usuário "teste" é "teste@teste".
 *  
 * @author Thiago Nascimento - nascimenthiago@gmail.com
 *
 */
@Configuration
public class SecurityConfiguration  {
	
	private static final String USER_ROLE = "USER";
	
	private static final String[][] registeredUsers = { 
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
    	
    	JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
    	
		Stream.of(registeredUsers).forEach(u -> {
			
			UserDetails user = User.builder()
					.username(u[0])
					.password(u[1])
					.roles(USER_ROLE)
					.build();
			users.createUser(user);
		});
        
        return users;
    }
}
