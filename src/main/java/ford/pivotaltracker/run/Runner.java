package ford.pivotaltracker.run;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import ford.pivotaltracker.read.ConfigManager;

public class Runner {

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException, URISyntaxException {
		ResourceConfig config = new ResourceConfig();
		 config.packages("ford.pivotaltracker.service");
		 ServletHolder servlet = new ServletHolder(new ServletContainer(config));


		Server server = new Server(2222);
		 ServletContextHandler context = new ServletContextHandler(server, "/*");
		 context.addServlet(servlet, "/*");
		 
		 URL resource = Thread.currentThread().getContextClassLoader().getResource("test-basic.yml");
		ConfigManager.init(new File(resource.toURI()));

		try {
		     server.start();
		     server.join();
		 } catch (Exception e) {
			e.printStackTrace();
		} finally {
		     server.destroy();
		 }
	}
	
}
