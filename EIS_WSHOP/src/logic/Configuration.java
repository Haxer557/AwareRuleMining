package logic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import model.BatteryLevel;
import model.HumidityLevel;
import model.PressureLevel;
import model.RainValue;
import model.TemperatureLevel;
import model.TrafficValue;
import model.VolumeLevel;
import model.WindStrength;

public class Configuration {
	//singleton pattern
	private static Configuration configuration = new Configuration();
	public static Configuration getInstance() {
		return configuration;
	}
	private Configuration() {
		try {
			readConfig();
		} catch (IOException e) {
			System.out.println("B³¹d podczas wczytywania pliku konfiguracyjnego.");
			System.out.println("Zostan¹ wykorzystane dane domyœlne...");
			seed();
		}
	}
	
	//config values
	public long singleTimeSpan;
	public int longitudeMapSize;
	public int latitudeMapSize;
	public String outputFile;
	//db
	public String databaseUrl;
	public String username;
	public String password;
	//dbscan
	public double dbscanEps;
	public int dbscanMinNeighbours;
	
	public Map<BatteryLevel, Double> batteryLevelThresholds;
	public Map<HumidityLevel, Double> humidityThresholds;
	public Map<TrafficValue, Long> trafficThresholds;
	public Map<PressureLevel, Double> pressureThresholds;
	public Map<TemperatureLevel, Double> temperatureThresholds;
	public Map<VolumeLevel, Double> volumeThresholds;
	public Map<WindStrength, Double> windThresholds;
	public Map<RainValue, Double> rainThresholds;
	
	private void seed() {
		singleTimeSpan = 600000L;
		longitudeMapSize = 100;
		latitudeMapSize = 100;
		outputFile = "C:\\weka.arff";
		
		databaseUrl = "jdbc:mysql://localhost:557/aware";
		username = "aware";
		password = "zaware";
		
		dbscanEps = 0.00001;
		dbscanMinNeighbours = 20;
		
		batteryLevelThresholds = new HashMap<>();
		batteryLevelThresholds.put(BatteryLevel.BL_VERY_LOW, 0.2);
		batteryLevelThresholds.put(BatteryLevel.BL_LOW, 0.4);
		batteryLevelThresholds.put(BatteryLevel.BL_MEDIUM, 0.6);
		batteryLevelThresholds.put(BatteryLevel.BL_HIGH, 0.8);
		
		humidityThresholds = new HashMap<>();
		humidityThresholds.put(HumidityLevel.HL_DRY, 0.33);
		humidityThresholds.put(HumidityLevel.HL_AVERAGE, 0.66);
		
		trafficThresholds = new HashMap<>();
		trafficThresholds.put(TrafficValue.TV_VERY_LOW, 50000L);
		trafficThresholds.put(TrafficValue.TV_LOW, 500000L);
		trafficThresholds.put(TrafficValue.TV_MEDIUM, 1000000L);
		trafficThresholds.put(TrafficValue.TV_HIGH, 3000000L);
		
		pressureThresholds = new HashMap<>();
		pressureThresholds.put(PressureLevel.PL_VERY_LOW, 990.0);
		pressureThresholds.put(PressureLevel.PL_LOW, 1005.0);
		pressureThresholds.put(PressureLevel.PL_NORMAL, 1020.0);
		pressureThresholds.put(PressureLevel.PL_HIGH, 1030.0);
		
		temperatureThresholds = new HashMap<>();
		temperatureThresholds.put(TemperatureLevel.TL_FREEZING, 0.0);
		temperatureThresholds.put(TemperatureLevel.TL_COLD, 10.0);
		temperatureThresholds.put(TemperatureLevel.TL_COOL, 20.0);
		temperatureThresholds.put(TemperatureLevel.TL_HOT, 30.0);
		
		volumeThresholds = new HashMap<>();
		volumeThresholds.put(VolumeLevel.VL_LOW, 0.33);
		volumeThresholds.put(VolumeLevel.VL_MEDIUM, 0.66);
		
		windThresholds = new HashMap<>();
		windThresholds.put(WindStrength.WS_WEAK, 6.0);
		windThresholds.put(WindStrength.WS_STRONG, 12.0);
		
		rainThresholds = new HashMap<>();
		rainThresholds.put(RainValue.RV_MIST, 1.0);
		rainThresholds.put(RainValue.RV_RAIN, 5.0);
	}
	
