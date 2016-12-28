package com.shop.dto;

public class ProductDto {
	
	private int id;
	private String name;
	private int price;
	private String image;

	public ProductDto() { }

	public ProductDto(int id, String name, int price, String image) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
