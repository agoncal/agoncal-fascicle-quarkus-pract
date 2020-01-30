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
npm install -g @angular/cli@v9.0.0-rc.11
```

# Creating the UI

## Creating the project

From the root directory 

```
ng new ui-bookstore --minimal=true --inline-style=true --inlineTemplate=false --prefix=bs --routing=true --skip-git=true --skip-install=true --strict=true --style=scss --force --packageManager=npm
```

## Installing Material Design

Install the Material Design dependencies: 

```
cd ui-bookstore

ng add @angular/material --defaults=true --interactive=false
ng add @angular/cdk --defaults=true --interactive=false
ng add @angular/flex-layout --defaults=true --interactive=false

npm install
ng serve
```

Create a shared Material module 

```
ng generate module shared/material --flat 
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

## Template




## Business modules

```
ng generate module book/book --flat --module=app.module.ts --routing=true --route=book
ng generate module number/number --flat --module=app.module.ts --routing=true --route=number
ng generate module inventory/inventory --flat --module=app.module.ts --routing=true --route=inventory
```

## Components

Generate all the needed components

``` 
ng generate component number/number-generate --module=number/number.module.ts --skipSelector=true
ng generate component book/book-list --module=book/book.module.ts --skipSelector=true
ng generate component book/book-form --module=book/book.module.ts --skipSelector=true
ng generate component book/book-detail --module=book/book.module.ts --skipSelector=true
ng generate component book/book-delete --module=book/book.module.ts --skipSelector=true
ng generate component book/book-random --module=book/book.module.ts --skipSelector=true
```
