package ics.eao;

import java.util.List;

import ics.ejb.Match;
import ics.ejb.Pitch;
import jakarta.ejb.Local;

@Local
public interface MatchEAOLocal {
    public Match findByMatchId(long id);

	public Match createMatch(Match m);

	public Match updateMatch(Match m);

	public void deleteMatch(long id);

	public List<Match> findAll();

	public List<Match> findByPitch(Pitch pitch);
}
