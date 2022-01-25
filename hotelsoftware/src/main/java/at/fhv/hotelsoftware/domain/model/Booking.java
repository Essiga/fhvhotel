package at.fhv.hotelsoftware.domain.model;

import at.fhv.hotelsoftware.domain.api.BookingRepository;
import at.fhv.hotelsoftware.domain.model.exceptions.*;
import at.fhv.hotelsoftware.domain.model.valueobjects.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Getter
@NoArgsConstructor
public class Booking {

    private Long id;
    private BookingId bookingId;
    private GuestId guestId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDate cancellationDeadLine;
    private Integer singleRoom;
    private Integer doubleRoom;
    private Integer superiorRoom;
    private VoucherCode voucherCode;
    private BookingStatus bookingStatus;
    private List<Invoice> invoices;

    @Builder
    public Booking(BookingId bookingId, GuestId guestId, LocalDate checkInDate, LocalDate checkOutDate, LocalDate cancellationDeadLine, Integer singleRoom, Integer doubleRoom, Integer superiorRoom, VoucherCode voucherCode, BookingStatus bookingStatus) {
        this.bookingId = bookingId;
        this.guestId = guestId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.cancellationDeadLine = cancellationDeadLine;
        this.singleRoom = singleRoom;
        this.doubleRoom = doubleRoom;
        this.superiorRoom = superiorRoom;
        this.voucherCode = voucherCode;
        this.bookingStatus = bookingStatus;
        this.invoices = new ArrayList<>();
    }

    public void confirmBooking(){
        this.bookingStatus = BookingStatus.CONFIRMED;
    }

    public void checkIn(){
        this.bookingStatus = BookingStatus.CHECKEDIN;
    }

    public void complete(){
        this.bookingStatus = BookingStatus.COMPLETED;
    }

    public Invoice createInvoice(Guest guest) throws InvoiceAlreadyCreatedException {

        int duration = (int) DAYS.between(checkInDate, checkOutDate);

        if (invoices.isEmpty()) {
            List<LineItem> lineItems = new ArrayList<>();

            if (singleRoom > 0) {
                lineItems.add(new LineItem(RoomCategory.SINGLE.toString(), singleRoom, duration, RoomCategory.SINGLE.getPrice()));
            }

            if (doubleRoom > 0) {
                lineItems.add(new LineItem(RoomCategory.DOUBLE.toString(), doubleRoom, duration, RoomCategory.DOUBLE.getPrice()));
            }

            if (superiorRoom > 0) {
                lineItems.add(new LineItem(RoomCategory.SUPERIOR.toString(), superiorRoom, duration, RoomCategory.SUPERIOR.getPrice()));
            }

            GuestData guestData = GuestData.fromGuest(guest);
            Invoice invoice = new Invoice(new InvoiceNumber(UUID.randomUUID()), lineItems, guestData);
            invoices.add(invoice);

            return invoice;
        }
        else {
            throw new InvoiceAlreadyCreatedException("An invoice for this booking has already been created.");
        }
    }

    public Invoice splitInvoice(InvoiceNumber invoiceNumber, List<LineItem> lineItemsToSplit) throws InvoiceNotFoundException, LineItemsMismatchException, NoLineItemsException, AllLineItemsRemovedException {

        List<LineItem> nonEmptyLineItems = removeEmptyLineItems(lineItemsToSplit);

        if(nonEmptyLineItems.isEmpty()){
            throw new NoLineItemsException("Line items must not be empty.");
        }

        Invoice originalInvoice = getOriginalInvoice(invoiceNumber);

        if(containsAllLineItems(lineItemsToSplit, originalInvoice.getLineItems())){

            updateOriginalInvoice(lineItemsToSplit, originalInvoice);

            Invoice splitInvoice = new Invoice(new InvoiceNumber(UUID.randomUUID()), nonEmptyLineItems, originalInvoice.getGuestData());

            invoices.add(splitInvoice);
            return splitInvoice;
        }
        else{
            throw new LineItemsMismatchException("At least one line item not found in invoice.");
        }

    }

