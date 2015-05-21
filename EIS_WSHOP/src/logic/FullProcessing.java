package logic;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import logic.modelCalculators.ModelCalculator;

public class FullProcessing<TE, TM> {
	public List<TM> process(List<TE> entities, ModelCalculator<TE, TM> calculator, List<TimeSpan> where, List<TimeSpan> groupBy) {
		List<TE> entitiesSplitted = new LinkedList<>();
		TE previous = null;
		for (TE entity : entities) {
			if(previous == null) {
				previous = entity;
				continue;
			}
			entitiesSplitted.addAll(new EntitySplitter<TE>().split(previous, entity));
			previous = entity;
		}
		DataContainer<TE>[] entitiesSpanned = new DataSpanBuilder<TE>().processData(entitiesSplitted);
		List<TM> models = new ModelBuilder<TE, TM>(calculator).buildModels(Arrays.asList(entitiesSpanned), where, groupBy);
		return models;
	}
}
