package at.fhv.hotelsoftware.view;

import at.fhv.hotelsoftware.application.api.*;
import at.fhv.hotelsoftware.application.dto.*;
import at.fhv.hotelsoftware.domain.model.LineItem;
import at.fhv.hotelsoftware.domain.model.exceptions.*;
import at.fhv.hotelsoftware.domain.model.valueobjects.BookingId;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestData;
import at.fhv.hotelsoftware.domain.model.valueobjects.InvoiceNumber;
import at.fhv.hotelsoftware.view.form.BookingForm;
import at.fhv.hotelsoftware.view.form.LineItemWrapper;
import at.fhv.hotelsoftware.view.form.RecipientState;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CheckOutController {


    @Autowired
    CheckInService checkInService;

    @Autowired
    CheckOutService checkOutService;

    @Autowired
    ViewBookingService viewBookingService;

    @Autowired
    ViewGuestService viewGuestService;

    @Autowired
    ViewRoomService viewRoomService;

    @Autowired
    ViewInvoiceService viewInvoiceService;

    @Autowired
    CreateInvoiceService createInvoiceService;

    @Autowired
    SplitInvoiceService splitInvoiceService;

    private static final String CHECK_OUT_GUEST_OVERVIEW = "/checkOutGuestOverview";
    private static final String CHECK_OUT_GUEST = "/checkOutGuest";
    private static final String ERROR_URL = "/showErrorPage";
    private static final String INVOICE_SUMMARY = "/invoiceSummary";
    private static final String CREATE_INVOICE_PDF = "/pdfInvoice";
    private static final String SPLIT_INVOICE = "/splitInvoice";

    @GetMapping(CHECK_OUT_GUEST_OVERVIEW)
    public ModelAndView showCheckOutGuestOverview(@RequestParam("id") String id, Model model){

        BookingId bookingId = new BookingId(id);

        try {
            List<RoomDTO> roomDTOs = viewRoomService.findRoomsByBookingId(bookingId);
            BookingDTO bookingDTO = viewBookingService.findBookingById(bookingId);
            GuestDTO guestDTO = viewGuestService.findGuestById(bookingDTO.getGuestId());
            List<InvoiceDTO> invoiceDTOs = viewInvoiceService.findInvoicesByBookingId(bookingId);

            if (invoiceDTOs.isEmpty())
            {
                invoiceDTOs.add(createInvoiceService.createInvoice(bookingId));
            }

            model.addAttribute("guest", guestDTO);
            model.addAttribute("rooms", roomDTOs);
            model.addAttribute("booking", bookingDTO);
            model.addAttribute("invoices", invoiceDTOs);

        } catch (BookingNotFoundException e){
            return redirectToErrorPage(e.getMessage());
        }
        catch (RoomNotFoundException e){
            return redirectToErrorPage(e.getMessage());
        }
        catch (GuestNotFoundException e){
            return redirectToErrorPage(e.getMessage());
        } catch (InvoiceAlreadyCreatedException e) {
            return redirectToErrorPage(e.getMessage());
        }

        return new ModelAndView("checkOutGuestOverview");
    }

    @PostMapping(CHECK_OUT_GUEST)
    public ModelAndView checkOutGuest(@ModelAttribute("booking") BookingForm booking){

        try {
            checkOutService.checkOut(booking.getBookingId());

        } catch (BookingNotFoundException e) {
            redirectToErrorPage(e.getMessage());
        } catch (RoomNotFoundException e) {
            redirectToErrorPage(e.getMessage());
        } catch (RoomNotOccupiedException e){
            redirectToErrorPage(e.getMessage());
        }

        return new ModelAndView("redirect:"+"/");
    }

    @GetMapping(INVOICE_SUMMARY)
    public ModelAndView showInvoiceSummary(@RequestParam("bookingId") String bookingIdString, @RequestParam("invoiceNumber") String invoiceNumberString, Model model){

        BookingId bookingId = new BookingId(bookingIdString);
        InvoiceNumber invoiceNumber = new InvoiceNumber(invoiceNumberString);
        try {
            List<InvoiceDTO> invoiceDTOs = viewInvoiceService.findInvoicesByBookingId(bookingId);
            List<RoomDTO> roomDTOs = viewRoomService.findRoomsByBookingId(bookingId);
            InvoiceDTO invoiceDTO = findInvoiceByNumber(invoiceNumber, invoiceDTOs);
            BookingDTO bookingDTO = viewBookingService.findBookingById(bookingId);

            GuestData guest = invoiceDTO.getGuestData();

            LineItemWrapper lineItemWrapper = new LineItemWrapper(invoiceDTO.getLineItemDTOs());
            RecipientState recipientState =  new RecipientState();

            model.addAttribute("invoice", invoiceDTO);
            model.addAttribute("booking", bookingDTO);
            model.addAttribute("guest", guest);
            model.addAttribute("rooms", roomDTOs);
            model.addAttribute("lineItemWrapper", lineItemWrapper);
            model.addAttribute("recipientState",recipientState);

        } catch (BookingNotFoundException e) {
            return redirectToErrorPage(e.getMessage());
        } catch (InvoiceNotFoundException e) {
            return redirectToErrorPage(e.getMessage());
        } catch (RoomNotFoundException e) {
            return redirectToErrorPage(e.getMessage());
        }

        return new ModelAndView("invoiceSummary");
    }

    private InvoiceDTO findInvoiceByNumber(InvoiceNumber invoiceNumber, List<InvoiceDTO> invoiceDTOs) throws InvoiceNotFoundException {
        for (InvoiceDTO invoiceDTO : invoiceDTOs) {
            if(invoiceDTO.getInvoiceNumber().getInvoiceNumber().equals(invoiceNumber.getInvoiceNumber())){
                return invoiceDTO;
            }
        }
        throw new InvoiceNotFoundException("Invoice with Number " + invoiceNumber.getInvoiceNumber().toString() + " not found.");
    }

    @GetMapping (CREATE_INVOICE_PDF)
    public ModelAndView ShowPdf(HttpServletResponse response, @RequestParam("id") String bookingIdString, @RequestParam("invoiceNumber") String invoiceNumberString, Model model) {

        try {
            BookingId bookingId = new BookingId(bookingIdString);
            InvoiceNumber invoiceNumber = new InvoiceNumber(invoiceNumberString);
            BookingDTO bookingDTO = viewBookingService.findBookingById(bookingId);
            List<RoomDTO> roomDTO = viewRoomService.findRoomsByBookingId(bookingId);
            List<InvoiceDTO> invoiceDTOs = viewInvoiceService.findInvoicesByBookingId(bookingId);

            InvoiceDTO invoiceDTO = findInvoiceByNumber(invoiceNumber, invoiceDTOs);
            GuestData guest = invoiceDTO.getGuestData();

            if(guest == null){
                guest = new GuestData();
            }

            model.addAttribute("booking", bookingDTO);
            model.addAttribute("guest",guest);
            model.addAttribute("room", roomDTO);
            model.addAttribute("invoice", invoiceDTO);

            ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
            templateResolver.setSuffix(".html");
            templateResolver.setTemplateMode("HTML");
            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(templateResolver);

            Context context = new Context();

            context.setVariable("room", roomDTO);
            context.setVariable("guest", guest);
            context.setVariable("booking", bookingDTO);
            context.setVariable("invoice", invoiceDTO);


            String html = templateEngine.process("templates/invoice", context);

            String fileName = "invoice.pdf";
            response.addHeader("Content-disposition", "attachment;filename=" + fileName);
            response.setContentType("application/pdf");
            OutputStream outputStream = response.getOutputStream();

            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();

            renderer.createPDF(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (DocumentException e) {
            return redirectToErrorPage(e.getMessage());
        } catch (IOException e) {
            return redirectToErrorPage(e.getMessage());
        } catch (BookingNotFoundException e) {
            return redirectToErrorPage(e.getMessage());
        } catch (RoomNotFoundException e) {
            return redirectToErrorPage(e.getMessage());
        } catch (InvoiceNotFoundException e) {
            return redirectToErrorPage(e.getMessage());
        }
        return null;
    }

    @PostMapping (SPLIT_INVOICE)
    public ModelAndView splitInvoice(@ModelAttribute("lineItemWrapper") LineItemWrapper lineItemWrapper,
                                     @ModelAttribute("booking") BookingDTO bookingDTO,
                                     @ModelAttribute("invoice") InvoiceDTO invoiceDTO,
                                     @ModelAttribute("recipientState") RecipientState recipientState) {

        List<LineItemDTO> lineItemDTOs = lineItemWrapper.getLineItemList();

        List<LineItem> lineItemsToSplit = lineItemDTOs
                .stream()
                .map(lineItemDTO ->
                        new LineItem(lineItemDTO.getName(),
                                lineItemDTO.getSplitAmount(),
                                lineItemDTO.getDuration(),
                                lineItemDTO.getPrice()))
                .collect(Collectors.toList());
        if(recipientState.isAnonymous())
        {
            try {
                splitInvoiceService.splitInvoiceWithoutRecipient(bookingDTO.getBookingId(), invoiceDTO.getInvoiceNumber(), lineItemsToSplit);
            } catch (BookingNotFoundException | InvoiceNotFoundException | NoLineItemsException | LineItemsMismatchException | AllLineItemsRemovedException e) {
                return redirectToErrorPage(e.getMessage());
            }
        }
        else{
            try {
                splitInvoiceService.splitInvoice(bookingDTO.getBookingId(), invoiceDTO.getInvoiceNumber(), lineItemsToSplit);
            } catch (BookingNotFoundException | InvoiceNotFoundException | NoLineItemsException | LineItemsMismatchException | AllLineItemsRemovedException e) {
                return redirectToErrorPage(e.getMessage());
            }
        }




        return new ModelAndView("redirect:checkOutGuestOverview?id="+bookingDTO.getBookingId().getBookingId().toString());
    }

    private static ModelAndView redirectToErrorPage (String errorMessage){
        return new ModelAndView("redirect:" + ERROR_URL + "?errorMessage=" + errorMessage);
    }

}
