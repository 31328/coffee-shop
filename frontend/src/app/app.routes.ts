import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { CategoryListComponent } from './components/categories/category-list/category-list.component';
import { MenuListComponent } from './components/menu/menu-list/menu-list.component';

export const routes: Routes = [
  {
    path: ``,

    component: HomeComponent, // sada je root "/"
  },

  {
    path: `categories`,
    component: CategoryListComponent,
  },

  {
    path: `menus`,
    component: MenuListComponent,
  },

  {
    path: `**`,
    redirectTo: `/`,
    pathMatch: `full`,
  },
];
