package ca.ulaval.glo4002.cart.application.shop;

import java.util.ArrayList;
import java.util.List;

import ca.ulaval.glo4002.cart.domain.shop.ShopItem;

public class ShopRepositoryInMemory implements ShopRepository {

	private ArrayList<ShopItem> listOfShopItem = new ArrayList<ShopItem>();

	@Override
	public List<ShopItem> readShopFromFile() {
		return listOfShopItem;
	}

	@Override
	public void persistShop(List<ShopItem> items) {
		
	}

}
