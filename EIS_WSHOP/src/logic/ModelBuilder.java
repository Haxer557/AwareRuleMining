package logic;

import java.util.LinkedList;
import java.util.List;

import logic.modelCalculators.ModelCalculator;
import model.EntityModel;

public class ModelBuilder<TE, TM> {
	
	private final ModelCalculator<TE, TM> modelCalculator;
	
	public ModelBuilder(ModelCalculator<TE, TM> modelCalculator) {
		this.modelCalculator = modelCalculator;
	}
	
	public List<TM> buildModels(List<DataContainer<TE>> containers, List<TimeSpan> where, List<TimeSpan> groupBy) {
		List<TM> models = new LinkedList<>();
		for (DataContainer<TE> dataContainer : containers) {
			TM model = modelCalculator.calculate(dataContainer);
			if(!meetsWhere(model, where))
				continue;
			((EntityModel) model).setGroupedBy(getGroup(model, groupBy));
			models.add(model);
		}
		return models;
	}
	
	public boolean meetsWhere(TM model, List<TimeSpan> where) {
		if(model == null)
			return false;
		for (TimeSpan timeSpan : where) {
			if(timeSpan.covers((EntityModel) model))
				return true;
		}
		return false;
	}
	
	public TimeSpan getGroup(TM model, List<TimeSpan> groupBy) {
		for (TimeSpan timeSpan : groupBy) {
			if(timeSpan.covers((EntityModel) model))
				return timeSpan;
		}
		throw new UnsupportedOperationException("GroupBy nie pokrywa ca³ego zakresu!");
	}
}
