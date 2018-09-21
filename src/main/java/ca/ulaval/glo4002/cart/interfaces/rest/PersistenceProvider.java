package ca.ulaval.glo4002.cart.interfaces.rest;

import ca.ulaval.glo4002.cart.application.cart.CartRepository;
import ca.ulaval.glo4002.cart.application.cart.CartRepositoryInMemory;
import ca.ulaval.glo4002.cart.application.cart.CartRepositoryXML;
import ca.ulaval.glo4002.cart.application.shop.ShopRepository;

public class PersistenceProvider {
    public static CartRepository getCartRepository() {
    	
    	CartRepository cartRepository;
    	
        if (System.getProperty("store").equalsIgnoreCase("xml")) {
        	cartRepository = new CartRepositoryXML();
        } else {
        	cartRepository = new CartRepositoryInMemory();
        }
		return cartRepository;
    }
    
    // Idem pour ShopRepository
    
    public static ShopRepository getShopRepository() {
        if (System.getProperty("store").equalsIgnoreCase("xml")) {
            // xml
        } else {
            // in memory
        }
		return null;
    }
}