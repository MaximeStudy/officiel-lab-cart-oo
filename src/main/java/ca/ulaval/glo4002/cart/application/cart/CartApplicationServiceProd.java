package ca.ulaval.glo4002.cart.application.cart;

import java.util.List;

import ca.ulaval.glo4002.cart.domain.cart.Cart;
import ca.ulaval.glo4002.cart.domain.cart.CartItem;
import ca.ulaval.glo4002.cart.domain.shop.ShopItem;
import ca.ulaval.glo4002.cart.interfaces.rest.PersistenceProvider;

public class CartApplicationServiceProd implements CartApplicationService{
	 private CartRepository cartRepository;

		public CartApplicationServiceProd() {
			this.cartRepository = PersistenceProvider.getCartRepository();
		}

		public Cart findOrCreateCartForClient(String email) {
			List<Cart> carts = cartRepository.retrieveCarts();

			Cart cart = getCartByOwner(email, carts);

			return cart;
		}

		public void addItemToCart(String email, ShopItem item) {
			List<Cart> carts = cartRepository.retrieveCarts();
			Cart cart = getCartByOwner(email, carts);

			cart.addItem(new CartItem(item.getName(), 1, item.getPriceWithShippingCost()));

			cartRepository.persistCarts(carts);
		}
		
		private Cart getCartByOwner(String email, List<Cart> carts) {
			return carts.stream().filter(c -> c.ownerEmail.equals(email)).findFirst().orElseGet(() -> {
				Cart newCart = new Cart(email);
				carts.add(newCart);
				cartRepository.persistCarts(carts);
				return newCart;
			});
		}
}
