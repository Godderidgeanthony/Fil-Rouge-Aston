package com.aston.filrouge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "produits")
public class Produit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "nameproduit", nullable = false)
	private String nameproduit;
	@Column(name = "description", nullable = false)
	private String description;
	private int quantite;
	private double prix;
	
	public Produit() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNameproduit() {
		return nameproduit;
	}

	public void setNameproduit(String nameproduit) {
		this.nameproduit = nameproduit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	@Override
	public String toString() {
		return "Produit [id=" + id + ", nameproduit=" + nameproduit + ", description=" + description + ", quantite="
				+ quantite + ", prix=" + prix + "]";
	}

	public Produit(String nameproduit, String description, int quantite, double prix) {
		super();
		this.nameproduit = nameproduit;
		this.description = description;
		this.quantite = quantite;
		this.prix = prix;
	}
	
	
	
}
