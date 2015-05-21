package logic.modelCalculators;

import java.util.HashMap;
import java.util.Map;

import dataAccess.entities.Location;
import logic.DataContainer;
import model.LocationModel;

public class LocationCalculator implements ModelCalculator<Location, LocationModel> {

	@Override
	public LocationModel calculate(DataContainer<Location> dataContainer) {
		Map<String, Integer> map = new HashMap<>();
		for (Location loc : dataContainer.getElements()) {
			if(map.containsKey(loc.getLocationName())) {
				int newValue = map.get(loc.getLocationName()).intValue()+1;
				map.remove(loc.getLocationName());
				map.put(loc.getLocationName(), newValue);
			}
			else
				map.put(loc.getLocationName(), 1);
		}
		String mostFrequentLocation = "";
		int locSum = 0;
		for (String key : map.keySet()) {
			if(map.get(key) > locSum) {
				locSum = map.get(key);
				mostFrequentLocation = key;
			}
		}
		return new LocationModel(dataContainer.getDate(), mostFrequentLocation);
	}

}
