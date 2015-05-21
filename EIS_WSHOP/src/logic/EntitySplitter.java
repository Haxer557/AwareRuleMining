package logic;

import java.util.LinkedList;
import java.util.List;

import dataAccess.entities.EntityWithTime;

public class EntitySplitter<TE> {
	@SuppressWarnings("unchecked")
	public List<TE> split(TE e, TE n) {
		EntityWithTime entity = (EntityWithTime)e;
		EntityWithTime next = (EntityWithTime)n;
		List<TE> splitEntities = new LinkedList<>();
		long duration = next.getDate().getTime() - entity.getDate().getTime();
		long shift = 0;
		while (duration > Configuration.getInstance().singleTimeSpan) {
			EntityWithTime entityWithTime = entity.copy();
			entityWithTime.getDate().setTime(entityWithTime.getDate().getTime()+shift);
			entityWithTime.setDuration(Configuration.getInstance().singleTimeSpan);
			shift += Configuration.getInstance().singleTimeSpan;
			duration -= Configuration.getInstance().singleTimeSpan;
			splitEntities.add((TE)entityWithTime);
		}
		entity.getDate().setTime(entity.getDate().getTime()+shift);
		entity.setDuration(duration);
		splitEntities.add((TE)entity);
		return splitEntities;
	}
}
