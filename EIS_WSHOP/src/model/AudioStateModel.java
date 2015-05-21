package model;

import java.util.Date;

public final class AudioStateModel extends EntityModel {
	private final boolean music;
	private final boolean headphones;
	private final RingerMode ringerMode;
	private final VolumeLevel mediaLevel;
	private final VolumeLevel ringtoneLevel;
	
	public AudioStateModel(Date date, boolean music, boolean headphones, RingerMode ringerMode, VolumeLevel mediaLevel, VolumeLevel ringtoneLevel) {
		super(date);
		this.music = music;
		this.headphones = headphones;
		this.ringerMode = ringerMode;
		this.mediaLevel = mediaLevel;
		this.ringtoneLevel = ringtoneLevel;
	}

	public boolean isMusic() {
		return music;
	}

	public boolean isHeadphones() {
		return headphones;
	}

	public RingerMode getRingerMode() {
		return ringerMode;
	}

	public VolumeLevel getMediaLevel() {
		return mediaLevel;
	}

	public VolumeLevel getRingtoneLevel() {
		return ringtoneLevel;
	}

	public static String toString(AudioStateModel model) {
		if (model == null)
			return "?,?,?,?,?";
		return (model.isMusic() ? "TRUE" : "FALSE") + "," + 
			   (model.isHeadphones() ? "TRUE" : "FALSE") + "," + 
			   model.getRingerMode().toString() + "," +
			   model.getMediaLevel().toString() + "," + 
			   model.getRingtoneLevel().toString();
	}
}
