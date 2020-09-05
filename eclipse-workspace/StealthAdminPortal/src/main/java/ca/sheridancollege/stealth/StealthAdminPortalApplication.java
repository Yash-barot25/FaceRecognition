package ca.sheridancollege.stealth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class StealthAdminPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(StealthAdminPortalApplication.class, args);
	}

}
