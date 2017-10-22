package ford.pivotaltracker.report;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.apache.commons.lang3.StringUtils;

import okhttp3.Request;
import okhttp3.Request.Builder;

public class UrlBuilder {

	private final String baseURL;
	private String projectId;
	private String epic;
	private int limit = 500;
	private String beforeId;
	private String afterId;
	private String state;
	
	public UrlBuilder(String baseURL) {
		this.baseURL = baseURL;
	}

	public UrlBuilder projectId(String projectId) {
		this.projectId = projectId;
		return this;
	}

	public UrlBuilder limit(int limit) {
		this.limit = limit;
		return this;
	}
	public UrlBuilder forEpic(String epic) {
		this.epic= epic;
		return this;
	}

	public UrlBuilder beforeId(String beforeId) {
		this.beforeId = beforeId;
		return this;
	}

	public UrlBuilder afterId(String afterId) {
		this.afterId = afterId;
		return this;
	}

	public UrlBuilder withState(String state) {
		this.state = state;
		return this;
	}

	public String build() throws URISyntaxException, UnsupportedEncodingException {
		return new StringBuilder(String.format("%s%s/stories?", baseURL, projectId))
					.append("with_label=")
					.append(epic)
					.append(beforeIdQuery())
					.append(afterIdQuery())
					.append(stateQuery())
					.append(limitQuery())
					.toString();
	}

	private String stateQuery() {
		return returnQueryOrBlank(state, "with_state");
	}

	private String limitQuery() {
		return returnQueryOrBlank(Integer.toString(limit), "limit");
	}

	private String afterIdQuery() {
		return returnQueryOrBlank(afterId, "after_story_id");
	}

	private String beforeIdQuery() {
		return returnQueryOrBlank(beforeId, "before_story_id");
	}

	private String returnQueryOrBlank(String value, String queryParam) {
		return StringUtils.isEmpty(value)? "": String.format("&%s=%s",queryParam, value);
	}

	public Request buildRequest(Builder requestBuilder) throws UnsupportedEncodingException, URISyntaxException {
		return requestBuilder.url(build())
					.build();
					
	}
	
}
