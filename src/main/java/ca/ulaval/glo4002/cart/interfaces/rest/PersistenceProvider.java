package ca.ulaval.glo4002.cart.interfaces.rest;

import ca.ulaval.glo4002.cart.application.cart.CartRepository;
import ca.ulaval.glo4002.cart.application.cart.CartRepositoryInMemory;
import ca.ulaval.glo4002.cart.application.cart.CartRepositoryXML;
import ca.ulaval.glo4002.cart.application.shop.ShopRepository;
import ca.ulaval.glo4002.cart.application.shop.ShopRepositoryInMemory;
import ca.ulaval.glo4002.cart.application.shop.ShopRepositoryXML;

public class PersistenceProvider {
	
    public static CartRepository getCartRepository() {
    	
    	CartRepository cartRepository;
    	
        if (System.getProperty("store").equalsIgnoreCase("xml")) {
        	cartRepository = new CartRepositoryXML();
        } 
        else {
        	cartRepository = new CartRepositoryInMemory();
        }
        
		return cartRepository;
    }
        
    public static ShopRepository getShopRepository() {
    	
    	ShopRepository shopRepository;
    	
        if (System.getProperty("store").equalsIgnoreCase("xml")) {
        	shopRepository = new ShopRepositoryXML();
        } 
        else {
        	shopRepository = new ShopRepositoryInMemory();
        }
        
		return shopRepository;
    }
}