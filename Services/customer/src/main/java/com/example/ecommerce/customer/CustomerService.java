package com.example.ecommerce.customer;

import com.ctc.wstx.util.StringUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository repo;
    private final CustomerMapper mapper;

    public String createCustomer(@Valid CustomerRequest request) {
        var customer = repo.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(@Valid CustomerRequest request) {
        var customer = repo.findById(request.id()).orElseThrow( () -> new CustomerNotFoundException(
                format("cannot update customer::No customer found with the privided Id:: %s" , request.id())
        ));
        mergeCustomer(customer , request);
        repo.save(customer);
    }

    private void mergeCustomer(Customer customer, @Valid CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstName())){
            customer.setFirstName(request.firstName());
        }
        if(StringUtils.isNotBlank(request.lastName())){
            customer.setLastName(request.lastName());
        }
        if(StringUtils.isNotBlank(request.email())){
            customer.setEmail(request.email());
        }
        if(request.lastName() != null){
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return repo.findAll().stream().map(mapper::fromCustomer).collect(Collectors.toList());
    }

    public Boolean existsById(String customerId) {
        return repo.findById(customerId).isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return repo.findById(customerId).map(mapper::fromCustomer).orElseThrow( () -> new CustomerNotFoundException(format("cannot find customer with the privided Id:: %s", customerId)));
    }

    public void deleteCustomer(String customerId) {
        repo.deleteById(customerId);
    }
}
