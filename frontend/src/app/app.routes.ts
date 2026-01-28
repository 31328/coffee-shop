import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';

export const routes: Routes = [
    
{
        path: ``,
        component: HomeComponent,   // sada je root "/"
    },
    {
        path: `**`,
        redirectTo: `/`,           
        pathMatch: `full`
    }
];
