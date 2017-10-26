package ford.pivotaltracker.config;

import ford.pivotaltracker.report.UrlBuilder;
import lombok.Data;

@Data
public class Field {
	private String name;
	private Criteria criteria;
	
	public UrlBuilder urlBuilder(UrlBuilder builder) {
		return criteria.urlBuilder(builder);
	}
}
