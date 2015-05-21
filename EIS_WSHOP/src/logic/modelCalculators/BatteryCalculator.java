package logic.modelCalculators;

import logic.DataContainer;
import model.BatteryLevel;
import model.BatteryModel;
import model.BatteryStatus;
import dataAccess.entities.Battery;

public class BatteryCalculator implements ModelCalculator<Battery, BatteryModel> {

	@Override
	public BatteryModel calculate(DataContainer<Battery> dataContainer) {
		final int batteryStatusSwitch = 2;
		
		int[] batteryStatuses = new int[8];
		int totalBatteryLevel = 0;
		for (Battery battery : dataContainer.getElements()) {
			int switchedBatteryStatus = battery.getBatteryStatus() + batteryStatusSwitch;
			batteryStatuses[switchedBatteryStatus]++;
			totalBatteryLevel += battery.getBatteryLevel();
		}
		int numberOfSamples = dataContainer.getElements().size();
		
		int mostFrequentSwitchedBS = 3; //defaultowo UNKNOWN (1+2)
		int timesBS = 0;
		for (int i =0; i < 8; i++) {
			if(batteryStatuses[i] > timesBS) {
				mostFrequentSwitchedBS = i;
				timesBS = batteryStatuses[i];
			}
		}
		int mostFrequentBS = mostFrequentSwitchedBS - batteryStatusSwitch;
		double batteryLevelD = 0;
		if(numberOfSamples > 0) {
			batteryLevelD = ((double)totalBatteryLevel / (double)numberOfSamples) / (double)(dataContainer.getElements().get(0).getBatteryScale());
		}
		
		BatteryStatus batteryStatus = BatteryStatus.getByInt(mostFrequentBS);
		BatteryLevel batteryLevel = BatteryLevel.getByPercentage(batteryLevelD);
		return new BatteryModel(dataContainer.getDate(), batteryStatus, batteryLevel);
	}

}
