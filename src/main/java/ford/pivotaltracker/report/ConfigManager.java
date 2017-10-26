package ford.pivotaltracker.report;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import ford.pivotaltracker.config.ReportConfig;
import ford.pivotaltracker.config.ReportConfigs;

public class ConfigManager {

	private static final String TRACKER_BASE_URL = "https://www.pivotaltracker.com/services/v5/projects/";
	private static List<ReportConfig>  configs;
	private static Map<String, Map<String, Map<String, UrlBuilder>>> transformed;
	
	public static void init(File file) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		configs =  mapper.readValue(file, ReportConfigs.class)
					.getReport();
		transform();
	}

	static List<ReportConfig> configs() {
		return configs;
	}

	private static void transform() {
		transformed = new HashMap<>();
		for(ReportConfig config: configs) {
			transformed.put(config.getName(), config.urlBuilder(TRACKER_BASE_URL));
		}
	}

	public static Map<String, Map<String, Map<String, UrlBuilder>>> transformed() {
		return transformed;
	}

}
