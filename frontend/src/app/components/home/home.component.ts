import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Product } from '../../classes/product';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {
  ngOnInit(): void {
    Promise.all([
      this.loadAllProducts()
    ])
    .catch ((error)=>{
      console.log(`Error loading data: ${error}`)
    })
  }
  
  private productList: Product[] = [];
  private productService = inject(ProductService);
  
  get products(): Product[] {
    return this.productList;
  }
  
  hasProducts(): boolean {
    return this.productList.length > 0;
  }
  
  loadAllProducts() {
    this.productService.collectAllProducts()
    .then((response) => {
      // Convert plain objects to Product instances
      this.productList = response.data.map((productData: any) => {
        const product = new Product();
        product.setId(productData.id);
        product.setName(productData.name);
        product.setPrice(productData.price);
        product.setPoints(productData.points);
        product.setMenusIdes(productData.menusIdes);
        product.setImageUrl(productData.imageUrl);
        product.setProductDescription(productData.productDescription);
        product.setProductCategoryId(productData.productCategoryId);
        return product;
      });
    })
  }

  scrollToMenu(): void {
    const menuSection = document.getElementById('menu-section');
    if (menuSection) {
      menuSection.scrollIntoView({ behavior: 'smooth' });
    }
  }
}