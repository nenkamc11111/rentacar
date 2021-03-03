package de.rentacar.projekt.reservierung;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;

import de.rentacar.projekt.reservierung.model.Mieten;
import net.minidev.json.writer.JsonReader;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


@Configuration
@SpringBootTest
class ReservierungApplicationTests {

    private static final Logger LOGGER = LogManager.getLogger(ReservierungApplication.class);

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
	    public void testGetMietenListSuccess() throws URISyntaxException 
	    {
	        RestTemplate restTemplate = new RestTemplate();
	         
	        URI uri = new URI("http://localhost:8083/mieten");
	     
	        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
	         
	        //Verify request succeed
			assertEquals(200, result.getStatusCodeValue());

	    }
	    
	    @Test
	    public void testGetPreisSuccess() throws URISyntaxException, JSONException 
	    {
	        RestTemplate restTemplate = new RestTemplate();
	         
	        URI uri = new URI("http://localhost:8082/auto/1");
	     
	        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
	        //Verify request succeed
			assertEquals(200, result.getStatusCodeValue());
			JSONObject obj = new JSONObject(result.getBody());
			double preis = obj.getInt("preis");
			//assertEquals(60, preis);
	        LOGGER.info("Response GET Preis AUTO 1: "+preis+"€");


	    }
	    @Test
	    public void testGetAutoByIDSuccess() throws URISyntaxException, JSONException 
	    {
	    	int id = 1000;
	        RestTemplate restTemplate = new RestTemplate();
	        
	        List<Integer> listAutArray = new ArrayList<Integer>();
	         
	        URI uri = new URI("http://localhost:8082/auto/");
	     
	        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
	        //Verify request succeed
			assertEquals(200, result.getStatusCodeValue());
			JSONArray obj = new JSONArray(result.getBody());
			
			for(int i=0; i < obj.length(); i++) {
				JSONObject listAuto = obj.getJSONObject(i);
				listAutArray.add(listAuto.getInt("idauto"));
			}
			if(listAutArray.contains(id)) {
				
				LOGGER.info("Response Youpi Youpi: - ");
			}else {
				LOGGER.info("Response Ne contiet pas 1:");
			}
			//double preis = obj.getInt("preis");
			//assertEquals(60, preis);
	        //LOGGER.info("Response GET Preis AUTO 1: "+preis+"€");


	    }
	    
	    @Test
	    public void testAutoUpdateSuccess() throws URISyntaxException 
	    {
	    try {
			String authUrl = "http://localhost:8082/auto/";
			
			JSONObject request = new JSONObject();
			request.put("idauto",1);
			request.put("frei", 1);
			

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<>(request.toString(), headers);
			
			RestTemplate rest = new RestTemplate(new HttpComponentsClientHttpRequestFactory());

			ResponseEntity<String> response = rest.exchange(authUrl, HttpMethod.PUT, entity, String.class);
			LOGGER.info("------------TEST---------"+response);
			assertEquals(200, response.getStatusCodeValue());
			
		} catch (Exception e) {

			System.out.print("ERROR-- "+e.getMessage());
		}
	    }
	    
	    /**
	     * Get a diff between two dates
	     * @param date1 the oldest date
	     * @param date2 the newest date
	     * @param timeUnit the unit in which you want the diff
	     * @return the diff value, in the provided unit
	     */
	    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	        long diffInMillies = date2.getTime() - date1.getTime();
	        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	    }
	    
	    @Test
	    public static void findDifference() {
	    	

	    	assertEquals(9, ChronoUnit.DAYS.between(LocalDate.parse("2021-02-10"),LocalDate.parse("2021-02-19")));

	    }
	    
		/*
		 * @Test public void testAddMietenMissingHeader() throws URISyntaxException {
		 * RestTemplate restTemplate = new RestTemplate(); final String baseUrl =
		 * "http://localhost:8083/mieten/"; URI uri = new URI(baseUrl); Mieten employee
		 * = new Mieten(0, 1, 1, new Date(2021, 03, 10), new Date(2021, 03, 19));
		 * 
		 * HttpHeaders headers = new HttpHeaders();
		 * 
		 * HttpEntity<Mieten> request = new HttpEntity<>(employee, headers);
		 * 
		 * try { restTemplate.postForEntity(uri, request, String.class); fail("Error");
		 * } catch(HttpClientErrorException ex) { //Verify bad request and missing
		 * header assertEquals(400, ex.getRawStatusCode()); assertEquals(true,
		 * ex.getResponseBodyAsString().contains("Missing request header")); } }
		 */
	/*
	 * @BeforeEach public void setup() { importedClasses = new ClassFileImporter()
	 * .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
	 * .importPackages("com.springboot.testing.archunit");
	 * 
	 * }
	 * 
	 * @Test void layeredArchitectureShouldBeRespected() { layeredArchitecture()
	 * .layer("Controller").definedBy("..controller..")
	 * .layer("Service").definedBy("..service..")
	 * .layer("Repository").definedBy("..repository..")
	 * .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
	 * .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
	 * .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service")
	 * .check(importedClasses); }
	 */

	@Test
	void contextLoads() {
	}
	
	/*
	 * @Test void updateCarAvaibility(){ try { String authUrl =
	 * "http://localhost:8082/auto";
	 * 
	 * JSONObject request = new JSONObject(); request.put("idauto",1);
	 * request.put("frei", 1);
	 * 
	 * 
	 * HttpHeaders headers = new HttpHeaders();
	 * headers.setContentType(MediaType.APPLICATION_JSON); HttpEntity<String> entity
	 * = new HttpEntity<>(request.toString(), headers); RestTemplate rest = new
	 * RestTemplate(new HttpComponentsClientHttpRequestFactory());
	 * ResponseEntity<String> response = rest.exchange(authUrl, HttpMethod.PUT,
	 * entity, String.class);
	 * 
	 * assertEquals(200, response.getStatusCodeValue());
	 * 
	 * } catch (Exception e) { System.out.print("ERROR-- "+e.getMessage()); } }
	 */

}
