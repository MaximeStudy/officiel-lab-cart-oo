package ca.ulaval.glo4002.cart.application.shop;

import java.util.List;
import java.util.stream.Collectors;

import ca.ulaval.glo4002.cart.domain.shop.ShopItem;
import ca.ulaval.glo4002.cart.interfaces.rest.PersistenceProvider;

public class ShopApplicationServiceProd implements ShopApplicationService{
    private final ShopRepository shopRepository;

    public ShopApplicationServiceProd() {
        this.shopRepository = PersistenceProvider.getShopRepository();
    }
    
	@Override
    public List<ShopItem> listAvailableItems() {
        List<ShopItem> items = shopRepository.readShopFromFile();

        return items.stream().filter(ShopItem::isAvailable).collect(Collectors.toList());
    }

	public ShopItem findItemBySku(String sku) {
        return listAvailableItems().stream()
                .filter(x -> x.hasSku(sku))
                .findFirst()
                .orElseThrow(ItemNotFoundException::new);
    }
}
