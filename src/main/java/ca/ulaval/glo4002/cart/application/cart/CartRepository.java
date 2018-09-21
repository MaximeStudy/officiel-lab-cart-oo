package ca.ulaval.glo4002.cart.application.cart;

import java.util.List;

import ca.ulaval.glo4002.cart.domain.cart.Cart;

public interface CartRepository {
	
	public List<Cart> retrieveCarts();
	
	public void persistCarts(List<Cart> carts);
}
