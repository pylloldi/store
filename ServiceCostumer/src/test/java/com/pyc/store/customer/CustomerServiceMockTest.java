package com.pyc.store.customer;

import com.pyc.store.customer.entity.Customer;
import com.pyc.store.customer.entity.Region;
import com.pyc.store.customer.repository.CustomerRepository;
import com.pyc.store.customer.service.CustomerService;
import com.pyc.store.customer.service.CustomerServiceImpl;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerServiceMockTest {
    
    @Mock
    private CustomerRepository customerRepository;
    
    private CustomerService customerService;
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        
        customerService = new CustomerServiceImpl(customerRepository);
        
        Customer custumer01 = Customer.builder()
                .numberId("12345678")
                .firstName("Ylloldi")
                .lastName("Paulo")
                .email("correo@correo.com")
                .region(Region.builder().id(1L).build())
        .build();
        
        Mockito.when(customerRepository.findAll())
                .thenReturn(Collections.singletonList(custumer01));        
        
    }
    
    @Test
    public void whenValidFindAll_ThenReturnListCustomer() {
        List<Customer> founds = customerService.findCustomerAll();
        
        Assertions.assertThat(founds.size()).isEqualTo(1);
        
        
    }
    
    
}
