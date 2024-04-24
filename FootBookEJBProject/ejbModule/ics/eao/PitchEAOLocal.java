package ics.eao;

import java.util.List;

import ics.ejb.Pitch;
import jakarta.ejb.Local;

@Local
public interface PitchEAOLocal {

	public void createPitch(Pitch pitch);

	public void updatePitch(Pitch pitch);

	public void deletePitch(Pitch pitch);

	public Pitch findPitchById(String pitchId);

}
