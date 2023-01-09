package com.pyc.store.customer;

import com.pyc.store.customer.entity.Customer;
import com.pyc.store.customer.entity.Region;
import com.pyc.store.customer.repository.CustomerRepository;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CustomerRepositoryMockTest {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Test
    public void whenFindByRegion_thenReturnListCustomer() {
        Customer custumer01 = Customer.builder()
                .numberId("12345678")
                .firstName("Ylloldi")
                .lastName("Paulo")
                .email("correo@correo.com")
                .region(Region.builder().id(1L).build())
        .build();
        
        customerRepository.save(custumer01);
        
        List<Customer> founds = customerRepository.findByRegion(custumer01.getRegion());
        
        Assertions.assertThat(founds.size()).isEqualTo(1);
    }
}
