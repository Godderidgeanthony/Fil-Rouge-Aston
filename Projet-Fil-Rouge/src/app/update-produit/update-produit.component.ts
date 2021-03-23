import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Produit } from '../produit';
import { ProduitService } from '../produit.service';

@Component({
  selector: 'app-update-produit',
  templateUrl: './update-produit.component.html',
  styleUrls: ['./update-produit.component.css']
})
export class UpdateProduitComponent implements OnInit {
  id:number;
  produit:Produit;

  constructor(private route: ActivatedRoute,private router:Router,
    private produitService:ProduitService) { }

  ngOnInit() {
    this.produit = new Produit();
    this.id = this.route.snapshot.params['id'];
    
    this.produitService.getProduit(this.id).subscribe(data => {
      console.log(data)
      this.produit = data;
    },error => console.error(error));
    
   }
   updateProduit() {
    this.produitService.updateProduit(this.id, this.produit)
      .subscribe(data => console.log(data), error => console.log(error));
    this.produit = new Produit();
    this.gotoList();
  }

  onSubmit() {
    this.updateProduit();    
  }

  gotoList() {
    this.router.navigate(['/produits']);
  }
}
