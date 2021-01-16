package models;

public class Seller {
	private int sellerId;
	private String shopId;
	private String sellerName;
	private Long sellerPhone;
	
	public Seller(int sellerId, String shopId, String sellerName, Long sellerPhone) {
		this.setSellerId(sellerId);
		this.setShopId(shopId);
		this.setSellerName(sellerName);
		this.setSellerPhone(sellerPhone);
	}

	public Seller() {
		// TODO Auto-generated constructor stub
	}

	

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public Long getSellerPhone() {
		return sellerPhone;
	}

	public void setSellerPhone(Long sellerPhone) {
		this.sellerPhone = sellerPhone;
	}

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

	

}