    public Invoice splitInvoiceWithoutRecipient(InvoiceNumber invoiceNumber, List<LineItem> lineItemsToSplit) throws InvoiceNotFoundException, LineItemsMismatchException, NoLineItemsException, AllLineItemsRemovedException {

        List<LineItem> nonEmptyLineItems = removeEmptyLineItems(lineItemsToSplit);

        if(nonEmptyLineItems.isEmpty()){
            throw new NoLineItemsException("Line items must not be empty.");
        }

        Invoice originalInvoice = getOriginalInvoice(invoiceNumber);

        if(containsAllLineItems(lineItemsToSplit, originalInvoice.getLineItems())){

            updateOriginalInvoice(lineItemsToSplit, originalInvoice);

            Invoice splitInvoice = new Invoice(new InvoiceNumber(UUID.randomUUID()), nonEmptyLineItems, new GuestData());

            invoices.add(splitInvoice);
            return splitInvoice;
        }
        else{
            throw new LineItemsMismatchException("At least one line item not found in invoice.");
        }

    }

    private Invoice getOriginalInvoice(InvoiceNumber invoiceNumber) throws InvoiceNotFoundException {
        Invoice originalInvoice = null;
        for (Invoice i : invoices) {
            if (i.getInvoiceNumber().getInvoiceNumber().equals(invoiceNumber.getInvoiceNumber())) {
                originalInvoice = i;
                break;
            }
        }

        if (originalInvoice == null) {
            throw new InvoiceNotFoundException("Invoice with invoice number: " + invoiceNumber.getInvoiceNumber().toString() + " not found.");
        }
        return originalInvoice;
    }

    private List<LineItem> removeEmptyLineItems(List<LineItem> lineItems) {
        return lineItems.stream().filter(l -> l.getAmount()>0).collect(Collectors.toList());
    }

    private void updateOriginalInvoice(List<LineItem> lineItemsToSplit, Invoice originalInvoice) throws AllLineItemsRemovedException {
        List<LineItem> remainingLineItems = getRemainingInvoiceLineItems(lineItemsToSplit, originalInvoice);
        List<LineItem> nonEmptyRemainingLineItems = removeEmptyLineItems(remainingLineItems);

        if(nonEmptyRemainingLineItems.isEmpty()){
            throw new AllLineItemsRemovedException("Cannot remove all line items from an invoice.");
        }
        Invoice updatedOriginalInvoice = new Invoice(originalInvoice.getInvoiceNumber(), nonEmptyRemainingLineItems, originalInvoice.getGuestData());
        invoices.set(invoices.indexOf(originalInvoice), updatedOriginalInvoice);
    }

    private List<LineItem> getRemainingInvoiceLineItems(List<LineItem> lineItemsToRemove, Invoice invoice) {
        List<LineItem> remainingLineItems = new LinkedList<LineItem>();

        for (LineItem lineItem : lineItemsToRemove) {
            for(LineItem invoiceLineItem : invoice.getLineItems()){
                if(lineItem.getName().equals(invoiceLineItem.getName())){
                    if(lineItem.getAmount() <= invoiceLineItem.getAmount() &&
                            lineItem.getDuration() == invoiceLineItem.getDuration() &&
                            lineItem.getPrice() == invoiceLineItem.getPrice()){

                        remainingLineItems.add(new LineItem(lineItem.getName(), invoiceLineItem.getAmount() - lineItem.getAmount(),  lineItem.getDuration(), lineItem.getPrice()));

                    }
                }
            }
        }
        return remainingLineItems;
    }

    private boolean containsAllLineItems(List<LineItem> lineItems, List<LineItem> invoiceLineItems){

        int count = 0;
        for (LineItem lineItem : lineItems) {
            for(LineItem invoiceLineItem : invoiceLineItems){
                if(lineItem.getName().equals(invoiceLineItem.getName())){
                    count++;
                    if(lineItem.getAmount() > invoiceLineItem.getAmount() ||
                            lineItem.getDuration() != invoiceLineItem.getDuration() ||
                            lineItem.getPrice() != invoiceLineItem.getPrice()){
                        return false;
                    }
                }
            }
        }
        if(count > invoiceLineItems.size()){
            return false;
        }

        return true;
    }
}
