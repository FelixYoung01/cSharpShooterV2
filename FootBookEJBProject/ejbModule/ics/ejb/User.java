package ics.ejb;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@NamedQueries({
	@NamedQuery(name="User.findAll", query="SELECT u FROM User u")})
@Table(name="Users")

public class User implements Serializable{
	private static final long serialVersionUID = 1L;

    
    private String userId;
    private int age;
    private String email;
    private String gender;
    private String name;
    private Match match;
    
	public User(String userId, int age, String email, String gender, String name) {
    	this.userId = userId;
    	this.age = age;
    	this.email = email;
    	this.gender = gender;
    	this.name = name;
	}
    	
    
    @Id
    @Column(name="UserId")
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name="Age")
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	@Column(name="Email")
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="Gender")
	public String getGender() {
        return gender;
        }
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Column(name="Name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
    @ManyToOne
    @JoinColumn(name="matchId")
	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}
	
	
}


