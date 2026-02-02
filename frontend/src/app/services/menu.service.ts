import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class MenuService {
private targetUrl= 'http://localhost:8080/menus';

collectAllMenus(){
  return axios.get(`${this.targetUrl}/listAll`);
}
}
