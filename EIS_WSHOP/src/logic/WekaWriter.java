package logic;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import model.ApplicationForegroundModel;
import model.GoogleActivityModel;
import model.LocationModel;

public class WekaWriter {
	public static void writeToWekaFile(Collection<DataSet> dataSets) {
		String headers = null;
		try {
			headers = readFile(dataSets.getClass().getResource("/headers/weka_headers.txt").toURI(), Charset.defaultCharset());
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
		headers = headers.replace(">>>TIME<<<", getGroupedByValues(dataSets));
		headers = headers.replace(">>>APPLICATION<<<", getApplicationValues(dataSets));
		headers = headers.replace(">>>LOCATION<<<", getLocationValues(dataSets));
		String output = headers.replace(">>>GOOGLE_ACTIVITY<<<", getGoogleActivityValues(dataSets));
		
		for (DataSet dataSet : dataSets) {
			output += dataSet.toString() + "\r\n";
		}
		
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter( new FileWriter(Configuration.getInstance().outputFile));
			writer.write(output);
		}
		catch ( IOException e) {}
		finally {
			try {
				if ( writer != null)
					writer.close( );
			}
			catch ( IOException e) {}
		}
	}
	
	static String readFile(URI uri, Charset encoding) 
			  throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(uri));
			  return new String(encoded, encoding);
			}
	
	private static String getGroupedByValues(Collection<DataSet> dataSets) {
		Set<String> distinctValues = new HashSet<>();
		for (DataSet dataSet : dataSets) {
			distinctValues.add(dataSet.getGroupedBy().toStringTime());
		}
		String values = "";
		for (String value : distinctValues) {
			values += value + ",";
		}
		return "{" + values.substring(0, values.length()-1) + "}";
	}
	
	private static String getApplicationValues(Collection<DataSet> dataSets) {
		Set<String> distinctValues = new HashSet<>();
		for (DataSet dataSet : dataSets) {
			ApplicationForegroundModel model = dataSet.getApplicationForegroundModel();
			if(model == null)
				continue;
			distinctValues.add(model.getApplicationName());
		}
		String values = "";
		for (String value : distinctValues) {
			values += value + ",";
		}
		return "{" + values.substring(0, values.length()-1) + "}";
	}
	
	private static String getGoogleActivityValues(Collection<DataSet> dataSets) {
		Set<String> distinctValues = new HashSet<>();
		for (DataSet dataSet : dataSets) {
			GoogleActivityModel model = dataSet.getGoogleActivityModel();
			if(model == null)
				continue;
			distinctValues.add(model.getActivity());
		}
		String values = "";
		for (String value : distinctValues) {
			values += value + ",";
		}
		return "{" + values.substring(0, values.length()-1) + "}";
	}
	
	private static String getLocationValues(Collection<DataSet> dataSets) {
		Set<String> distinctValues = new HashSet<>();
		for (DataSet dataSet : dataSets) {
			LocationModel model = dataSet.getLocationModel();
			if(model == null)
				continue;
			distinctValues.add(model.getLocationName());
		}
		String values = "";
		for (String value : distinctValues) {
			values += value + ",";
		}
		return "{" + values.substring(0, values.length()-1) + "}";
	}
}
