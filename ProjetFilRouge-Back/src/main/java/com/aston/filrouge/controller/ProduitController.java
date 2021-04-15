package com.aston.filrouge.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aston.filrouge.dto.ProduitDto;
import com.aston.filrouge.model.Produit;
import com.aston.filrouge.repository.ProduitRepository;

//@CrossOrigin(origins = "http://192.168.0.37:4200")
@CrossOrigin(origins = "http://fil-rouge-1.xyz:4200")
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ProduitController {
	@Autowired
	private ProduitRepository produitRepository;

	@GetMapping("/produits")
	public List<Produit> getAllProduits() {
		return produitRepository.findAll();
	}

	@GetMapping("/produits/{id}")
	public ResponseEntity<Produit> getProduitById(@PathVariable(value = "id") Long produitId) throws Exception
			 {
		Produit produit = produitRepository.findById(produitId)
				.orElseThrow(() -> new Exception("Produit not found for this id :: " + produitId));
		return ResponseEntity.ok().body(produit);
	}

	@PostMapping("/produits")
	public Produit createProduit(@RequestBody ProduitDto produit) {
		Produit pro	= new Produit();
		pro.setNameproduit(produit.getNameproduit());
		pro.setDescription(produit.getDescription());
		pro.setQuantite(produit.getQuantite());
		pro.setPrix(produit.getPrix());
		return produitRepository.save(pro);
	}

	@PutMapping("/produits/{id}")
	public ResponseEntity<Produit> updateProduit(@PathVariable(value = "id") Long produitId,
			 @RequestBody ProduitDto produitDetails) throws Exception {
		Produit produit = produitRepository.findById(produitId)
				.orElseThrow(() -> new Exception("Produit not found for this id :: " + produitId));

		
		
		produit.setNameproduit(produitDetails.getNameproduit());
		produit.setDescription(produitDetails.getDescription());
		final Produit updatedProduit = produitRepository.save(produit);
		return ResponseEntity.ok(updatedProduit);
	}

	@DeleteMapping("/produits/{id}")
	public Map<String, Boolean> deleteProduit(@PathVariable(value = "id") Long produitId) throws Exception
			{
		Produit produit = produitRepository.findById(produitId)
				.orElseThrow(() -> new Exception("Produit not found for this id :: " + produitId));

		produitRepository.delete(produit);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
