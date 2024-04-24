
package ics.eao;

import java.util.List;

import ics.ejb.Player;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

/**
 * Session Bean implementation class PlayerEAOImpl
 */
@Stateless
public class PlayerEAOImpl implements PlayerEAOLocal {
	@PersistenceContext(unitName = "FootBookEJBSql")
	private EntityManager em;

	public PlayerEAOImpl() {

	}

	public Player findByPlayerId(String playerId) {
		return em.find(Player.class, playerId);
	}

	public Player createPlayer(Player p) {
		em.persist(p);
		return p;
	}

	public Player updatePlayer(Player p) {
		em.merge(p);
		return p;
	}

	public void deletePlayer(String playerId) {
		Player p = this.findByPlayerId(playerId);
		if (p != null) {
			em.remove(p);
		}
	}

	public List<Player> findAll() {
		TypedQuery<Player> query = em.createNamedQuery("Player.findAll", Player.class);
		List<Player> results = query.getResultList();
		return results;
	}
}
