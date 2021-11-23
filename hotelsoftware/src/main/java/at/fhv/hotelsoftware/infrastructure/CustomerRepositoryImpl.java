package at.fhv.hotelsoftware.infrastructure;

import at.fhv.hotelsoftware.domain.api.CustomerRepository;
import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.Customer;
import at.fhv.hotelsoftware.domain.model.CustomerId;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Component
public class CustomerRepositoryImpl implements CustomerRepository {


    @PersistenceContext
    private EntityManager em;

    @Override
    public void addCustomer(Customer customer) {
        this.em.persist(customer);
    }

    @Override
    public Optional<Customer> findCustomerById(CustomerId customerId) {
        TypedQuery<Customer> query = this.em.createQuery("FROM Customer WHERE customer_Id = :customerId", Customer.class);
        query.setParameter("customerId", customerId.getCustomerId());
        Optional<Customer> customer = query.getResultStream().findFirst();

        return customer;
    }
}
