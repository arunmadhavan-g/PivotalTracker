package ford.pivotaltracker.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public class ReportConfig {
	private String name;
	private String project_id;
	@JsonIgnoreProperties(ignoreUnknown=true)
	private int max_limit;
	@JsonIgnoreProperties(ignoreUnknown=true)
	private String main_tag;
	private List<Field> fields;
	
	public Map<String, UrlBuilder> urlBuilder(String baseUrl) {
		Map<String, UrlBuilder> builderMap = new HashMap<>();
		builderMap.put("all", builderInstance(baseUrl));
		for(Field field: fields) {
			builderMap.put(field.getName(), field.urlBuilder(builderInstance(baseUrl)));
		}
		return builderMap;
	}
	
	private UrlBuilder builderInstance(String baseUrl) {
		return new UrlBuilder(baseUrl)
					.forEpic(main_tag)
					.projectId(project_id)
					.limit(max_limit);
	}
}
