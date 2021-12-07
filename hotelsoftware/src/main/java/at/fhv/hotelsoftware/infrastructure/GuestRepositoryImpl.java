package at.fhv.hotelsoftware.infrastructure;

import at.fhv.hotelsoftware.domain.api.GuestRepository;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestId;
import at.fhv.hotelsoftware.domain.model.Guest;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Component
public class GuestRepositoryImpl implements GuestRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void addGuest(Guest guest) {
        this.em.persist(guest);
    }

    @Override
    public Optional<Guest> findGuestById(GuestId guestId) {
        TypedQuery<Guest> query = this.em.createQuery("FROM Guest WHERE guest_Id = :guestId", Guest.class);
        query.setParameter("guestId", guestId.getGuestId());
        Optional<Guest> guest = query.getResultStream().findFirst();

        return guest;
    }


}
