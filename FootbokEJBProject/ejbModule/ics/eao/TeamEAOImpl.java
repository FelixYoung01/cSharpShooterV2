package ics.eao;

import java.util.List;

import ics.ejb.Team;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

/**
 * Session Bean implementation class TeamEAOImpl
 */
@Stateless
public class TeamEAOImpl implements TeamEAOLocal {
	private EntityManager em;
    public TeamEAOImpl() {
        // TODO Auto-generated constructor stub
    }
    
	public Team findByTeamId(String teamId) {
		return em.find(Team.class, teamId);
	}
	
	public Team createTeam(Team t) {
		em.persist(t);
		return t;
	}
	
	public Team updateTeam(Team t) {
		em.merge(t);
		return t;
	}
	
	public void deleteTeam(String teamId) {
		Team t = this.findByTeamId(teamId);
		if (t != null) {
			em.remove(t);
		}
	}
	
	public List<Team> findAll() {
		TypedQuery<Team> query = em.createNamedQuery("Team.findAll", Team.class);
		List<Team> results = query.getResultList();
		return results;
	}
}
