package logic.modelCalculators;

import java.util.HashMap;
import java.util.Map;

import logic.DataContainer;
import model.ApplicationForegroundModel;
import dataAccess.entities.ApplicationForeground;

public class ApplicationForegroundCalculator implements ModelCalculator<ApplicationForeground, ApplicationForegroundModel>{

	@Override
	public ApplicationForegroundModel calculate(DataContainer<ApplicationForeground> dataContainer) {
		Map<String, Long> map = new HashMap<>();
		for (ApplicationForeground app : dataContainer.getElements()) {
			String name = app.getApplicationName();
			if(map.containsKey(name)) {
				long newValue = map.get(name).longValue() + app.getDuration();
				map.remove(name);
				map.put(name, newValue);
			}
			else
				map.put(name, app.getDuration());
		}
		String mostFrequent = "";
		long longest = 0;
		for (String name : map.keySet()) {
			long length = map.get(name);
			if (length > longest) {
				mostFrequent = name;
				longest = length;
			}
		}
		boolean systemApp = false;
		for (ApplicationForeground app : dataContainer.getElements()) {
			if(app.getApplicationName().equals(mostFrequent)) {
				systemApp = app.isSystemApp();
				break;
			}
		}
		return new ApplicationForegroundModel(dataContainer.getDate(), mostFrequent, systemApp);
	}

}
