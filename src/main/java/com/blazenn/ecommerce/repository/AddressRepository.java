package com.blazenn.ecommerce.repository;

import com.blazenn.ecommerce.domain.Address;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Address entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AddressRepository extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address> {

    // @Override
    // @Query(nativeQuery = true, value = "SELECT a.*, b.first_name, b.last_name, b.email FROM address AS a LEFT JOIN jhi_user AS b ON a.user_id = b.id")
    // Page<Address> findAll(Specification<Address> specification, Pageable page);

    // @Override
    // @Query(nativeQuery = true, value = "SELECT a.*, b.first_name, b.last_name, b.email FROM address AS a LEFT JOIN jhi_user AS b ON a.user_id = b.id")
    // List<Address> findAll(Specification<Address> specification);
}