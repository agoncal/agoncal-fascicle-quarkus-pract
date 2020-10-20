import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { path: '', loadChildren: () => import('./book/book.module').then(m => m.BookModule) },
  { path: 'book-random', loadChildren: () => import('./book/book.module').then(m => m.BookModule) },
  { path: 'inventory', loadChildren: () => import('./inventory/inventory.module').then(m => m.InventoryModule) },
  { path: 'number-generate', loadChildren: () => import('./number/number.module').then(m => m.NumberModule) },
  { path: '**', loadChildren: () => import('./book/book.module').then(m => m.BookModule) },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
