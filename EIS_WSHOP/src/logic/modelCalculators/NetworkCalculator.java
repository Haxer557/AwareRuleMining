package logic.modelCalculators;

import dataAccess.entities.Network;
import logic.DataContainer;
import model.NetworkModel;
import model.NetworkType;

public class NetworkCalculator implements ModelCalculator<Network, NetworkModel> {

	@Override
	public NetworkModel calculate(DataContainer<Network> dataContainer) {
		final int networkTypeSwitch = 1;
		
		int[] networkTypeCounts = new int[7];
		int networkEnabledCount = 0;
		int numberOfSamples = dataContainer.getElements().size();
		
		for (Network network : dataContainer.getElements()) {
			int switchedNT = network.getNetworkType() + networkTypeSwitch;
			networkTypeCounts[switchedNT]++;
			if(network.getNetworkState() == 1)
				networkEnabledCount++;
		}
		
		boolean networkEnabled = networkEnabledCount > numberOfSamples / 2;
		int mostFrequentSwitchedNT = 2; //defaultowo WIFI (1+1)
		int timesNT = 0;
		for (int i =0; i < 7; i++) {
			if(networkTypeCounts[i] > timesNT) {
				mostFrequentSwitchedNT = i;
				timesNT = networkTypeCounts[i];
			}
		}
		int mostFrequentNT = mostFrequentSwitchedNT - networkTypeSwitch;
		NetworkType networkType = NetworkType.getByInt(mostFrequentNT);
		NetworkModel networkModel = new NetworkModel(dataContainer.getDate(), networkEnabled, networkType);
		return networkModel;
	}

}
