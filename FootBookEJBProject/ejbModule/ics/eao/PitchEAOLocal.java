package ics.eao;

import java.util.List;
import java.util.Set;

import ics.ejb.Pitch;
import ics.exceptions.FootBookException;
import jakarta.ejb.Local;

@Local
public interface PitchEAOLocal {

	public void createPitch(Pitch pitch) throws FootBookException;

	public void updatePitch(Pitch pitch) throws FootBookException;

	public void deletePitch(String pitch) throws FootBookException;

	public Pitch findPitchById(String pitchId) throws FootBookException;

	public Set<Pitch> getAllPitches() throws FootBookException;

	public List<String> findAllPitchIds() throws FootBookException;
}
