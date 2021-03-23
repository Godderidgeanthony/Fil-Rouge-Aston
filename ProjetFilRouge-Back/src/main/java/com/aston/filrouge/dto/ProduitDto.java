package com.aston.filrouge.dto;


public class ProduitDto {
	private String nameproduit;
	private String description;
	private int quantite;
	private double prix;
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
		return "ProduitDto [nameproduit=" + nameproduit + ", description=" + description + ", quantite=" + quantite
				+ ", prix=" + prix + "]";
	}
	public ProduitDto(String nameproduit, String description, int quantite, double prix) {
		super();
		this.nameproduit = nameproduit;
		this.description = description;
		this.quantite = quantite;
		this.prix = prix;
	}
	public ProduitDto() {
		super();
	}
	
	
}
