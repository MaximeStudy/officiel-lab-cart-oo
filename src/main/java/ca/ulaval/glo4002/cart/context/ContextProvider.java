package ca.ulaval.glo4002.cart.context;

import ca.ulaval.glo4002.cart.application.cart.CartApplicationService;
import ca.ulaval.glo4002.cart.application.cart.CartApplicationServiceDemo;
import ca.ulaval.glo4002.cart.application.cart.CartApplicationServiceProd;
import ca.ulaval.glo4002.cart.application.cart.CartRepository;
import ca.ulaval.glo4002.cart.application.shop.ShopApplicationService;
import ca.ulaval.glo4002.cart.application.shop.ShopApplicationServiceDemo;
import ca.ulaval.glo4002.cart.application.shop.ShopApplicationServiceProd;

public class ContextProvider {
	private static ContextProvider uniqueInstanceOfProvider = null;
	
	private CartApplicationService cartApplicationService;

	private ShopApplicationService shopApplicationService;

	public static ContextProvider getInstance() {
		if(uniqueInstanceOfProvider == null) {
			uniqueInstanceOfProvider = new ContextProvider();
		}
		return uniqueInstanceOfProvider;
	}
	private ContextProvider() {
		setCartApplicationService();
		setShopApplicationService();
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
}
