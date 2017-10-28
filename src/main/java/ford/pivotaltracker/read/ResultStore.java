package ford.pivotaltracker.read;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import ford.pivotaltracker.beans.tracker.Story;

public class ResultStore {

	private static Map<String , Map<String, Map<String, List<Story>>>> result;
	
	public static void refresh() throws URISyntaxException, IOException{
		Reader reader = new Reader();
		result = reader.process();
	}
	
	public static <T> T transform(Function<Map<String , Map<String, Map<String, List<Story>>>>, T> tranformer) {
		return tranformer.apply(result);
	}
	
}
