package ics.eao;

import java.util.List;

import ics.ejb.Player;
import jakarta.ejb.Local;

@Local
public interface PlayerEAOLocal {
	public Player findByPlayerId(String playerId);
	public Player createPlayer(Player p);
	public Player updatePlayer(Player p);
	public void deletePlayer(String playerId);
	public List<Player> findAll();
}
