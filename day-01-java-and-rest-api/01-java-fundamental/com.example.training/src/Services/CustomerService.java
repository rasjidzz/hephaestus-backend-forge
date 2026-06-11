package Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Customer;

public class CustomerService {
    private Map<Long, Customer> customerStorage = new HashMap<>();
    private Long sequence = 1L;

    public Customer createCustomer(String fullName, String email, String phoneNumber) {

        Customer newCust = new Customer(sequence, fullName, email, phoneNumber);
        customerStorage.put(sequence, newCust);
        sequence++;
        return newCust;
    }

    public Customer getCustomerById(Long id) {
        Customer cust = customerStorage.get(id);
        return cust;
    }

    public List<Customer> getAllCustomer() {
        return new ArrayList<>(customerStorage.values());
    }

    public void updateCustomerEmail(Long id, String email) {
        Customer cust = getCustomerById(id);
        cust.setEmail(email);
    }

    public void deleteCustomer(Long id) {
        customerStorage.remove(id);
    }

}
