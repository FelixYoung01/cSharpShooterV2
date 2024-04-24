package ics.ejb;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


@Entity
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u") })
@Table(name = "User")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userId;
	private String name;
	private String email;
	private int Age;
	private String Gender;
	
	@Id
	@Column(name = "userId")
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "Age")
	public int getAge() {
		return Age;
	}
	
	public void setAge(int age) {
		this.Age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}
	
	
	
	
	


}


