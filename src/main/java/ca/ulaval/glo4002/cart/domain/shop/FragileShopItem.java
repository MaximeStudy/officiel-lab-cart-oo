package ca.ulaval.glo4002.cart.domain.shop;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FragileShopItem extends ShopItem {

	private static final int SHIPPING_PRICE_PER_KG = 3;
	private static final int EXTRA_FRAGILE_COST = 5;

	@XmlElement
    @JsonProperty
    private String itemSku;

    @XmlElement
    @JsonProperty
    private String name;

    @XmlElement
    @JsonProperty
    private boolean available;

    @XmlElement
    @JsonProperty
    private int price;

    @XmlElement
    @JsonProperty
    private int weight;

    @XmlElement
    @JsonProperty
    // Ne pas enlever @JsonProperty, ceci sert pour le panneau d'admin où on saisit les items
    private double profitMarginPercentage;

    private FragileShopItem() {
        // JAXB
    }

    public FragileShopItem(String itemSku, String name, int price, int weight, double profitMarginPercentage,
            boolean available) {
        this.itemSku = itemSku;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.profitMarginPercentage = profitMarginPercentage;
        this.available = available;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public boolean hasSku(String sku) {
        return this.itemSku.equals(sku);
    }

    @Override
    public boolean isPrime() {
        return false;
    }

	@Override
	public int getPriceWithShippingCost() {
		int totalPrice = getPrice() + getShippingCost();
		return totalPrice;
	}

	@Override
    public int getPrice() {
        return price;
    }
	
	@Override
	public int getShippingCost() {
		int shippingPrice = getWeight() * SHIPPING_PRICE_PER_KG + EXTRA_FRAGILE_COST;
		return shippingPrice;
	}
	
	@Override
	public int getWeight() {
	    return weight;
	}
}
