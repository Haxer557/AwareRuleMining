package logic.modelCalculators;

import logic.DataContainer;
import model.ScreenModel;
import model.ScreenStatus;
import dataAccess.entities.Screen;

public class ScreenCalculator implements ModelCalculator<Screen, ScreenModel> {

	@Override
	public ScreenModel calculate(DataContainer<Screen> dataContainer) {
		long[] statuses = new long[4];
		for (Screen screen : dataContainer.getElements()) {
			statuses[screen.getScreenStatus()] += screen.getDuration();
		}
		int longestStatus = 0;
		long longest = 0L;
		for(int i = 0; i < 4; i++) {
			if(statuses[i] > longest) {
				longestStatus = i;
				longest = statuses[i];
			}
		}
		ScreenStatus screenStatus = ScreenStatus.getByInt(longestStatus);
		return new ScreenModel(dataContainer.getDate(), screenStatus);
	}

}
