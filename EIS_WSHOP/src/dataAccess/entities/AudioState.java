package dataAccess.entities;

import java.util.Date;

public final class AudioState extends EntityWithTime {
	private final Device device;
	private final int ringerMode;
	private final int music;
	private final int wiredHeadphones;
	private final int btHeadphones;
	private final int volumeMedia;
	private final int volumeMediaMax;
	private final int volumeRingtone;
	private final int volumeRingtoneMax;
	
	public AudioState(Date date, Device device, int ringerMode, int music, int wiredHeadphones, int btHeadphones, int volumeMedia, int volumeMediaMax, int volumeRingtone, int volumeRingtoneMax) {
		super(date);
		this.device = device;
		this.ringerMode = ringerMode;
		this.music = music;
		this.wiredHeadphones = wiredHeadphones;
		this.btHeadphones = btHeadphones;
		this.volumeMedia = volumeMedia;
		this.volumeMediaMax = volumeMediaMax;
		this.volumeRingtone = volumeRingtone;
		this.volumeRingtoneMax = volumeRingtoneMax;
	}

	public Device getDevice() {
		return device;
	}

	public int getRingerMode() {
		return ringerMode;
	}

	public int getMusic() {
		return music;
	}

	public int getWiredHeadphones() {
		return wiredHeadphones;
	}

	public int getBtHeadphones() {
		return btHeadphones;
	}

	public int getVolumeMedia() {
		return volumeMedia;
	}

	public int getVolumeMediaMax() {
		return volumeMediaMax;
	}

	public int getVolumeRingtone() {
		return volumeRingtone;
	}

	public int getVolumeRingtoneMax() {
		return volumeRingtoneMax;
	}

	@Override
	public EntityWithTime copy() {
		return new AudioState(new Date(date.getTime()), device, ringerMode, music, wiredHeadphones, btHeadphones, volumeMedia, volumeMediaMax, volumeRingtone, volumeRingtoneMax);
	}
}
