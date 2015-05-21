package logic.modelCalculators;

import logic.DataContainer;
import model.AudioStateModel;
import model.RingerMode;
import model.VolumeLevel;
import dataAccess.entities.AudioState;

public class AudioStateCalculator implements ModelCalculator<AudioState, AudioStateModel> {

	@Override
	public AudioStateModel calculate(DataContainer<AudioState> dataContainer) {
		if(dataContainer.getElements().size() == 0)
			return null;
		int musicSamples = 0;
		int headphonesSamples = 0;
		int totalMediaVolume = 0;
		int totalRingtoneVolume = 0;
		int[] rmCounts = new int[3];
		int numberOfSamples = dataContainer.getElements().size();
		for (AudioState audioState : dataContainer.getElements()) {
			if(audioState.getMusic() > 0)
				musicSamples++;
			if(audioState.getWiredHeadphones() > 0 || audioState.getBtHeadphones() > 0)
				headphonesSamples++;
			totalMediaVolume += audioState.getVolumeMedia();
			totalRingtoneVolume += audioState.getVolumeRingtone();
			rmCounts[audioState.getRingerMode()]++;
		}
		
		boolean music = musicSamples > numberOfSamples / 2;
		boolean headphones = headphonesSamples > numberOfSamples / 2;
		double mediaVolume = 0;
		double ringtoneVolume = 0;
		if(numberOfSamples != 0) {
			mediaVolume = ((double)totalMediaVolume / (double)numberOfSamples) / (double)(dataContainer.getElements().get(0).getVolumeMediaMax());
			ringtoneVolume = ((double)totalRingtoneVolume / (double)numberOfSamples) / (double)(dataContainer.getElements().get(0).getVolumeRingtoneMax());
		}
		int mostFrequentRM = 0;
		int times = 0;
		for (int i = 0; i < 3; i++) {
			if (rmCounts[i] > times) {
				mostFrequentRM = i;
				times = rmCounts[i];
			}
		}
		VolumeLevel mediaLevel = VolumeLevel.getByPercentage(mediaVolume);
		VolumeLevel ringtoneLevel = VolumeLevel.getByPercentage(ringtoneVolume);
		RingerMode ringerMode = RingerMode.getByInt(mostFrequentRM);
		return new AudioStateModel(dataContainer.getDate(), music, headphones, ringerMode, mediaLevel, ringtoneLevel);
	}

}
