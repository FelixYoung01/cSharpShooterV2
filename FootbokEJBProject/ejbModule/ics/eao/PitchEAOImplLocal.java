package ics.eao;

import java.util.List;

import ics.ejb.Pitch;
import jakarta.ejb.Local;

@Local
public interface PitchEAOImplLocal {
	public Pitch findByPitchId(String pitchId);
	public Pitch createPitch(Pitch p);
	public Pitch updatePitch(Pitch p);
	public void deletePitch(String pitchId);
	public List<Pitch> findAll();
}
