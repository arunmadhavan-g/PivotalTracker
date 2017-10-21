package ford.pivotaltracker.report;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Field {
	private String name;
	private Criteria criteria;
}
