package tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.Configuration;
import logic.DataSet;
import logic.FullProcessing;
import logic.WekaWriter;
import logic.dbscan.DbScan;
import logic.modelCalculators.ApplicationForegroundCalculator;
import logic.modelCalculators.AudioStateCalculator;
import logic.modelCalculators.BatteryCalculator;
import logic.modelCalculators.GoogleActivityCalculator;
import logic.modelCalculators.LocationCalculator;
import logic.modelCalculators.NetworkCalculator;
import logic.modelCalculators.NetworkTrafficCalculator;
import logic.modelCalculators.ScreenCalculator;
import logic.modelCalculators.WeatherCalculator;
import model.ApplicationForegroundModel;
import model.AudioStateModel;
import model.BatteryModel;
import model.GoogleActivityModel;
import model.LocationModel;
import model.NetworkModel;
import model.NetworkTrafficModel;
import model.ScreenModel;
import model.WeatherModel;

import dataAccess.entities.ApplicationForeground;
import dataAccess.entities.AudioState;
import dataAccess.entities.Battery;
import dataAccess.entities.Device;
import dataAccess.entities.GoogleActivity;
import dataAccess.entities.Location;
import dataAccess.entities.Network;
import dataAccess.entities.NetworkTraffic;
import dataAccess.entities.Screen;
import dataAccess.entities.WeatherCurrent;
import dataAccess.repositories.ApplicationForegroudRepository;
import dataAccess.repositories.AudioStateRepository;
import dataAccess.repositories.BatteryRepository;
import dataAccess.repositories.DeviceRepository;
import dataAccess.repositories.GoogleActivityRepository;
import dataAccess.repositories.LocationRepository;
import dataAccess.repositories.NetworkRepository;
import dataAccess.repositories.NetworkTrafficRepository;
import dataAccess.repositories.ScreenRepository;
import dataAccess.repositories.WeatherCurrentRepository;

