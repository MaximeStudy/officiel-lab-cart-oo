package ca.ulaval.glo4002.cart.application.cart;

import ca.ulaval.glo4002.cart.domain.cart.Cart;
import ca.ulaval.glo4002.cart.domain.shop.ShopItem;

public interface CartApplicationService {

	public Cart findOrCreateCartForClient(String email);

	public void addItemToCart(String email, ShopItem item);
}
