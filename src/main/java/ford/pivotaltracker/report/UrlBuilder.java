package ford.pivotaltracker.report;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class UrlBuilder {

	private final String baseURL;
	private String projectId;
	private List<String> labels = new ArrayList<>();

	public UrlBuilder(String baseURL) {
		this.baseURL = baseURL;
	}

	public UrlBuilder projectId(String projectId) {
		this.projectId = projectId;
		return this;
	}

	public UrlBuilder addLabel(String label) {
		labels.add(label);
		return this;
	}

	public String build() throws URISyntaxException, UnsupportedEncodingException {
		return new StringBuilder(String.format("%s%s/search?", baseURL, projectId))
					.append("query=")
					.append(buildQuery(labels))
					.toString();
	}

	private String buildQuery(List<String> labels) throws UnsupportedEncodingException {
		StringBuilder builder = new StringBuilder();
		boolean isFirst = true;
		for(String label: labels) {
			if(!isFirst) {
				builder.append(" AND ");
			}
			isFirst = false;
			builder.append("label:\"")
					.append(label)
					.append("\"");
		}
		builder.append(" includedone:true");
		return URLEncoder.encode(builder.toString(),"UTF-8");
	}
	
}
