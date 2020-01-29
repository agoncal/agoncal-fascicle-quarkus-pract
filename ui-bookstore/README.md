# Building the UI

## Tooling 

Check versions:

``` 
node -v
npm -version
```

Update the last version of NG CLI  (check https://github.com/angular/angular-cli/releases)

```
ng --version
npm install -g @angular/cli@v9.0.0-rc.10
```

# Creating the UI

## Creating the project

From the root directory 

```
ng new ui-bookstore --minimal=true --inline-style=true --inlineTemplate=false --prefix=bs --routing=true --skip-git=true --skip-install=true --strict=true --style=css --force --packageManager=npm
cd ui-bookstore

npm install
ng serve
```

## Installing Material Design

Install the Material Design dependencies: 

```
ng add @angular/material --defaults=true --interactive=false
ng add @angular/cdk --defaults=true --interactive=false
```

Create a shared Material module

```
ng generate module shared/material --flat 
```

The module imports and exports all the needed Material design components and should look like that:

```
import {NgModule} from '@angular/core';
import {MatButtonModule} from "@angular/material/button";
import {MatCheckboxModule} from "@angular/material/checkbox";

@NgModule({
  imports: [
    MatButtonModule,
    MatCheckboxModule
  ],
  exports: [
    MatButtonModule,
    MatCheckboxModule
  ]
})
export class MaterialModule {
}
```

In the main `app.module.ts` import both `BrowserAnimationsModule` and our `MaterialModule`

```
  imports: [
    BrowserModule,
    MaterialModule,
    BrowserAnimationsModule,
```

## Template


## Components

ng generate component number-generate
ng generate component book-list
ng generate component book-form
ng generate component book-detail
ng generate component book-delete
ng generate component book-random
