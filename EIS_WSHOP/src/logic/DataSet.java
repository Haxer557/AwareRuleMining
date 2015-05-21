package logic;

import model.ApplicationForegroundModel;
import model.AudioStateModel;
import model.BatteryModel;
import model.GoogleActivityModel;
import model.LocationModel;
import model.NetworkModel;
import model.NetworkTrafficModel;
import model.ScreenModel;
import model.WeatherModel;

public class DataSet {
	private ApplicationForegroundModel applicationForegroundModel;
	private AudioStateModel audioStateModel;
	private BatteryModel batteryModel;
	private GoogleActivityModel googleActivityModel;
	private LocationModel locationModel;
	private NetworkModel networkModel;
	private NetworkTrafficModel networkTrafficModel;
	private ScreenModel screenModel;
	private WeatherModel weatherModel;
	private final DayOfWeek dayOfWeek;
	private final TimeSpan groupedBy;
	
	public DataSet(DayOfWeek dayOfWeek, TimeSpan groupedBy) {
		this.dayOfWeek = dayOfWeek;
		this.groupedBy = groupedBy;
	}
	
	public ApplicationForegroundModel getApplicationForegroundModel() {
		return applicationForegroundModel;
	}
	public void setApplicationForegroundModel(ApplicationForegroundModel applicationForegroundModel) {
		this.applicationForegroundModel = applicationForegroundModel;
	}
	public AudioStateModel getAudioStateModel() {
		return audioStateModel;
	}
	public void setAudioStateModel(AudioStateModel audioStateModel) {
		this.audioStateModel = audioStateModel;
	}
	public BatteryModel getBatteryModel() {
		return batteryModel;
	}
	public void setBatteryModel(BatteryModel batteryModel) {
		this.batteryModel = batteryModel;
	}
	public GoogleActivityModel getGoogleActivityModel() {
		return googleActivityModel;
	}
	public void setGoogleActivityModel(GoogleActivityModel googleActivityModel) {
		this.googleActivityModel = googleActivityModel;
	}
	public NetworkModel getNetworkModel() {
		return networkModel;
	}
	public void setNetworkModel(NetworkModel networkModel) {
		this.networkModel = networkModel;
	}
	public NetworkTrafficModel getNetworkTrafficModel() {
		return networkTrafficModel;
	}
	public void setNetworkTrafficModel(NetworkTrafficModel networkTrafficModel) {
		this.networkTrafficModel = networkTrafficModel;
	}
	public ScreenModel getScreenModel() {
		return screenModel;
	}
	public void setScreenModel(ScreenModel screenModel) {
		this.screenModel = screenModel;
	}
	public WeatherModel getWeatherModel() {
		return weatherModel;
	}
	public void setWeatherModel(WeatherModel weatherModel) {
		this.weatherModel = weatherModel;
	}
	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}
	public TimeSpan getGroupedBy() {
		return groupedBy;
	}
	
	@Override
	public String toString() {
		return  dayOfWeek + "," +
				groupedBy.toStringTime() + "," +
				ApplicationForegroundModel.toString(applicationForegroundModel) + "," +
				AudioStateModel.toString(audioStateModel) + "," +
				BatteryModel.toString(batteryModel) + "," +
				GoogleActivityModel.toString(googleActivityModel) + "," +
				LocationModel.toString(locationModel) + "," +
				NetworkModel.toString(networkModel) + "," +
				NetworkTrafficModel.toString(networkTrafficModel) + "," +
				ScreenModel.toString(screenModel) + "," +
				WeatherModel.toString(weatherModel);
	}

	public LocationModel getLocationModel() {
		return locationModel;
	}

	public void setLocationModel(LocationModel locationModel) {
		this.locationModel = locationModel;
	}
}
