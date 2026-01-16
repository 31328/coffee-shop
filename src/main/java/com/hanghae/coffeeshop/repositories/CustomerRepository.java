package com.hanghae.coffeeshop.repositories;

import com.hanghae.coffeeshop.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    @Query("""
    SELECT c
    FROM CustomerEntity c
    WHERE c.registrationTime <= :registrationCutoff
      AND NOT EXISTS (
          SELECT o
          FROM OrderEntity o
          WHERE o.cart = c.cart
            AND o.createTime >= :orderCutoff
      )
""")
    List<CustomerEntity> findInactiveCustomersForOneYear(
            @Param("registrationCutoff") Timestamp registrationCutoff,
            @Param("orderCutoff") Timestamp orderCutoff
    );
    @Query(value = "SELECT * FROM CUSTOMERS WHERE USER_ID= :userId", nativeQuery = true)
    Optional<CustomerEntity> findByUserId(@Param("userId") Long userId);

    Optional<CustomerEntity> findByPhone(String phone);
}
