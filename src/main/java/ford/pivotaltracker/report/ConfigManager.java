package ford.pivotaltracker.report;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class ConfigManager {

	private static List<ReportConfig>  reportConfigurations;
	private static Map<String, List<Map<String, URI>>> transformed;
	
	public static void init(File file) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		reportConfigurations =  mapper.readValue(file, ReportConfigs.class)
					.getReport();
	}

	static List<ReportConfig> configs() {
		return reportConfigurations;
	}

	public static void transform() {
		
	}

	public static Map<String, List<Map<String, URI>>> transformed() {
		return transformed;
	}

}
