package com.aston.filrouge;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.aston.filrouge.model.Produit;

@SpringBootTest
class ProjetFilRougeApplicationTests {

	@Test
	void contextLoads() {
		
	}

	@Test
	void test1() {
		Produit p = new Produit();
		System.err.println(p.getDescription());
		assertTrue(p.getDescription().isEmpty());
		
	}
}
