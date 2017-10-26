package ford.pivotaltracker.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ford.pivotaltracker.report.UrlBuilder;
import lombok.Data;

@Data
public class ReportConfig {
	private String name;
	private String project_id;
	@JsonIgnoreProperties(ignoreUnknown = true)
	private int max_limit;
	@JsonIgnoreProperties(ignoreUnknown = true)
	private List<String> main_tag;
	private List<Field> fields;

	public Map<String, Map<String, UrlBuilder>> urlBuilder(String baseUrl) {
		Map<String, Map<String, UrlBuilder>> resultMap = new HashMap<>();
		for (String tag : main_tag) {
			Map<String, UrlBuilder> builderMap = new HashMap<>();
			builderMap.put("all", builderInstance(baseUrl, tag));
			for (Field field : fields) {
				builderMap.put(field.getName(), field.urlBuilder(builderInstance(baseUrl, tag)));
			}
			resultMap.put(tag, builderMap);
		}
		return resultMap;
	}

	private UrlBuilder builderInstance(String baseUrl, String tag) {
		return new UrlBuilder(baseUrl).forEpic(tag).projectId(project_id).limit(max_limit);
	}
}
