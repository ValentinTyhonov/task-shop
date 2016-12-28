package com.shop.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int total;
    private boolean paid;
    
    @ManyToOne(fetch=FetchType.EAGER)
    private User user;
    
    @ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="order_product", joinColumns=@JoinColumn(name="order_id"), inverseJoinColumns=@JoinColumn(name="product_id"))
    private List<Product> products;

    public Order() { }
    
    public Order(boolean paid) {
		this.paid = paid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
