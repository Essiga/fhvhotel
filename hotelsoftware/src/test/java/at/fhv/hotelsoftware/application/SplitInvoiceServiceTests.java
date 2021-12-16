package at.fhv.hotelsoftware.application;

import at.fhv.hotelsoftware.application.api.SplitInvoiceService;
import at.fhv.hotelsoftware.application.dto.InvoiceDTO;
import at.fhv.hotelsoftware.application.dto.LineItemDTO;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.model.Booking;
import at.fhv.hotelsoftware.domain.model.Guest;
import at.fhv.hotelsoftware.domain.model.Invoice;
import at.fhv.hotelsoftware.domain.model.LineItem;
import at.fhv.hotelsoftware.domain.model.exceptions.*;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestId;
import at.fhv.hotelsoftware.domain.model.valueobjects.InvoiceNumber;
import at.fhv.hotelsoftware.domain.model.valueobjects.RoomCategory;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SplitInvoiceServiceTests {

    @Autowired
    SplitInvoiceService splitInvoiceService;

    @MockBean
    BookingRepository bookingRepository;

    @Test
    public void given_existingbookingwithinvoice_when_invoicesplit_then_twoinvoices() throws InvoiceAlreadyCreatedException, BookingNotFoundException, InvoiceNotFoundException, NoLineItemsException, LineItemsMismatchException, AllLineItemsRemovedException {
        //given
        BookingId bookingId = new BookingId(UUID.randomUUID());
        Booking booking = Booking.builder().
                singleRoom(4).
                doubleRoom(2).
                superiorRoom(1).
                checkInDate(LocalDate.now()).
                checkOutDate(LocalDate.now().plusDays(2)).
                bookingId(bookingId).
                build();
        Guest guest = new Guest(new GuestId(UUID.randomUUID()), "Fabian", "Egartner", "Jahngasse 1", "6850", "Dornbirn", "Austria", "066023874", "abc@test.de");
        Invoice invoice = booking.createInvoice(guest);

        List<LineItem> lineItems = new LinkedList<>();
        lineItems.add(new LineItem(RoomCategory.SINGLE.toString(), 2, 2, RoomCategory.SINGLE.getPrice()));
        lineItems.add(new LineItem(RoomCategory.DOUBLE.toString(), 1, 2, RoomCategory.DOUBLE.getPrice()));
        lineItems.add(new LineItem(RoomCategory.SUPERIOR.toString(), 1, 2, RoomCategory.SUPERIOR.getPrice()));

        List<LineItemDTO> lineItemDTOs = LineItemDTO.fromLineItemList(lineItems);
        double splitSum = lineItems.get(0).getTotalPrice() + lineItems.get(1).getTotalPrice() + lineItems.get(2).getTotalPrice();

        Mockito.when(bookingRepository.findBookingById(bookingId)).thenReturn(Optional.of(booking));

        //when
        InvoiceDTO splitInvoiceDTO = splitInvoiceService.splitInvoice(bookingId, invoice.getInvoiceNumber(), lineItems);

        //then
        assertEquals(splitSum, splitInvoiceDTO.getSum());

        for (int i = 0; i < lineItemDTOs.size(); i++){
            assertEquals(lineItemDTOs.get(i), splitInvoiceDTO.getLineItemDTOs().get(i));
        }

    }

    @Test
    public void given_existingbookingwithinvoice_when_invoicesplitwithwrongbookingid_then_throwbookingnotfoundexception() throws InvoiceAlreadyCreatedException {
        //given
        BookingId bookingId = new BookingId(UUID.randomUUID());
        Booking booking = Booking.builder().
                singleRoom(4).
                doubleRoom(2).
                superiorRoom(1).
                checkInDate(LocalDate.now()).
                checkOutDate(LocalDate.now().plusDays(2)).
                bookingId(bookingId).
                build();
        Guest guest = new Guest(new GuestId(UUID.randomUUID()), "Fabian", "Egartner", "Jahngasse 1", "6850", "Dornbirn", "Austria", "066023874", "abc@test.de");
        Invoice invoice = booking.createInvoice(guest);

        List<LineItem> lineItems = new LinkedList<>();
        lineItems.add(new LineItem(RoomCategory.SINGLE.toString(), 2, 2, RoomCategory.SINGLE.getPrice()));
        lineItems.add(new LineItem(RoomCategory.DOUBLE.toString(), 1, 2, RoomCategory.DOUBLE.getPrice()));
        lineItems.add(new LineItem(RoomCategory.SUPERIOR.toString(), 1, 2, RoomCategory.SUPERIOR.getPrice()));

        Mockito.when(bookingRepository.findBookingById(bookingId)).thenReturn(Optional.of(booking));

        //when...then
        assertThrows(BookingNotFoundException.class, () -> splitInvoiceService.splitInvoice(new BookingId(UUID.randomUUID()), invoice.getInvoiceNumber(), lineItems));


    }
}
