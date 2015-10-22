package domain;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
	private String userId;
	private String password;
	private String firstName;
	private String lastName;

	public Person(String userId, String password, String firstName, String lastName) {
		setUserId(userId);
		setHashedPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
	}
	
	public Person() {
	}

	public void setUserId(String userId) {
		if(userId == null || userId.isEmpty()){
			throw new IllegalArgumentException("No id given");
		}
		String USERID_PATTERN = 
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern p = Pattern.compile(USERID_PATTERN);
		Matcher m = p.matcher(userId);
		if (!m.matches()) {
			throw new IllegalArgumentException("Email not valid");
		}
		this.userId = userId;
	}

	
	
	public String getUserId() {
		return userId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public boolean isCorrectPassword(String password)  {
		if(password == null || password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		return getPassword().equals(hashPassword(password));
	}

	public void setPassword(String password) {
		if(password == null || password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		this.password=password;
	}
	
	public void setHashedPassword(String password) {
		if(password == null || password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		this.password=hashPassword(password);
	}
	
	private String hashPassword(String password) {
		return Person.hashString(password);
	}
	

	public static String hashString(String input){
		MessageDigest crypt;
		try {
			crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(input.getBytes("UTF-8"));
			byte[] digest = crypt.digest();
			return new BigInteger(1, digest).toString(16);
		} catch (Exception e) {
			throw new RuntimeException("error hashing password\r\n"+e);
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}	
}
