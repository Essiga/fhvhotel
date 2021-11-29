package at.fhv.hotelsoftware.infrastructure;

import at.fhv.hotelsoftware.domain.api.InvoiceRepository;
import at.fhv.hotelsoftware.domain.model.BookingId;
import at.fhv.hotelsoftware.domain.model.Invoice;
import at.fhv.hotelsoftware.domain.model.InvoiceNumber;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Component
public class InvoiceRepositoryImpl implements InvoiceRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addInvoice(Invoice invoice) {this.em.persist(invoice);}

    @Override
    public Optional<Invoice> findInvoiceByInvoiceNumber(InvoiceNumber invoiceNumber) {

        TypedQuery<Invoice> query = this.em.createQuery("FROM Invoice WHERE invoice_number = :invoiceNumber", Invoice.class);
        query.setParameter("invoiceNumber", invoiceNumber.getInvoiceNumber());
        Optional<Invoice> invoice = query.getResultStream().findFirst();

        return invoice;
    }

    @Override
    public List<Invoice> findInvoicesByBookingId(BookingId bookingId) {

        TypedQuery<Invoice> query = this.em.createQuery("FROM Invoice WHERE booking_id = :bookingId", Invoice.class);
        query.setParameter("bookingId", bookingId.getBookingId());
        List<Invoice> invoices = query.getResultList();

        return invoices;
    }
}
