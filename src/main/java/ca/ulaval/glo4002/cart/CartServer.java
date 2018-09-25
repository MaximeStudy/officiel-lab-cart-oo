package ca.ulaval.glo4002.cart;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import ca.ulaval.glo4002.cart.application.shop.ItemNotFoundException;
import ca.ulaval.glo4002.cart.context.ApplicationContext;
import ca.ulaval.glo4002.cart.interfaces.rest.CartResource;
import ca.ulaval.glo4002.cart.interfaces.rest.ShopResource;
import ca.ulaval.glo4002.cart.interfaces.rest.PersistenceExceptionMapper;
import ca.ulaval.glo4002.cart.interfaces.rest.filters.CORSFilter;

public class CartServer {
	private static final int PORT = 7222;

	private ApplicationContext applicationContext;
	
	public CartServer() {
		this.applicationContext = new ApplicationContext();
	}
	
	public static void main(String[] args) {
		//new CartServer().run();
		new ApplicationContext().run();
	}

	public void run() {
		Server server = new Server(PORT);
		ServletContextHandler contextHandler = new ServletContextHandler(server, "/");

		
		// Configuration manuelle au lieu du package scanning -> mettre ca dans le contexte
		ResourceConfig packageConfig = new ResourceConfig()
				.registerInstances(createClientResource(), createCartResource())
				.registerInstances(new PersistenceExceptionMapper(), new ItemNotFoundException())
				.register(new CORSFilter()); 

		ServletContainer container = new ServletContainer(packageConfig);
		ServletHolder servletHolder = new ServletHolder(container);

		contextHandler.addServlet(servletHolder, "/*");

		try {
			server.start();
			server.join();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			server.destroy();
		}
	}

	private CartResource createCartResource() {
		return new CartResource(applicationContext.getCartApplicationService(), applicationContext.getShopApplicationService());
	}

	private Object createClientResource() {
		return new ShopResource(applicationContext.getShopApplicationService());
	}
}