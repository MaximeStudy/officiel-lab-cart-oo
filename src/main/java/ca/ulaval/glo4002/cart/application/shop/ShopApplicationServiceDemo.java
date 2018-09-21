package ca.ulaval.glo4002.cart.application.shop;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import ca.ulaval.glo4002.cart.domain.shop.FragileShopItem;
import ca.ulaval.glo4002.cart.domain.shop.PrimeShopItem;
import ca.ulaval.glo4002.cart.domain.shop.ShopItem;
import ca.ulaval.glo4002.cart.domain.shop.StandardShopItem;
import ca.ulaval.glo4002.cart.interfaces.rest.PersistenceProvider;

public class ShopApplicationServiceDemo implements ShopApplicationService {
    private final ShopRepository shopRepository;

    public ShopApplicationServiceDemo() {
        this.shopRepository = PersistenceProvider.getShopRepository();
    }

    public List<ShopItem> listAvailableItems() {
        List<ShopItem> items = shopRepository.readShopFromFile();
        if (items.isEmpty()) {
            Logger.getGlobal().info("Prefilling data in the shop for the demo");
            prefillDemoData();
            items = shopRepository.readShopFromFile();
        }

        return items.stream().filter(ShopItem::isAvailable).collect(Collectors.toList());
    }

    public ShopItem findItemBySku(String sku) {
        return listAvailableItems().stream()
                .filter(x -> x.hasSku(sku))
                .findFirst()
                .orElseThrow(ItemNotFoundException::new);
    }
    
    private void prefillDemoData() {
        addItem(new StandardShopItem("1251521", "Peanuts", 5, 1, 1.20, true));
        addItem(new PrimeShopItem("236637", "Clean Code", 35, 2, 0.50, false));
        addItem(new StandardShopItem("235265", "Détendeur Mares Abyss Navy 22", 999, 5, 0.15, true));
        addItem(new StandardShopItem("276101", "Imprimante 3D", 2341, 31, 0.60, true));
        addItem(new PrimeShopItem("818113", "GoPro", 650, 1, 4.60, true));
        addItem(new StandardShopItem("51-153", "Peinture à numéro", 1, 2, 1.40, true));
        addItem(new FragileShopItem("14568", "Victor", 1, 2, 1, true));
    }

    private void addItem(ShopItem item) {
        List<ShopItem> items = shopRepository.readShopFromFile();
        items.add(item);

        shopRepository.persistShop(items);
    }
}
