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
ng new ui-bookstore --minimal true --inline-style true --prefix bs --routing true --skip-git true --skip-install true --strict true --style css --force --packageManager npm
cd ui-bookstore

ng add @angular/material --defaults=true
npm install
ng serve
```




ng generate component number-generate
ng generate component book-list
ng generate component book-form
ng generate component book-detail
ng generate component book-delete
ng generate component book-random
