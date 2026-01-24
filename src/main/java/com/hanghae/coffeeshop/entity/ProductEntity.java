package com.hanghae.coffeeshop.entity;

import jakarta.persistence.*;


import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "products")
public class ProductEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer points;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_menus", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "menu_id"))
    private List<MenuEntity> menus;
    @Column(name = "product_description", nullable = false, length = 255)
    private String productDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity productCategory;

    @Column(nullable = false, name = "img_url")
    private String imageUrl;

    public CategoryEntity getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(CategoryEntity productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public List<MenuEntity> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuEntity> menus) {
        this.menus = menus;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
