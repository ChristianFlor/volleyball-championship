package model;

import java.util.Date;

public class Assistant implements Comparable<Assistant> {
	
	private String firstName;
	private String lastName;
	private String id;
	private String email;
	private String gender;
	private String country;
	private String pathForPhoto;
	private Date birthDay;
	
	public Assistant(String id ,String firstName, String lastName, String email, String gender,
			String country, String pathForPhoto, Date birthDay) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.email = email;
		this.gender = gender;
		this.country = country;
		this.pathForPhoto = pathForPhoto;
		this.birthDay = birthDay;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPathForPhoto() {
		return pathForPhoto;
	}

	public void setPathForPhoto(String pathForPhoto) {
		this.pathForPhoto = pathForPhoto;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	
	@Override
	public int compareTo(Assistant a) {
		return this.id.compareTo(a.id);
	}
	
	@Override
	public String toString() {
		return "FN: " + firstName + "\t ID: " + id + "\n";
	}

	public static Competitor cast(Espectator c) {
		return new Competitor(c.getId(), c.getFirstName(), c.getLastName(), c.getEmail(), c.getGender(), c.getCountry(), c.getPathForPhoto(), c.getBirthDay());
	}
}
