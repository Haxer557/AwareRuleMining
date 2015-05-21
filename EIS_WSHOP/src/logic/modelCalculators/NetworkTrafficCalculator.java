package logic.modelCalculators;

import dataAccess.entities.NetworkTraffic;
import logic.DataContainer;
import model.NetworkTrafficModel;

public class NetworkTrafficCalculator implements ModelCalculator<NetworkTraffic, NetworkTrafficModel> {
	
	@Override
	public NetworkTrafficModel calculate(DataContainer<NetworkTraffic> dataContainer) {
		long totalSent = 0;
		long totalReceived = 0;
		for (NetworkTraffic trafficEntity : dataContainer.getElements()) {
			totalSent += trafficEntity.getDataSentB();
			totalReceived += trafficEntity.getDataReceivedB();
		}
		return new NetworkTrafficModel(dataContainer.getDate(), totalSent, totalReceived);
	}
}
