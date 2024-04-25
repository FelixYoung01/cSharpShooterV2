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

    @Id
    @Column(name="userId")
    private String userId;
    
    @Column(name="age")
    private int age;
    
    @Column(name="Email")
    private String email;
    
    @Column(name="Gender")
    private String gender;
    
    @Column(name="Name")
    private String name;
    
    //many to one relationship with Match
   /* @ManyToOne
    @JoinColumn(name="matchId")
	public set<Match> getMatch() {
		return match;
	}

	public void setMatch(Set<Match> match) {
		this.match = match;
	}
	*/
    
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
}


