import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Menu } from '../../../classes/menu';
import { Product } from '../../../classes/product';
import { MenuService } from '../../../services/menu.service';
import { ProductService } from '../../../services/product.service';

@Component({
  selector: 'app-menu-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './menu-list.component.html',
  styleUrl: './menu-list.component.css',
})
export class MenuListComponent implements OnInit {
  private menuList: Menu[] = [];
  private productList: Product[] = [];
  private productNameById = new Map<number, string>();

  private menuService = inject(MenuService);
  private productService = inject(ProductService);

  ngOnInit(): void {
    Promise.all([this.loadAllMenus(), this.loadAllProducts()]).catch(
      (error) => {
        console.log(`Error loading data: ${error}`);
      },
    );
  }

  loadAllMenus(): Promise<void> {
    return this.menuService.collectAllMenus().then((response) => {
      this.menuList = response.data.map((menuData: any) => {
        const menu = new Menu();
        menu.setId(menuData.id);
        menu.setName(menuData.name);
        menu.setPoints(menuData.points);
        menu.setPrice(menuData.price);
        menu.setProductIdes(menuData.productsIdes);
        return menu;
      });
    });
  }

  loadAllProducts(): Promise<void> {
    return this.productService.collectAllProducts().then((response) => {
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

      this.buildProductNameMap();
    });
  }

  private buildProductNameMap(): void {
    this.productNameById.clear();

    for (const product of this.productList) {
      const id = product.getId();
      if (id !== undefined) {
        this.productNameById.set(id, product.getName() ?? '');
      }
    }
  }

  // Add this missing method
  getProductList(): Product[] {
    return this.productList;
  }

  getMenuList(): Menu[] {
    return this.menuList;
  }

  getProductNameById(productId?: number): string {
    return productId !== undefined
      ? (this.productNameById.get(productId) ?? '')
      : '';
  }
}
