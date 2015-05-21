package logic.dbscan;

import java.util.LinkedList;
import java.util.List;

import logic.Configuration;

import dataAccess.entities.Location;

/**
 * @author Przemek
 * Algorytm DbScan
 * Implementacja w oparciu o http://en.wikipedia.org/wiki/DBSCAN
 */
public class DbScan {
	static int regionQueries = 0;
	static int allCount = 0;
	static double lastPercentage = 0.0;
	
	public static List<PointDB> dbScan(List<Location> locations, double[] minMaxes, double eps, int minPoints) {
		System.out.println("DBSCAN: Inicjalizacja...");
		List<PointDB> points = initializeLocations(locations, minMaxes);
		System.out.println("DBSCAN: Tworzenie mapy lokacji...");
		List<PointDB>[][] map = createPointMap(points);
		int currentCluster = 0;
		allCount = points.size();
		System.out.println("DBSCAN: Klasteryzacja...");
		for (PointDB point : points) {
			if(point.isVisited())
				continue;
			point.setVisited(true);
			List<PointDB> neighbours = naiveRegionQuery(map, point, eps);
			if(neighbours.size() < minPoints)
				point.setCluster("NONE");
			else {
				expandCluster(map, point, neighbours, currentCluster, eps, minPoints);
				currentCluster++;
			}
		}
		System.out.println("DBSCAN: Klasteryzacja ukoñczona!");
		System.out.println("DBSCAN: Zidentyfikowano " + currentCluster + " klastrów.");
		return points;
	}
	
	@SuppressWarnings("unchecked")
	private static List<PointDB>[][] createPointMap(List<PointDB> allPoints) {
		List<PointDB>[][] map = new LinkedList[Configuration.getInstance().longitudeMapSize+1][Configuration.getInstance().latitudeMapSize+1];
		for (int i = 0; i <= Configuration.getInstance().longitudeMapSize; i++)
			for (int j = 0; j <= Configuration.getInstance().latitudeMapSize; j++) {
				map[i][j] = new LinkedList<PointDB>();
			}
		for (PointDB point : allPoints) {
			map[point.getLongitudeIndex()][point.getLatitudeIndex()].add(point);
		}
		return map;
	}
	
	private static List<PointDB> initializeLocations(List<Location> locations, double[] minMaxes) {
		double minLongitude = minMaxes[0];
		double minLatitude = minMaxes[1];
		double maxLongitude = minMaxes[2];
		double maxLatitude = minMaxes[3];
		List<PointDB> points = new LinkedList<>();
		for (Location location : locations) {
			points.add(new PointDB(location, minLongitude, minLatitude, maxLongitude, maxLatitude));
		}
		return points;
	}
	
	private static void expandCluster(List<PointDB>[][] map, PointDB point, List<PointDB> neighbours, int currentCluster, double eps, int minPoints) {
		point.setCluster("area" + currentCluster);
		for (PointDB nPoint : neighbours) {
			
			if(!nPoint.isVisited()) {
				nPoint.setVisited(true);
				List<PointDB> nNeighbours = naiveRegionQuery(map, nPoint, eps);
				if(nNeighbours.size() > minPoints) {
					expandCluster(map, nPoint, nNeighbours, currentCluster, eps, minPoints);
				}
			}
			if(nPoint.getCluster() == null)
				nPoint.setCluster("area" + currentCluster);
		}
	}
	
	private static List<PointDB> regionQuery(List<PointDB> potentialNeighbours, PointDB point, double eps) {
		regionQueries++;
		//System.out.println(potentialNeighbours.size());
		double percentage = (((double)(regionQueries))/((double)(allCount)))*100.0;
		if(((int)lastPercentage) / 10 != ((int)percentage) / 10)
			System.out.println("DBSCAN: Wykonano " + (int)percentage + " %");
		lastPercentage = percentage;
		double squaredEps = eps * eps;
		List<PointDB> neighbours = new LinkedList<>();
		for (PointDB other : potentialNeighbours) {
			if(arePointsInRange(point, other, squaredEps))
				neighbours.add(other);
		}
		//System.out.println(neighbours.size());
		return neighbours;
	}
	
	private static List<PointDB> naiveRegionQuery(List<PointDB>[][] map, PointDB point, double eps) {
		List<PointDB> neighbours = new LinkedList<>();
		int i = point.getLongitudeIndex();
		int j = point.getLatitudeIndex();
		neighbours.addAll(getElements(map, i-1, j-1));
		neighbours.addAll(getElements(map, i-1, j));
		neighbours.addAll(getElements(map, i-1, j+1));
		neighbours.addAll(getElements(map, i, j-1));
		neighbours.addAll(getElements(map, i, j));
		neighbours.addAll(getElements(map, i, j+1));
		neighbours.addAll(getElements(map, i+1, j-1));
		neighbours.addAll(getElements(map, i+1, j));
		neighbours.addAll(getElements(map, i+1, j+1));
		return regionQuery(neighbours, point, eps);
	}
	
	private static List<PointDB> getElements(List<PointDB>[][] map, int i, int j) {
		if(i < 0 || j < 0 || i > Configuration.getInstance().longitudeMapSize || j > Configuration.getInstance().latitudeMapSize)
			return new LinkedList<>();
		return map[i][j];
	}
	
	private static boolean arePointsInRange(PointDB a, PointDB b, double squaredEps) {
		return Math.pow(a.getLongitude() - b.getLongitude(), 2.0) + Math.pow(a.getLatitude() - b.getLatitude(), 2.0) < squaredEps;
	}
}
