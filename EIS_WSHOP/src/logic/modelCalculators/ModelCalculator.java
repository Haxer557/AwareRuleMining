package logic.modelCalculators;

import logic.DataContainer;

public interface ModelCalculator<TE, TM> {
	public TM calculate(DataContainer<TE> dataContainer);
}
