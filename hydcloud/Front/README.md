# Angular8SpringbootClient

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 8.0.1.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).

************************ Code ************************

************************ HTML ************************

## /src/app/create-employee/create-employee.component.html

<h3>Créer un employé</h3>
<div [hidden]="submitted" style="width: 400px;">
    <form (ngSubmit)="onSubmit()">
        <div class="form-group">
            <label for="name">Prénom</label>
            <input type="text" class="form-control" id="firstName" required [(ngModel)]="employee.firstName" name="firstName">
        </div>

        <div class="form-group">
            <label for="name">Nom</label>
            <input type="text" class="form-control" id="lastName" required [(ngModel)]="employee.lastName" name="lastName">
        </div>

        <div class="form-group">
            <label for="name">Email</label>
            <input type="text" class="form-control" id="emailId" required [(ngModel)]="employee.emailId" name="emailId">
        </div>

        <button type="submit" class="btn btn-success">Submit</button>
    </form>
</div>

<div [hidden]="!submitted">
    <h4>You submitted successfully!</h4>
    <!-- <button class="btn btn-success" (click)="newEmployee()">Add</button> -->
</div>

## /src/app/create-produit/create-produit.component.html

<h3>Créer un produit</h3>
<div [hidden]="submitted" style="width: 400px;">
    <form (ngSubmit)="onSubmit()">
        <div class="form-group">
            <label for="name">Nom du produit</label>
            <input type="text" class="form-control" id="nameproduit" required [(ngModel)]="produit.nameproduit" name="nameproduit">
        </div>

        <div class="form-group">
            <label for="name">Description</label>
            <input type="text" class="form-control" id="description" required [(ngModel)]="produit.description" name="description">
        </div>

        <div class="form-group">
            <label for="name">Quantité</label>
            <input type="number" class="form-control" id="quantite" required [(ngModel)]="produit.quantite" name="quantite">
        </div>

        <div class="form-group">
            <label for="name">Prix</label>
            <input type="number" class="form-control" id="prix" required [(ngModel)]="produit.prix" name="prix">
        </div>


        <button type="submit" class="btn btn-success">Submit</button>
    </form>
</div>

<div [hidden]="!submitted">
    <h4>You submitted successfully!</h4>
    <!-- <button class="btn btn-success" (click)="newProduit()">AddP</button> -->
</div>

## /src/app/employee-details/employee-details.component.html

<h2>Employé détails</h2>

<hr/>
<div *ngIf="employee">
    <div>
        <label><b>Prenom: </b></label> {{employee.firstName}}
    </div>
    <div>
        <label><b>Nom: </b></label> {{employee.lastName}}
    </div>
    <div>
        <label><b>Email: </b></label> {{employee.emailId}}
    </div>
</div>

<br>
<br>
<button (click)="list()" class="btn btn-primary">Retour</button><br>

## /src/app/employee-list/employee-list.component.html

<div class="container">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3>Liste des employés</h3>
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Prénom</th>
                        <th>Nom</th>
                        <th>Email</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <tr *ngFor="let employee of employees | async">
                        <td>{{employee.firstName}}</td>
                        <td>{{employee.lastName}}</td>
                        <td>{{employee.emailId}}</td>
                        <td>
                            <button (click)="employeeDetails(employee.id)" class="btn btn-info" style="margin-left: 10px">Detail</button>
                            <button (click)="updateEmployee(employee.id)" class="btn btn-info" style="margin-left: 10px">Mise A Jour</button>
                            <button (click)="deleteEmployee(employee.id)" class="btn btn-danger" style="margin-left: 10px">Supprimer</button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

## /src/app/produit-details/produit-details.component.html

<h2>Produit Details</h2> 

<hr/>
<div *ngIf="produit">
  <div> 
    <label><b>Nom du Produit: </b></label> {{produit.nameproduit}}
  </div>
  <div>
    <label><b>Description: </b></label> {{produit.description}}
  </div>
  <div>
    <label><b>Quantite: </b></label> {{produit.quantite}}
  </div>
  <div>
    <label><b>Prix: </b></label> {{produit.prix}} Euro
  </div>
  <div>
    <label><b>Prix Total: </b></label> {{produit.prix*produit.quantite}} Euro
  </div>

</div>

<br>
<br>
<button (click)="listP()" class="btn btn-primary">Retour</button><br>

## /src/app/produit-list/produit-list.component.html

<div class="panel panel-primary">
    <div class="panel-heading">
        <h3>Liste de produits</h3>
    </div>
    <div class="panel-body">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Nom du produit</th>
                    <th>Description</th>
                </tr>
            </thead>
            <tbody>
                <tr *ngFor="let produit of produits | async">
                    <td>{{produit.nameproduit}}</td>
                    <td>{{produit.description}}</td>
                    <td>
                        <button (click)="produitDetails(produit.id)" class="btn btn-info" style="margin-left: 10px;margin-top: 10;">Detail</button>
                        <button (click)="updateProduit(produit.id)" class="btn btn-info" style="margin-left: 10px;margin-top: 10;">Mise A Jour</button>
                        <button (click)="deleteProduit(produit.id)" class="btn btn-danger" style="margin-left: 10px;margin-top: 10;">Supprimer</button>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</div>

## /src/app/update-employee/update-employee.component.html

