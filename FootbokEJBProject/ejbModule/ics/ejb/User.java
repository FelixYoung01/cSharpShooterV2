package ics.ejb;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;


	
	@Entity
	@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
    })
	    @Table(name="User")
public class User implements Serializable{
		private String name;
		
		
		@Column(name = "playerName")
		public String getPlayerName() {
			return name;
		}

		public void setPlayerName(String playerName) {
			this.name = playerName;
		}
		
		@Column(name = "refereeName")
		public String getRefereeName() {
			return name;
		}

		public void setRefereeName(String refereeName) {
			this.name = refereeName;
		}

}
