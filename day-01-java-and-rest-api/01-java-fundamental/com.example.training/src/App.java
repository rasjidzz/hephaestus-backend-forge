import java.util.List;

import Model.Customer;
import Services.CustomerService;

public class App {
    public static void main(String[] args) {
        List<Customer> customers;
        CustomerService customerService = new CustomerService();
        customerService.createCustomer("Budi Santoso", "budi@mail.com", "08123456789");
        customerService.createCustomer("Siti Aminah", "siti@mail.com", "08222222222");
        customerService.createCustomer("Test", "test@mail.com", "0811");

        customers = customerService.getAllCustomer();
        System.out.println("Muhammad Risjad Shidqi Febian - 74525");
        System.out.println("\nCustomer List : ");
        int i = 1;
        for (Customer customer : customers) {
            System.out.println(i + " - " + customer.getFullName() + " - " + customer.getEmail() + " - "
                    + customer.getPhoneNumber());
            i++;
        }
        Customer cust1 = customerService.getCustomerById(1L);
        System.out.println("\nCustomer Detail : ");
        cust1.getDisplayName();

        customerService.updateCustomerEmail(1L, "emailbudibaru@mail.com");
        cust1 = customerService.getCustomerById(1L);
        System.out.println("\nCustomer Detail (After Update): ");
        cust1.getDisplayName();

        customerService.deleteCustomer(3L);
        System.out.println("\nCustomer List (After Delete): ");
        customers = customerService.getAllCustomer();
        i = 1;
        for (Customer customer : customers) {
            System.out.println(i + " - " + customer.getFullName() + " - " + customer.getEmail() + " - "
                    + customer.getPhoneNumber());
            i++;
        }

    }
}
