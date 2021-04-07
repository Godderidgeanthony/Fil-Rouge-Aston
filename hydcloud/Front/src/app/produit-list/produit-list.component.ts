import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Produit } from '../produit';

import { ProduitService } from '../produit.service';

@Component({
  selector: 'app-produit-list',
  templateUrl: './produit-list.component.html',
  styleUrls: ['./produit-list.component.css']
})
export class ProduitListComponent implements OnInit {
produits:Observable<Produit[]>;
  constructor(private produitService: ProduitService,
    private router: Router) { } 

  ngOnInit() {
    this.reloadData()
  
   
  }
  reloadData() {
    
    this.produits = this.produitService.getProduitsList().pipe();
  }
  deleteProduit(id: number) {
    this.produitService.deleteProduit(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  produitDetails(id: number){
    this.router.navigate(['detailsP', id]);
  }

  updateProduit(id: number){
    this.router.navigate(['updateP', id]);
  }
}
