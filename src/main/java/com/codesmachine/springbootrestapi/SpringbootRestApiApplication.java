package com.codesmachine.springbootrestapi;

import com.codesmachine.springbootrestapi.domain.Role;
import com.codesmachine.springbootrestapi.domain.User;
import com.codesmachine.springbootrestapi.repositories.RoleRepository;
import com.codesmachine.springbootrestapi.repositories.UserRepository;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Spring boot Blog APIs",
				description = "Spring boot Blog APIs Documentation",
				version = "v1.0",
				contact = @Contact(
						name = "Gasana Theophile",
						email = "gasana141414@gmail.com",
						url = "hhttps://github.com/Gasana14"
				),
				license = @License(
						name = "Apache 2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Spring boot Blog External Documentation",
				url = "https://github.com/Gasana14"
		)
)
public class SpringbootRestApiApplication implements CommandLineRunner {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private boolean firstTimeFlag = false;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestApiApplication.class, args);
		System.out.println("App Running");
	}

	private void registerAdminUser(){
		Optional<User> adminUser = userRepository.findByUsernameOrEmail("admin","admin@gmail.com");
		if(adminUser.isEmpty()){
			// create admin user
			User newAdminUser = new User();
			newAdminUser.setName("Administrator");
			newAdminUser.setUsername("admin");
			newAdminUser.setPassword(passwordEncoder.encode("admin"));
			newAdminUser.setEmail("admin@gmail.com");
			userRepository.save(newAdminUser);
			firstTimeFlag = true;

		}else{
			firstTimeFlag = false;
		}
	}


	private void addRoles(){
		Optional<Role> adminRole = roleRepository.findByName("ROLE_ADMIN");
		if(adminRole.isEmpty()){
			Role newAdminRole = new Role();
			newAdminRole.setName("ROLE_ADMIN");
			roleRepository.save(newAdminRole);
		}

		Optional<Role> userRole = roleRepository.findByName("ROLE_USER");
		if(userRole.isEmpty()){
			Role newUserRole = new Role();
			newUserRole.setName("ROLE_USER");
			roleRepository.save(newUserRole);
		}

	}

	private void assigningAdminRoleToAdminUser(){

		// find Role by Admin Role
		Optional<Role> adminRole = roleRepository.findByName("ROLE_ADMIN");

		// find User by Admin
		Optional<User> userAdmin = userRepository.findByUsername("admin");

		if(!adminRole.isEmpty() && !userAdmin.isEmpty()){
			// Role Admin exist and User Admin Exist so we have to assign admin Role to admin User

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole.get());

			userAdmin.get().setRoles(roles);
			userRepository.save(userAdmin.get());
		}

	}

	@Override
	public void run(String... args) throws Exception {
			addRoles();
			registerAdminUser();
			if(firstTimeFlag == true){
				assigningAdminRoleToAdminUser();
			}

	}
}
