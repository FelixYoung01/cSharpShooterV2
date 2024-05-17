package ics.ejb;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.logging.Logger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@NamedQueries({

	@NamedQuery(name="User.findAll", query="SELECT u FROM User u"),
	@NamedQuery(name="User.countAll", query="SELECT COUNT(u) FROM User u"), //Namedquery for the stats of users registered
	@NamedQuery(name="User.findUsersOnMatch", query="SELECT u FROM User u WHERE u.match.matchId = :matchId"), // Namedquery for getting all users registered on a specific match
	@NamedQuery(name="User.availableUsers", query="SELECT u FROM User u WHERE u.match IS NULL"), // Namedquery for getting all users that are not registered on a match
	@NamedQuery(name="User.countRegisteredOnMatches", query="SELECT COUNT(u.match) FROM User u WHERE u.match IS NOT NULL") // Namedquery for counting amount of users registered on matches
	
	})
@Table(name="[User]") // User is a reserved word in SQL, so it needs to be enclosed in square brackets")


public class User implements Serializable{
	
    private static final Logger logger = Logger.getLogger(Match.class.getName());

	private static final long serialVersionUID = 1L;

    
    private String userId;
    private int age;
    private String email;
    private String gender;
    private String name;
    private LocalDateTime joined;

    private Match match; // Each user participates in exactly one match
    
	public User(String userId, int age, String email, String gender, String name) {
		this.userId = userId;
    	this.age = age;
    	this.email = email;
    	this.gender = gender;
    	this.name = name;
	}
	
	public User() {
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
	
	@Email // Validates the email format
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
	
	
	@NotNull // Name should not be null
	@Size(min=1, max=100) // Name should be between 1 and 100 characters)	
	@Column(name="Name")
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="matchId", nullable=true) // This allows a User to exist without being linked to a Match. Useful for users who have not yet chosen a match or for non-participant roles	
	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}
	
	//New fields for auditing purposes
	@Column(name="joined", updatable=false)
	public LocalDateTime getJoined() {
		return joined;
	}
	
	public void setJoined(LocalDateTime joined) {
		this.joined = joined;
	}
}


