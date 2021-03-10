package com.aston.filrouge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aston.filrouge.model.Produit;



@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long>{

}
