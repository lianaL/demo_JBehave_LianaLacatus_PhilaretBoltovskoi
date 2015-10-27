package steps;

import static org.junit.Assert.*;
import org.jbehave.core.annotations.*;

import domain.Person;

public class CreateUserSteps {
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private Person person;
	
	protected Person createPerson() {
		return new Person(email, password, firstname, lastname);
	}
	
	@Given("the lastname Bertels, email bert.bertels@gmail.com and password PasswordForBert but no firstname")
	public void givenTheLastnameBertelsEmailBertbertelsgmailcomAndPasswordPasswordForBertButNoFirstname() {
		firstname = null;
		lastname = "Bertels";
		email = "bert.bertels@gmail.com";
		password = "PasswordForBert";
	}

	@When("I choose to create the person with the given data")
	public void whenIChooseToCreateThePersonWithTheGivenData() {
		person = createPerson();
	}

	@Then("a person object is created with these data and no firstname")
	public void thenAPersonObjectIsCreatedWithTheseDataAndNoFirstname() {
		assertNull(person.getFirstName());
		assertEquals(person.getLastName(), lastname);
		assertEquals(person.getUserId(), email);
		assertNotNull(person.getPassword());
	}
}