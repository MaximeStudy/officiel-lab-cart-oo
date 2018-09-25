package ca.ulaval.glo4002.cart.context;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import ca.ulaval.glo4002.cart.application.cart.CartApplicationService;
import ca.ulaval.glo4002.cart.application.cart.CartApplicationServiceDemo;
import ca.ulaval.glo4002.cart.application.cart.CartApplicationServiceProd;
import ca.ulaval.glo4002.cart.application.shop.ItemNotFoundException;
import ca.ulaval.glo4002.cart.application.shop.ShopApplicationService;
import ca.ulaval.glo4002.cart.application.shop.ShopApplicationServiceDemo;
import ca.ulaval.glo4002.cart.application.shop.ShopApplicationServiceProd;
import ca.ulaval.glo4002.cart.interfaces.rest.CartResource;
import ca.ulaval.glo4002.cart.interfaces.rest.PersistenceExceptionMapper;
import ca.ulaval.glo4002.cart.interfaces.rest.ShopResource;
import ca.ulaval.glo4002.cart.interfaces.rest.filters.CORSFilter;

public class ApplicationContext implements Runnable {
	
	private static final int PORT = 7222;

	private CartApplicationService cartApplicationService;

	private ShopApplicationService shopApplicationService;
	
	public ApplicationContext() {
		setCartApplicationService();
		setShopApplicationService();		
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
	
	
	private void setCartApplicationService() {		
	    if (modeParameterPropertyIsDemo()) {
	    	cartApplicationService = new CartApplicationServiceDemo();
	    } 
	    else {
	    	cartApplicationService = new CartApplicationServiceProd();
	    }
	}
	
	private void setShopApplicationService() {
	    if (modeParameterPropertyIsDemo()) {
	    	shopApplicationService = new ShopApplicationServiceDemo();
	    } 
	    else 
	    {
	    	shopApplicationService = new ShopApplicationServiceProd();
	    }
	}
	
	private static boolean modeParameterPropertyIsDemo() {
		return System.getProperty("mode").equalsIgnoreCase("demo");
	}
	
	public CartApplicationService getCartApplicationService() {
		return cartApplicationService;
	}
	
	public ShopApplicationService getShopApplicationService() {
		return shopApplicationService;
	}
	
	private CartResource createCartResource() {
		return new CartResource(cartApplicationService, shopApplicationService);
	}

	private Object createClientResource() {
		return new ShopResource(shopApplicationService);
	}
}
