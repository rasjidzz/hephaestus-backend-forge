import java.util.List;

import Model.Customer;
import Services.CustomerService;

public class App {
    public static void main(String[] args) {
        List<Customer> customers;
        CustomerService customerService = new CustomerService();
        customerService.createCustomer("Budi Santoso", "budi@mail.com", "08123456789");
        customerService.createCustomer("Siti Aminah", "siti@mail.com", "08222222222");

        customers = customerService.getAllCustomer();
        for (Customer customer : customers) {
            int i = 1;
            System.out.println(i + " - " + customer.getFullName() + " - " + customer.getEmail() + " - "
                    + customer.getPhoneNumber());
        }
        Customer cust1 = customerService.getCustomerById(2L);
        System.out.println("Customer Detail : ");
        cust1.getDisplayName();
    }
}
