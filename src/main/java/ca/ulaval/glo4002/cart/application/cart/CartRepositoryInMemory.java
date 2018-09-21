package ca.ulaval.glo4002.cart.application.cart;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.cart.domain.cart.Cart;

public class CartRepositoryInMemory implements CartRepository {

	ArrayList<Cart> listOfCart = new ArrayList<Cart>();
	
	@Override
	public List<Cart> retrieveCarts() {
		return listOfCart;
	}

	@Override
	public void persistCarts(List<Cart> carts) {
		
	}

}
