package models;

public class Shop {
	private String shopId;
	private String shopAddress;
	private Long shopPhone;
	private int isCity;
	
	public Shop(String shopId, String shopAddress, Long shopPhone, int isCity) {
		this.shopId = shopId;
		this.shopAddress = shopAddress;
		this.shopPhone = shopPhone;
		this.isCity = isCity;
	}
	
	public Shop() {}
	
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	public Long getShopPhone() {
		return shopPhone;
	}
	public void setShopPhone(Long shopPhone) {
		this.shopPhone = shopPhone;
	}
	public int getIsCity() {
		return isCity;
	}
	public void setIsCity(int isCity) {
		this.isCity = isCity;
	}

}
