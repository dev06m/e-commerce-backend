package com.luv2code.ecommerce.dao;

import com.luv2code.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin("http://localhost:4200") // with this we can accept calls from web browser scripts
public interface ProductRepository extends JpaRepository<Product, Long> { // buradaki iki parametrelerden ilki hangi hangi entity'e bagli olacagi, ikincisi de o entry nin primary key inin tipi

    Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);
    // findByIdCategory -> query method, "id" -> match by category id
    // behind the scenes; spring will execute a query similar to " SELECT  * FROM product where category_id=?"
        // so spring data rest automatically expose endpoint as follows. . . http://localhost:8080/api/products/search/findByCategoryId?id=2

    // SELECT * FROM Product WHERE p.name LIKE CONCAT ('%', :name, '%)
    // http://localhost:8080/api/products/search/findByNameContaining?name=Python
    Page<Product> findByNameContaining(@RequestParam("name") String name, Pageable pageable);
}
