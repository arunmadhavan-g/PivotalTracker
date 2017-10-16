package ford.pivotaltracker.service2;

import org.junit.BeforeClass;
import org.junit.Test;

public class Runner {

	
	@BeforeClass
	public static void proxy() {
		 System.setProperty("http.proxyHost", "proxyvipfmcc.nb.ford.com");
	        System.setProperty("http.proxyPort", "83");
	        System.setProperty("https.proxyHost", "proxyvipfmcc.nb.ford.com");
	        System.setProperty("https.proxyPort", "83");
	}
	
	@Test
	public void testName() throws Exception {
		StoryConfig config = new StoryConfig("150543886", "145019619", "135438735");
		Service service = new Service("1431430");
		
		StoryGroups groups = new StoryGroups();
		groups.populateStories(service, config, "v1.9");
		
		System.out.println(groups);
	}
}
