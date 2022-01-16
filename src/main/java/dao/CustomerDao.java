package dao;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class CustomerDao {
    @Autowired
    EntityManager entityManager;

    public List<Customer> getListCustomer(){
        String queryStr = "SELECT c FROM Customer c";
        TypedQuery<Customer> query = entityManager.createQuery(queryStr, Customer.class);
        return (List<Customer>) query.getResultList();
    }

    public void save(Customer customer){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(customer);
        transaction.commit();
    }

    public void edit(Customer customer){
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(customer);
        transaction.commit();
    }

    public void delete(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(findById(id));
        transaction.commit();
    }

    public Customer findById(int id) {
        String query = "SELECT c FROM Customer c where c.id=:id";
        Customer customer = entityManager.createQuery(query, Customer.class).setParameter("id", id).getSingleResult();
        return customer;
    }
}