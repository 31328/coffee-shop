package com.hanghae.coffeeshop.repositories;

import com.hanghae.coffeeshop.dto.ProductDto;
import com.hanghae.coffeeshop.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<ProductEntity> findByName(String name);

    @Query(
            value = """
                        SELECT COALESCE(SUM(p.points), 0)
                        FROM products p
                        JOIN product_menus pm ON p.id = pm.product_id
                        WHERE pm.menu_id = :menuId
                    """,
            nativeQuery = true
    )
    Integer sumProductPointsByMenuId(@Param("menuId") Long menuId);

    @Query(
            value = """
                        SELECT COALESCE(SUM(p.price), 0)
                        FROM products p
                        JOIN product_menus pm ON p.id = pm.product_id
                        WHERE pm.menu_id = :menuId
                    """,
            nativeQuery = true
    )
    Double sumProductPriceByMenuId(@Param("menuId") Long menuId);

    @Query(value = "SELECT * FROM products WHERE category_id = :categoryId", nativeQuery = true)
    List<ProductEntity> findAllByCategoryId(@Param("categoryId") Long categoryId);
}
