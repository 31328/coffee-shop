import { Component, inject, OnInit } from '@angular/core';
import { Category } from '../../../classes/category';
import { CategoryService } from '../../../services/category.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-category-list',
  imports: [CommonModule],
  standalone: true,
  templateUrl: './category-list.component.html',
  styleUrl: './category-list.component.css',
})
export class CategoryListComponent implements OnInit {
  ngOnInit(): void {
    Promise.all([this.loadAllCategories()]).catch((error) => {
      console.log(`error loading data:  ${error}`);
    });
  }
  private categoryList: Category[] = [];
  private categoryService = inject(CategoryService);

  loadAllCategories() {
    this.categoryService.collectAllCategories().then((response) => {
      this.categoryList = response.data.map((categoryData: any) => {
        const category = new Category();
        category.setId(categoryData.id);
        category.setName(categoryData.name);
        category.setProductsIdes(categoryData.productsIdes);
        return category;
      });
    });
  }

  getCategoryList() {
    return this.categoryList;
  }
}
