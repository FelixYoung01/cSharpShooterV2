
package ics.eao;

import java.util.List;

import ics.ejb.Referee;
import jakarta.ejb.Local;

@Local
public interface RefereeEAOLocal {
	public List<Referee> getAllReferees();

	public void addReferee(Referee referee);

	public void updateReferee(Referee referee);

	public void deleteReferee(String refereeId);

	public Referee findRefereeById(String refereeId);

	public List<String> findAllRefereeIds();
}

