import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
private targetUrl='http://localhost:8080/categories';

collectAllCategories(){
  return axios.get(`${this.targetUrl}/listAll`);
}
}
