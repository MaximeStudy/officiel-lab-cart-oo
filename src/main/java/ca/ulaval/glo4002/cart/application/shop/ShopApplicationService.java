package ca.ulaval.glo4002.cart.application.shop;

import java.util.List;

import ca.ulaval.glo4002.cart.domain.shop.ShopItem;

public interface ShopApplicationService {
	
	public abstract List<ShopItem> listAvailableItems();
	
	public ShopItem findItemBySku(String sku);
}