	private void readConfig() throws IOException {
		System.out.println("Wczytywanie konfiguracji...");
		
		String configPath = "config.properties";
		PropertyReader properties = new PropertyReader(configPath);
		
		singleTimeSpan = properties.getLong("singleTimeSpan");
		longitudeMapSize = properties.getInt("longitudeMapSize");
		latitudeMapSize = properties.getInt("latitudeMapSize");
		outputFile = properties.getString("outputFile");
		
		databaseUrl = properties.getString("url");
		username = properties.getString("username");
		password = properties.getString("password");
		
		dbscanEps = properties.getDouble("dbscanEps");
		dbscanMinNeighbours = properties.getInt("dbscanMinNeighbours");
		
		batteryLevelThresholds = new HashMap<>();
		batteryLevelThresholds.put(BatteryLevel.BL_VERY_LOW, properties.getDouble("THRESHOLD_BL_VERY_LOW"));
		batteryLevelThresholds.put(BatteryLevel.BL_LOW, properties.getDouble("THRESHOLD_BL_LOW"));
		batteryLevelThresholds.put(BatteryLevel.BL_MEDIUM, properties.getDouble("THRESHOLD_BL_MEDIUM"));
		batteryLevelThresholds.put(BatteryLevel.BL_HIGH, properties.getDouble("THRESHOLD_BL_HIGH"));
		
		humidityThresholds = new HashMap<>();
		humidityThresholds.put(HumidityLevel.HL_DRY, properties.getDouble("THRESHOLD_HL_DRY"));
		humidityThresholds.put(HumidityLevel.HL_AVERAGE, properties.getDouble("THRESHOLD_HL_AVERAGE"));
		
		trafficThresholds = new HashMap<>();
		trafficThresholds.put(TrafficValue.TV_VERY_LOW, properties.getLong("THRESHOLD_TV_VERY_LOW"));
		trafficThresholds.put(TrafficValue.TV_LOW, properties.getLong("THRESHOLD_TV_LOW"));
		trafficThresholds.put(TrafficValue.TV_MEDIUM, properties.getLong("THRESHOLD_TV_MEDIUM"));
		trafficThresholds.put(TrafficValue.TV_HIGH, properties.getLong("THRESHOLD_TV_HIGH"));
		
		pressureThresholds = new HashMap<>();
		pressureThresholds.put(PressureLevel.PL_VERY_LOW, properties.getDouble("THRESHOLD_PL_VERY_LOW"));
		pressureThresholds.put(PressureLevel.PL_LOW, properties.getDouble("THRESHOLD_PL_LOW"));
		pressureThresholds.put(PressureLevel.PL_NORMAL, properties.getDouble("THRESHOLD_PL_NORMAL"));
		pressureThresholds.put(PressureLevel.PL_HIGH, properties.getDouble("THRESHOLD_PL_HIGH"));
		
		temperatureThresholds = new HashMap<>();
		temperatureThresholds.put(TemperatureLevel.TL_FREEZING, properties.getDouble("THRESHOLD_TL_FREEZING"));
		temperatureThresholds.put(TemperatureLevel.TL_COLD, properties.getDouble("THRESHOLD_TL_COLD"));
		temperatureThresholds.put(TemperatureLevel.TL_COOL, properties.getDouble("THRESHOLD_TL_COOL"));
		temperatureThresholds.put(TemperatureLevel.TL_HOT, properties.getDouble("THRESHOLD_TL_HOT"));
		
		volumeThresholds = new HashMap<>();
		volumeThresholds.put(VolumeLevel.VL_LOW, properties.getDouble("THRESHOLD_VL_LOW"));
		volumeThresholds.put(VolumeLevel.VL_MEDIUM, properties.getDouble("THRESHOLD_VL_MEDIUM"));
		
		windThresholds = new HashMap<>();
		windThresholds.put(WindStrength.WS_WEAK, properties.getDouble("THRESHOLD_WS_WEAK"));
		windThresholds.put(WindStrength.WS_STRONG, properties.getDouble("THRESHOLD_WS_STRONG"));
		
		rainThresholds = new HashMap<>();
		rainThresholds.put(RainValue.RV_MIST, properties.getDouble("THRESHOLD_RV_MIST"));
		rainThresholds.put(RainValue.RV_RAIN, properties.getDouble("THRESHOLD_RV_RAIN"));
	}
}
