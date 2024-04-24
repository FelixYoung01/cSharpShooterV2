
package ics.eao;

import java.util.List;

import ics.ejb.Referee;
import jakarta.ejb.Local;

@Local
public interface RefereeEAOLocal {
	public Referee findByRefereeId(String refereeId);

	public Referee createReferee(Referee r);

	public Referee updateReferee(Referee r);

	public void deleteReferee(String refereeId);

	public List<Referee> findAll();
}
