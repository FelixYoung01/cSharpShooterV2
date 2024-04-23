package ics.facade;

import java.util.List;

import ics.ejb.Match;
import jakarta.ejb.Local;

@Local
public interface FacadeLocal {
    public Match findByMatchId(long id);

	public Match createMatch(Match m);

	public Match updateMatch(Match m);

	public void deleteMatch(long id);
	
	public List<Match> findAll();

	public List<Match> findByPitchId(String pitchId);
}
