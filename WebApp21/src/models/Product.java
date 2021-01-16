package models;

public class Product {
	private int productId;
	private String productName;
	private String productCategory;
	private String productMaker;
	
	public Product(int productId, String productName, String productCategory, String productMaker) {
		this.setProductId(productId);
		this.setProductName(productName);
		this.setProductCategory(productCategory);
		this.setProductMaker(productMaker);
	}
	
	public Product() {}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductMaker() {
		return productMaker;
	}

	public void setProductMaker(String productMaker) {
		this.productMaker = productMaker;
	}

}
