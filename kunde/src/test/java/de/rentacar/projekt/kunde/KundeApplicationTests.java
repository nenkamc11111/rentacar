package de.rentacar.projekt.kunde;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noFields;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;

@SpringBootTest
class KundeApplicationTests {

	@Test
	void contextLoads() {
	}
	
private JavaClasses importedClasses;
	
	@BeforeEach
	    public void setup() {
	        importedClasses = new ClassFileImporter()
	                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
	                .importPackages("com.springboot.testing.archunit");
	    }

	    @Test
	    void fieldInjectionNotUseAutowiredAnnotation() {
	        noFields()
	                .should().beAnnotatedWith(Autowired.class)
	                .check(importedClasses);
	    }

	    @Test
	    void controllerClassesShouldBeNamedXController() {
	        classes()
	                .that().resideInAPackage("..controller..")
	                .should().haveSimpleNameEndingWith("Controller")
	                .check(importedClasses);
	    }
	    
	    @Test
	    void repositoryClassesShouldHaveSpringRepositoryAnnotation() {
	        classes()
	                .that().resideInAPackage("..repository..")
	                .should().beAnnotatedWith(Repository.class)
	                .check(importedClasses);
	    }
	    @Test
	    void serviceClassesShouldHaveSpringServiceAnnotation() {
	        classes()
	                .that().resideInAPackage("..service..")
	                .should().beAnnotatedWith(Service.class)
	                .check(importedClasses);
	    }
	    
	    @Test
	    public void testGetAutoListSuccess() throws URISyntaxException 
	    {
	        RestTemplate restTemplate = new RestTemplate();
	         
	        URI uri = new URI("http://localhost:8081/auto");
	     
	        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
	         
	        //Verify request succeed
			assertEquals(200, result.getStatusCodeValue());

	    }
	

}
