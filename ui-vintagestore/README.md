# Building the UI

## Tooling 

Check versions:

``` 
node -v
npm -version
```

Update the last version of NG CLI  (check https://github.com/angular/angular-cli/releases) and check Angular version (https://github.com/angular/angular/releases)

```
ng --version
npm install -g @angular/cli@v9.1.12
```

# Creating the UI

## Creating the project

From the root directory 

```
ng new ui-vintagestore --minimal=true --inline-style=true --inline-template=false --prefix=bs --routing=true --skip-git=true --skip-install=true --strict=true --style=scss --force=true --package-manager=npm
```

## Installing Material Design

Install the Material Design dependencies: 

```
cd ui-vintagestore

ng add @ng-bootstrap/ng-bootstrap --defaults=true --interactive=false
ng add font-awesome --defaults=true --interactive=false
ng add jquery --defaults=true --interactive=false
ng add popper --defaults=true --interactive=false
npm install
ng serve
```

The module imports and exports all the needed Material design components and should look like that:

```
import {NgModule} from '@angular/core';
import {
  MatButtonModule,
  MatCheckboxModule,
  MatIconModule
} from "@angular/material";

@NgModule({
  imports: [
    MatButtonModule,
    MatCheckboxModule,
    MatIconModule
  ],
  exports: [
    MatButtonModule,
    MatCheckboxModule,
    MatIconModule
  ]
})
export class MaterialModule {
}
```

In `angular.json`:

``` 
"styles": [
  "../node_modules/bootstrap/scss/bootstrap.scss",
  "src/jumbotron.scss",
  "src/styles.scss",
  "../node_modules/font-awesome/scss/font-awesome.scss"
],
"scripts": [
  "../node_modules/jquery/dist/jquery.slim.js",
  "../node_modules/bootstrap/dist/js/bootstrap.js"
]
```

## Template




## Business modules

```
ng generate module book/book --flat=true --module=app.module.ts --routing=true --route=book
ng generate module number/number --flat=true --module=app.module.ts --routing=true --route=number
ng generate module inventory/inventory --flat=true --module=app.module.ts --routing=true --route=inventory
ng generate module admin/admin --flat=true --module=app.module.ts --routing=true --route=admin
```

## Components

Generate all the needed components

``` 
ng generate component number/number-generate --module=number/number.module.ts --skipSelector=true --inline-template=false  --skip-tests=true
ng generate component book/book-list --module=book/book.module.ts --skipSelector=true --inline-template=false  --skip-tests=true
ng generate component book/book-form --module=book/book.module.ts --skipSelector=true --inline-template=false  --skip-tests=true
ng generate component book/book-detail --module=book/book.module.ts --skipSelector=true --inline-template=false  --skip-tests=true
ng generate component book/book-delete --module=book/book.module.ts --skipSelector=true --inline-template=false  --skip-tests=true
ng generate component book/book-random --module=book/book.module.ts --skipSelector=true --inline-template=false  --skip-tests=true
```

## Swagger Code Gen

Swagger CodeGen (https://github.com/swagger-api/swagger-codegen) is used to generate the TypeScript code from the OpenAPI Doc. 
To get some help on the commands:

```
swagger-codegen version
swagger-codegen -h
swagger-codegen generate -h
```

To generate the code from the Number and Book REST endpoint.

```
swagger-codegen generate --lang typescript-angular --output src/app/shared --input-spec http://127.0.0.1:8701/openapi
swagger-codegen generate --lang typescript-angular --output src/app/shared --input-spec http://127.0.0.1:8702/openapi
```
