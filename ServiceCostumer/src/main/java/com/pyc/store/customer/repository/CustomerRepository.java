package com.pyc.store.customer.repository;

import com.pyc.store.customer.entity.Customer;
import com.pyc.store.customer.entity.Region;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    
    public Customer findByNumberId(String numberId);
    public List<Customer> findByLastName(String lastName);
    public List<Customer> findByRegion(Region region);
}
