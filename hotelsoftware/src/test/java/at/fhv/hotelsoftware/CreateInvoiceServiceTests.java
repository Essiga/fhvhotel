package at.fhv.hotelsoftware;

import at.fhv.hotelsoftware.application.api.CreateInvoiceService;
import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.api.CustomerRepository;
import at.fhv.hotelsoftware.domain.model.*;
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
public class CreateInvoiceServiceTests {

    @Autowired
    private CreateInvoiceService createInvoiceService;

    @MockBean
    private BookingRepository bookingRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    public void given_existingbooking_when_createinvoice_then_invoicecreated() throws BookingNotFoundException, InvoiceAlreadyCreatedException, CustomerNotFoundException {
        //given
        CustomerId customerId = new CustomerId(UUID.randomUUID());
        Customer customer = new Guest(customerId, "Fabian", "Egartner", "Jahngasse 1", "6800", "Dornbirn", "Austria", "066023874", "abc@test.de");
        CustomerData customerData = CustomerData.fromCustomer(customer);

        List<LineItem> lineItems = new ArrayList<LineItem>();
        lineItems.add(new LineItem(RoomCategory.SINGLE.toString(), 1, RoomCategory.SINGLE.getPrice()));
        lineItems.add(new LineItem(RoomCategory.DOUBLE.toString(), 1, RoomCategory.DOUBLE.getPrice()));
        lineItems.add(new LineItem(RoomCategory.SUPERIOR.toString(), 1, RoomCategory.SUPERIOR.getPrice()));

        Invoice invoice = new Invoice(new InvoiceNumber(UUID.randomUUID()), lineItems, customerData);

        Booking booking = Booking.builder().
                withBookingId(new BookingId(UUID.randomUUID())).
                withCancellationDeadLine(null).
                withCustomerId(customerId).
                withBookingStatus(BookingStatus.PENDING).
                withVoucherCode(new VoucherCode("test")).
                withSingleRoom(1).
                withDoubleRoom(1).
                withSuperiorRoom(1).
                build();


        Mockito.when(bookingRepository.findBookingById(booking.getBookingId())).thenReturn(Optional.of(booking));
        Mockito.when(customerRepository.findCustomerById(booking.getCustomerId())).thenReturn(Optional.of(customer));

        //when
        createInvoiceService.createInvoice(booking.getBookingId());


        //then
        for (int i = 0; i < lineItems.size(); i++){
            assertEquals(lineItems.get(i), booking.getInvoices().get(0).getLineItems().get(i));
        }

        assertEquals(invoice.getInvoiceStatus(), booking.getInvoices().get(0).getInvoiceStatus());
        assertEquals(invoice.getInvoiceDate(), booking.getInvoices().get(0).getInvoiceDate());


    }


    @Test
    public void given_novalidcustomerid_when_createinvoice_then_throw_customernotfoundexception() {
        //given
        CustomerId customerId = new CustomerId(UUID.randomUUID());
        Customer customer = new Guest(customerId, "Fabian", "Egartner", "Jahngasse 1", "6800", "Dornbirn", "Austria", "066023874", "abc@test.de");

        Booking booking = Booking.builder().
                withBookingId(new BookingId(UUID.randomUUID())).
                withCancellationDeadLine(null).
                withCustomerId(customerId).
                withBookingStatus(BookingStatus.PENDING).
                withVoucherCode(new VoucherCode("test")).
                withSingleRoom(1).
                withDoubleRoom(1).
                withSuperiorRoom(1).
                build();

        Booking booking2 = Booking.builder().
                withBookingId(new BookingId(UUID.randomUUID())).
                withCancellationDeadLine(null).
                withCustomerId(new CustomerId(UUID.randomUUID())).
                withBookingStatus(BookingStatus.PENDING).
                withVoucherCode(new VoucherCode("test")).
                withSingleRoom(1).
                withDoubleRoom(1).
                withSuperiorRoom(1).
                build();

        Mockito.when(bookingRepository.findBookingById(booking2.getBookingId())).thenReturn(Optional.of(booking2));
        Mockito.when(customerRepository.findCustomerById(booking.getCustomerId())).thenReturn(Optional.of(customer));

        //when...then
        assertThrows(CustomerNotFoundException.class, () -> createInvoiceService.createInvoice(booking2.getBookingId()));




    }

    @Test
    public void given_novalidbookingid_when_createinvoice_then_throw_bookingnotfoundexception() {
        //given
        CustomerId customerId = new CustomerId(UUID.randomUUID());

        Booking booking = Booking.builder().
                withBookingId(new BookingId(UUID.randomUUID())).
                withCancellationDeadLine(null).
                withCustomerId(customerId).
                withBookingStatus(BookingStatus.PENDING).
                withVoucherCode(new VoucherCode("test")).
                withSingleRoom(1).
                withDoubleRoom(1).
                withSuperiorRoom(1).
                build();

        Mockito.when(bookingRepository.findBookingById(booking.getBookingId())).thenReturn(Optional.of(booking));


        //when...then
        assertThrows(BookingNotFoundException.class, () -> createInvoiceService.createInvoice(new BookingId(UUID.randomUUID())));

    }
}
