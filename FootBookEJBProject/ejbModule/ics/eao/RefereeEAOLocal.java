
package ics.eao;

import java.util.List;
import java.util.Set;

import ics.ejb.Referee;
import ics.exceptions.FootBookException;
import jakarta.ejb.Local;

@Local
public interface RefereeEAOLocal {
	public Set<Referee> getAllReferees() throws FootBookException;

	public void addReferee(Referee referee) throws FootBookException;

	public Referee updateReferee(Referee referee) throws FootBookException;

	public void deleteReferee(String refereeId) throws FootBookException;

	public Referee findRefereeById(String refereeId) throws FootBookException;

	public List<String> findAllRefereeIds() throws FootBookException;
}

