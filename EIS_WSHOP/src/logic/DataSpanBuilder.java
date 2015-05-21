package logic;

import java.util.Date;
import java.util.List;

import dataAccess.entities.EntityWithTime;

public class DataSpanBuilder<TE> {
	
	@SuppressWarnings("unchecked")
	public DataContainer<TE>[] processData(List<TE> input) {
		List<? extends EntityWithTime> entityInput = (List<? extends EntityWithTime> )input;
		long timeBegin = entityInput.get(0).getDate().getTime();
		long timeEnd = entityInput.get(input.size()-1).getDate().getTime();
		long totalTimeSpan = timeEnd - timeBegin;
		int timeSpans = (int) (totalTimeSpan / Configuration.getInstance().singleTimeSpan);
		DataContainer<TE>[] dataContainers = new DataContainer[timeSpans+1];
		for (int i=0; i<timeSpans+1;i++) {
			dataContainers[i] = new DataContainer<>(new Date(timeBegin+(long)i*Configuration.getInstance().singleTimeSpan));
		}
		for (EntityWithTime entity : entityInput) {
			long entityOffset = entity.getDate().getTime()-timeBegin;
			int entityIndex = (int) (entityOffset/Configuration.getInstance().singleTimeSpan);
			dataContainers[entityIndex].addElement((TE)entity);
		}
		return dataContainers;
	}
}
