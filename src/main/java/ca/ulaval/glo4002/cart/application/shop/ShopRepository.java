package ca.ulaval.glo4002.cart.application.shop;

import java.util.List;

import ca.ulaval.glo4002.cart.domain.shop.ShopItem;

public interface ShopRepository {

	public List<ShopItem> readShopFromFile();

    public void persistShop(List<ShopItem> items);
    
}