<h3>Mise A Jour Employee</h3>
<div style="width: 400px;">
  <form (ngSubmit)="onSubmit()">
    <div class="form-group">
      <label for="name">Prenom</label>
      <input type="text" class="form-control" id="firstName" required [(ngModel)]="employee.firstName" name="firstName">
    </div>

    <div class="form-group">
      <label for="name">Nom</label>
      <input type="text" class="form-control" id="lastName" required [(ngModel)]="employee.lastName" name="lastName">
    </div> 

    <div class="form-group">
      <label for="name">Email</label>
      <input type="text" class="form-control" id="emailId" required [(ngModel)]="employee.emailId" name="emailId">
    </div>

    <button type="submit" class="btn btn-success">Submit</button>
    <button  class="btn btn-primary" >Retour</button>
  </form>
</div>

## /src/app/update-produit/update-produit.component.html

<h3>Mise A Jour Produit</h3>
<div style="width: 400px;">
  <form (ngSubmit)="onSubmit()">
    <div class="form-group">
      <label for="name">Nom du Produit</label>
      <input type="text" class="form-control" id="nameproduit" required [(ngModel)]="produit.nameproduit" name="nameproduit">
    </div>

    <div class="form-group">
      <label for="name">Description</label>
      <input type="text" class="form-control" id="description" required [(ngModel)]="produit.description" name="description">
    </div> 
    
    <div class="form-group">
      <label for="name">Quantite</label>
      <input type="number" class="form-control" id="quantite" required [(ngModel)]="produit.quantite" name="quantite">
    </div>

    <div class="form-group">
      <label for="name">Prix</label>
      <input type="number" class="form-control" id="prix" required [(ngModel)]="produit.prix" name="prix">
    </div>
   
    <td><button type="submit" class="btn btn-success">Submit</button></td>
    <td><button  class="btn btn-primary" >Retour</button></td>
    
  </form>
</div>

## /src/app/app.component.html

<div class="container">
    <nav class="navbar navbar-expand-lg navbar-light" style=" display:inline-block;">
        <!-- Links -->
        <ul class="navbar">
            <li class="nav-item">
                <a routerLink="employees" class="nav-link" routerLinkActive="active">Liste des employés</a>
            </li>
            <li class="nav-item">
                <a routerLink="add" class="nav-link" routerLinkActive="active">Ajouter des employés</a>
            </li>
            <li class="nav-item">
                <a routerLink="produits" class="nav-link" routerLinkActive="active">Liste de produits</a>
            </li>
            <li class="nav-item">
                <a routerLink="addP" class="nav-link" routerLinkActive="active">Ajouter des produits</a>
            </li>
        </ul>
    </nav>
</div>

<div class="container">
    <br>
    <h2 style="text-align: center;">{{title1}}</h2>
    <h3 style="text-align: center;">{{title2}}</h3>
    <hr>
    <div class="card">
        <div class="card-body">
            <router-outlet></router-outlet>
        </div>
    </div>

## /src/index.html

<!doctype html>
<html lang="fr">

<head>
    <meta charset="utf-8">
    <title>Gestion Employés et Produits</title>
    <base href="/">

    <meta name="viewport" content="width=device-width, initial-scale=1">

</head>

<body class="bg">
    <app-root></app-root>
    <footer>
        <div class="footer">
            <h4>Copyright © Your Website 2020</h4>
        </div>
    </footer>
</body>



</html>

************************ CSS ************************

## /src/app/create-employee/create-employee.component.css

## /src/app/create-produit/create-produit.component.css

## /src/app/employee-details/employee-details.component.css

## /src/app/employee-list/employee-list.component.css

## /src/app/produit-details/produit-details.component.css

## /src/app/produit-list/produit-list.component.css

## /src/app/update-employee/update-employee.component.css

## /src/app/update-produit/update-produit.component.css

## /src/app/app.component.css

h2 {
    font-size: 60px;
    font-weight: 800;
    @include sans-serif-font;
    color: white;
}

h3 {
    font-size: 20px;
    @include sans-serif-font;
    color: white;
}

p {
    font-family: Lato;
}

## /src/styles.css

/* You can add global styles to this file, and also import other style files */

@import '~bootstrap/dist/css/bootstrap.min.css';
.body {
    font-size: 15px;
    color: $gray-900;
    height: 100%;
    @include serif-font;
}

.navbar {
    font-weight: 800;
    list-style-type: none;
    text-align: center;
    width: 100%;
    height: 100%;
}

.nav-link {
    font-size: 20px;
    color: white;
}

h2 {
    font-size: 20px;
    font-weight: 800;
    @include sans-serif-font;
}

h4 {
    font-size: 14px;
    @include sans-serif-font;
}

.bg {
    background-image: url('/assets/img/home-bg.jpg');
    background-position: initial;
    background-repeat: no-repeat;
    background-size: cover;
    position: fixed;
    height: 100vh%;
    left: 0;
    width: 100%;
    top: 0;
}

.footer {
    flex-shrink: 0;
    flex-grow: 0;
    background: rgb(255, 255, 255);
    position: fixed;
    bottom: 0;
    width: 100%;
    padding-top: 50px;
    height: 20%;
    text-align: center;
    padding: 50px 0 65px;
}

.card {
    opacity: 0.8;
}