public class Tests {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Inicjalizacja...");
		System.out.println("Pobieranie urz¹dzeñ...");
		List<Device> devices = new DeviceRepository().getAll();
		for (Device device : devices) {
			LocationRepository lRepo = new LocationRepository();
			System.out.println("Pobieranie danych o lokalizacjach...");
			List<Location> ls = lRepo.getAllByDevice(device);
			double[] minMaxes = lRepo.getLocationMinMaxes(device);
			System.out.println("Klasteryzacja danych o lokalizacjach...");
			DbScan.dbScan(ls, minMaxes, Configuration.getInstance().dbscanEps, 	Configuration.getInstance().dbscanMinNeighbours);
			System.out.println("Przetwarzanie danych o lokalizacjach..");
			List<LocationModel> lModels = new FullProcessing<Location, LocationModel>().process(ls, new LocationCalculator(), Configuration.getInstance().whereFilter, Configuration.getInstance().groupByFilter);
			
			System.out.println("Pobieranie danych o aplikacjach...");
			List<ApplicationForeground> afs = new ApplicationForegroudRepository().getAllByDevice(device);
			System.out.println("Pobieranie danych audio...");
			List<AudioState> ass = new AudioStateRepository().getAllByDevice(device);
			System.out.println("Pobieranie danych o baterii...");
			List<Battery> bs = new BatteryRepository().getAllByDevice(device);
			System.out.println("Pobieranie danych o aktywnoœci Google...");
			List<GoogleActivity> gas = new GoogleActivityRepository().getAllByDevice(device);
			System.out.println("Pobieranie danych o sieci...");
			List<Network> ns = new NetworkRepository().getAllByDevice(device);
			System.out.println("Pobieranie danych o transferze danych...");
			List<NetworkTraffic> nts = new NetworkTrafficRepository().getAllByDevice(device);
			System.out.println("Pobieranie danych o wyœwietlaczu...");
			List<Screen> ss = new ScreenRepository().getAllByDevice(device);
			System.out.println("Pobieranie danych o pogodzie...");
			List<WeatherCurrent> wcs = new WeatherCurrentRepository().getAllByDevice(device);
			
			System.out.println("Przetwarzanie danych o aplikacjach...");
			List<ApplicationForegroundModel> afModels = new FullProcessing<ApplicationForeground, ApplicationForegroundModel>().process(afs, new ApplicationForegroundCalculator(), Configuration.getInstance().whereFilter, Configuration.getInstance().groupByFilter);
			System.out.println("Przetwarzanie danych audio...");
			List<AudioStateModel> asModels = new FullProcessing<AudioState, AudioStateModel>().process(ass, new AudioStateCalculator(), Configuration.getInstance().whereFilter, Configuration.getInstance().groupByFilter);
			System.out.println("Przetwarzanie danych o baterii...");
			List<BatteryModel> bModels = new FullProcessing<Battery, BatteryModel>().process(bs, new BatteryCalculator(), Configuration.getInstance().whereFilter, Configuration.getInstance().groupByFilter);
			System.out.println("Przetwarzanie danych o aktywnoœci Google...");
			List<GoogleActivityModel> gaModels = new FullProcessing<GoogleActivity, GoogleActivityModel>().process(gas, new GoogleActivityCalculator(), Configuration.getInstance().whereFilter, Configuration.getInstance().groupByFilter);
			System.out.println("Przetwarzanie danych o sieci...");
			List<NetworkModel> nModels = new FullProcessing<Network, NetworkModel>().process(ns, new NetworkCalculator(), Configuration.getInstance().whereFilter, Configuration.getInstance().groupByFilter);
			System.out.println("Przetwarzanie danych o transferze danych...");
			List<NetworkTrafficModel> ntModels = new FullProcessing<NetworkTraffic, NetworkTrafficModel>().process(nts, new NetworkTrafficCalculator(), Configuration.getInstance().whereFilter, Configuration.getInstance().groupByFilter);
			System.out.println("Przetwarzanie danych o wyœwietlaczu...");
			List<ScreenModel> sModels = new FullProcessing<Screen, ScreenModel>().process(ss, new ScreenCalculator(), Configuration.getInstance().whereFilter, Configuration.getInstance().groupByFilter);
			System.out.println("Przetwarzanie danych o pogodzie...");
			List<WeatherModel> wModels = new FullProcessing<WeatherCurrent, WeatherModel>().process(wcs, new WeatherCalculator(), Configuration.getInstance().whereFilter, Configuration.getInstance().groupByFilter);
			
			System.out.println("Generowanie zbiorów danych...");
			Map<Long, DataSet> dataSets =  new HashMap<>();
			for (ApplicationForegroundModel afModel : afModels) {
				DataSet dataSet = dataSets.get(afModel.getDate().getTime());
				if(dataSet == null) {
					dataSet = new DataSet(afModel.getDayOfWeek(), afModel.getGroupedBy());
					dataSets.put(afModel.getDate().getTime(), dataSet);
				}
				dataSet.setApplicationForegroundModel(afModel);
			}
			for (AudioStateModel asModel : asModels) {
				DataSet dataSet = dataSets.get(asModel.getDate().getTime());
				if(dataSet == null) {
					dataSet = new DataSet(asModel.getDayOfWeek(), asModel.getGroupedBy());
					dataSets.put(asModel.getDate().getTime(), dataSet);
				}
				dataSet.setAudioStateModel(asModel);
			}
			for (BatteryModel bModel : bModels) {
				DataSet dataSet = dataSets.get(bModel.getDate().getTime());
				if(dataSet == null) {
					dataSet = new DataSet(bModel.getDayOfWeek(), bModel.getGroupedBy());
					dataSets.put(bModel.getDate().getTime(), dataSet);
				}
				dataSet.setBatteryModel(bModel);
			}
			for (GoogleActivityModel gaModel : gaModels) {
				DataSet dataSet = dataSets.get(gaModel.getDate().getTime());
				if(dataSet == null) {
					dataSet = new DataSet(gaModel.getDayOfWeek(), gaModel.getGroupedBy());
					dataSets.put(gaModel.getDate().getTime(), dataSet);
				}
				dataSet.setGoogleActivityModel(gaModel);
			}
			for (LocationModel lModel : lModels) {
				DataSet dataSet = dataSets.get(lModel.getDate().getTime());
				if(dataSet == null) {
					dataSet = new DataSet(lModel.getDayOfWeek(), lModel.getGroupedBy());
					dataSets.put(lModel.getDate().getTime(), dataSet);
				}
				dataSet.setLocationModel(lModel);
			}
			for (NetworkModel nModel : nModels) {
				DataSet dataSet = dataSets.get(nModel.getDate().getTime());
				if(dataSet == null) {
					dataSet = new DataSet(nModel.getDayOfWeek(), nModel.getGroupedBy());
					dataSets.put(nModel.getDate().getTime(), dataSet);
				}
				dataSet.setNetworkModel(nModel);
			}
			for (NetworkTrafficModel ntModel : ntModels) {
				DataSet dataSet = dataSets.get(ntModel.getDate().getTime());
				if(dataSet == null) {
					dataSet = new DataSet(ntModel.getDayOfWeek(), ntModel.getGroupedBy());
					dataSets.put(ntModel.getDate().getTime(), dataSet);
				}
				dataSet.setNetworkTrafficModel(ntModel);
			}
			for (ScreenModel sModel : sModels) {
				DataSet dataSet = dataSets.get(sModel.getDate().getTime());
				if(dataSet == null) {
					dataSet = new DataSet(sModel.getDayOfWeek(), sModel.getGroupedBy());
					dataSets.put(sModel.getDate().getTime(), dataSet);
				}
				dataSet.setScreenModel(sModel);
			}
			for (WeatherModel wModel : wModels) {
				DataSet dataSet = dataSets.get(wModel.getDate().getTime());
				if(dataSet == null) {
					dataSet = new DataSet(wModel.getDayOfWeek(), wModel.getGroupedBy());
					dataSets.put(wModel.getDate().getTime(), dataSet);
				}
				dataSet.setWeatherModel(wModel);
			}
			System.out.println("Zapisywanie danych do pliku wyjœciowego...");
			WekaWriter.writeToWekaFile(dataSets.values());
			System.out.println("Gotowe!");
		}
	}

	
}
