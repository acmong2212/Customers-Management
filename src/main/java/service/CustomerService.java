package service;

import dao.CustomerDao;
import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CustomerService {
    @Autowired
    CustomerDao customerDao;

    public List<Customer> getAllCustomer() {
        return customerDao.getListCustomer();
    }

    public void createCustomer(Customer customer) {
        customerDao.save(customer);
    }

    public void editCustomer(Customer customer) {
        customerDao.edit(customer);
    }

    public Customer findById(int id) {
        return customerDao.findById(id);
    }

    public void deleteCustomer(int id) {
        customerDao.delete(id);
    }
}
