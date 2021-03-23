import { EmployeeDetailsComponent } from './employee-details/employee-details.component';
import { CreateEmployeeComponent } from './create-employee/create-employee.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { UpdateEmployeeComponent } from './update-employee/update-employee.component';
import { ProduitListComponent } from './produit-list/produit-list.component';
import { CreateProduitComponent } from './create-produit/create-produit.component';
import { UpdateProduitComponent } from './update-produit/update-produit.component';
import { ProduitDetailsComponent } from './produit-details/produit-details.component';

const routes: Routes = [
  { path: '', redirectTo: 'employee', pathMatch: 'full' },
  { path: 'employees', component: EmployeeListComponent },
  { path: 'add', component: CreateEmployeeComponent },
  { path: 'update/:id', component: UpdateEmployeeComponent },
  { path: 'details/:id', component: EmployeeDetailsComponent },
  { path: 'produits' , component:ProduitListComponent},
  { path: 'addP' , component: CreateProduitComponent},
  { path: 'updateP/:id' , component:UpdateProduitComponent},
  { path: 'detailsP/:id', component:ProduitDetailsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
