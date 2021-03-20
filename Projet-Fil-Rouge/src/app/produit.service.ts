import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({ 
  providedIn: 'root'
})
export class ProduitService {
  private baseUrl = 'http://164.132.212.109:8080/projetfilrouge/api/produits';

  constructor(private http: HttpClient) { }
  getProduit(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createProduit(produit: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, produit);
  }

  updateProduit(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteProduit(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getProduitsList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
