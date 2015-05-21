package logic.modelCalculators;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import logic.DataContainer;
import model.GoogleActivityModel;
import dataAccess.entities.GoogleActivity;

public class GoogleActivityCalculator implements ModelCalculator<GoogleActivity, GoogleActivityModel> {
	private class SingleActivity {
		public final String activity;
		public final int confidence;
		private SingleActivity(String activity, int confidence) {
			this.activity = activity;
			this.confidence = confidence;
		}
	}
	
	@Override
	public GoogleActivityModel calculate(DataContainer<GoogleActivity> dataContainer) {
		Map<String, Integer> map = new HashMap<>();
		for (GoogleActivity ga : dataContainer.getElements()) {
			List<SingleActivity> activities = parseActivities(ga.getActivities());
			for (SingleActivity singleActivity : activities) {
				String name = singleActivity.activity;
				if(map.containsKey(name)) {
					int newValue = map.get(name).intValue()+singleActivity.confidence;
					map.remove(name);
					map.put(name, newValue);
				}
				else
					map.put(name, singleActivity.confidence);
			}
		}
		String mostFrequentActivity = "";
		int confSum = 0;
		for (String key : map.keySet()) {
			if(map.get(key) > confSum) {
				confSum = map.get(key);
				mostFrequentActivity = key;
			}
		}
		return new GoogleActivityModel(dataContainer.getDate(), mostFrequentActivity);
	}
	
	private List<SingleActivity> parseActivities(String activities) {
		List<SingleActivity> singleActivities = new LinkedList<>();
		String[] activityRows = activities.substring(2, activities.length()-2).split("\\}\\,\\{");
		for (String activityRow : activityRows) {
			String[] params = activityRow.split(",");
			String activity = params[0].split(":")[1];
			activity = activity.substring(1, activity.length()-1);
			int confidence = Integer.parseInt(params[1].split(":")[1]);
			SingleActivity singleActivity = new SingleActivity(activity, confidence);
			singleActivities.add(singleActivity);
		}
		return singleActivities;
	}

}
