import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
private targetUrl='http://localhost:8080/products';

collectAllProducts(){
  return axios.get(`${this.targetUrl}/listAll`);
}
}
