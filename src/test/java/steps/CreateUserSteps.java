package steps;

import static org.junit.Assert.*;
import org.jbehave.core.annotations.*;

import domain.Person;

public class CreateUserSteps {
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String otherPassword;
	private Person person;
	private Person otherPerson;
	private boolean exceptionIsThrown = false;
	@SuppressWarnings("unused")
	private Throwable throwable = null;

	protected void resetPerson(){
		firstname = "Bert";
		lastname = "Bertels";
		email = "bert.bertels@gmail.com";
		password = "PasswordForBert";
	}

	@BeforeScenario
	public void beforeEachScenario() {
		this.resetPerson();
	}	
	@AfterScenario
	public void afterEachScenario() {
		this.resetPerson();
		person = null;
	}	

	protected Person createPerson() {
		return new Person(email, password, firstname, lastname);
	}
	
	@Given("the lastname $lastname, email $email and password $password but no firstname")
	public void givenTheLastnameEmailAndPasswordButNoFirstname(String lastname, String email, String password) {
		this.firstname = null;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}

	@When("I choose to create the person with the given data")
	public void whenIChooseToCreateThePersonWithTheGivenData() {
		person = null;
		throwable = null;
		exceptionIsThrown = false;
		try {
			person = createPerson();
		} catch (Exception e) {
			exceptionIsThrown = true;
			throwable = e;
		}
	}

	@Then("a person object is created with these data and no firstname")
	public void thenAPersonObjectIsCreatedWithTheseDataAndNoFirstname() {
		assertNull(person.getFirstName());
		assertEquals(person.getLastName(), lastname);
		assertEquals(person.getUserId(), email);
		assertNotNull(person.getPassword());
	}
	
	@Given("the firstname $firstname, lastname $lastname, email $email and password $password")
	public void givenTheFirstnameLastnameEmailAndPassword(String firstname, String lastname, String email, String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}

	@Then("a person object is created with these data")
	@Aliases(values={
			"a person object is created with the given email"
	})
	public void thenAPersonObjectIsCreatedWithTheseData() {
		assertEquals(person.getFirstName(), firstname);
		assertEquals(person.getLastName(), lastname);
		assertEquals(person.getUserId(), email);
		assertNotNull(person.getPassword());
	}
	@Given("the firstname $firstname, email $email and password $password of a person but no lastname")
	public void givenTheFirstnameEmailAndPasswordOfAPersonButNoLastname(String firstname, String email, String password) {
		this.firstname = firstname;
		this.lastname = null;
		this.email = email;
		this.password = password;
	}

	@Then("a person object is created with these data and no lastname")
	public void thenAPersonObjectIsCreatedWithTheseDataAndNoLastname() {
		assertEquals(person.getFirstName(), firstname);
		assertNull(person.getLastName());
		assertEquals(person.getUserId(), email);
		assertNotNull(person.getPassword());
	}
	
	@Given("the password $password")
	public void givenThePassword(String password) {
		this.password = password;
	}

	@When("I choose to create a person with this password")
	@Aliases(values={
			"I choose to create a person with this email"
	})
	public void whenIChooseToCreateAPersonWithThisPassword() {
		person = createPerson();
	}

	@Then("the password is stored as a digest of 40 characters")
	public void thenThePasswordIsStoredAsADigestOf40Characters() {
		assertTrue(person.getPassword().length() == 40);
	}

	@Given("another password $password")
	public void givenAnotherPasswordOther(String password) {
		this.otherPassword = password;
	}

	@When("I choose to create a person with the first password")
	public void whenIChooseToCreateAPersonWithTheFirstPassword() {
		person = createPerson();
	}

	@When("I choose to create a person with the second password")
	public void whenIChooseToCreateAPersonWithTheSecondPassword() {
		password = otherPassword;
		otherPerson = createPerson();
	}

	@Then("the stored password of the first person is different from the stored password of the second user")
	public void thenTheStoredPasswordOfTheFirstPersonIsDifferentFromTheStoredPasswordOfTheSecondUser() {
		assertNotEquals(person.getPassword(), otherPerson.getPassword());
	}

	@When("I choose to create another person with this password")
	public void whenIChooseToCreateAnotherPersonWithThisPassword() {
	  otherPerson = createPerson();
	}

	@Given("the firstname $firstname, lastname $lastname and password $password but no email")
	public void givenTheFirstnameLastnameAndPasswordButNoEmail(String firstname, String lastname, String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = null;
		this.password = password;
	}

	@Then("an error is given")
	public void thenAnErrorIsGiven() {
		assertTrue(exceptionIsThrown);
	}

	@Then("the person is not created")
	public void thenThePersonIsNotCreated() {
		assertNull(person);
	}

	@Given("the firstname $firstname, lastname $lastname and email $email but no password")
	public void givenTheFirstnameLastnameAndEmailButNoPassword(String firstname, String lastname, String email) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = null;
	}

	@Given("an email addres <email> that is valid because <motivation>")
	public void givenAnEmailAddresemailThatIsValidBecausemotivation(@Named("email") String email) {
		this.email = email;
	}
}