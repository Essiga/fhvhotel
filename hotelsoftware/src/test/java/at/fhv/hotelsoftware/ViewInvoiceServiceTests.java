package at.fhv.hotelsoftware;

import at.fhv.hotelsoftware.application.api.ViewInvoiceService;
import at.fhv.hotelsoftware.application.dto.InvoiceDTO;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.model.*;
import at.fhv.hotelsoftware.domain.model.exceptions.BookingNotFoundException;
import at.fhv.hotelsoftware.domain.model.exceptions.InvoiceAlreadyCreatedException;
import at.fhv.hotelsoftware.domain.model.valueobjects.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class ViewInvoiceServiceTests {

    @Autowired
    private ViewInvoiceService viewInvoiceService;

    @MockBean
    private BookingRepository bookingRepository;


    @Test
    public void given_invoicealreadycreated_when_getinvoice_then_returninvoicedtoswithcorrectlineitems() throws InvoiceAlreadyCreatedException, BookingNotFoundException {
        //given
        GuestId guestId = new GuestId(UUID.randomUUID());
        Guest guest = new Guest(guestId, "Fabian", "Egartner", "Jahngasse 1", "6800", "Dornbirn", "Austria", "066023874", "abc@test.de");

        List<LineItem> lineItems = new ArrayList<>();
        lineItems.add(new LineItem(RoomCategory.SINGLE.toString(), 1, RoomCategory.SINGLE.getPrice()));
        lineItems.add(new LineItem(RoomCategory.DOUBLE.toString(), 1, RoomCategory.DOUBLE.getPrice()));
        lineItems.add(new LineItem(RoomCategory.SUPERIOR.toString(), 1, RoomCategory.SUPERIOR.getPrice()));

        Booking booking = Booking.builder().
                bookingId(new BookingId(UUID.randomUUID())).
                cancellationDeadLine(null).
                guestId(guestId).
                bookingStatus(BookingStatus.PENDING).
                voucherCode(new VoucherCode("test")).
                singleRoom(1).
                doubleRoom(1).
                superiorRoom(1).
                build();

        booking.createInvoice(guest);
        Mockito.when(bookingRepository.findBookingById(booking.getBookingId())).thenReturn(Optional.of(booking));

        //when
        List<InvoiceDTO> invoiceDTOs = viewInvoiceService.findInvoiceByBookingId(booking.getBookingId());

        //then
        for (int i = 0; i < lineItems.size(); i++){
            assertEquals(lineItems.get(i), invoiceDTOs.get(0).getLineItems().get(i));
        }
    }

    @Test
    public void given_novalidbookingid_when_findinvoice_then_throwbookingnotfoundexception() throws InvoiceAlreadyCreatedException, BookingNotFoundException {
        //given
        GuestId guestId = new GuestId(UUID.randomUUID());
        Guest guest = new Guest(guestId, "Fabian", "Egartner", "Jahngasse 1", "6800", "Dornbirn", "Austria", "066023874", "abc@test.de");

        Booking booking = Booking.builder().
                bookingId(new BookingId(UUID.randomUUID())).
                cancellationDeadLine(null).
                guestId(guestId).
                bookingStatus(BookingStatus.PENDING).
                voucherCode(new VoucherCode("test")).
                singleRoom(1).
                doubleRoom(1).
                superiorRoom(1).
                build();

        booking.createInvoice(guest);

        Mockito.when(bookingRepository.findBookingById(booking.getBookingId())).thenReturn(Optional.of(booking));

        //when...then
        assertThrows(BookingNotFoundException.class, () -> viewInvoiceService.findInvoiceByBookingId(new BookingId(UUID.randomUUID())));
    }
}
