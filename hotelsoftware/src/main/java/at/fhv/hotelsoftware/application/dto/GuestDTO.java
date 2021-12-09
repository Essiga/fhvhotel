package at.fhv.hotelsoftware.application.dto;

import at.fhv.hotelsoftware.domain.model.Guest;
import at.fhv.hotelsoftware.domain.model.Room;
import at.fhv.hotelsoftware.domain.model.valueobjects.Address;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestId;
import at.fhv.hotelsoftware.domain.model.valueobjects.GuestType;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class GuestDTO {

    private Long id;
    private GuestId guestId;
    private String firstName;
    private String lastName;
    private String streetAddress;
    private String zip;
    private String city;
    private String country;
    private String phoneNumber;
    private String email;
    private GuestType guestType;
    private String companyName;
    private String agencyName;

    @Builder
    public GuestDTO(GuestId guestId, String firstName, String lastName, String streetAddress, String zip, String city, String country, String phoneNumber, String email, GuestType guestType, String companyName, String agencyName) {
        this.guestId = guestId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.zip = zip;
        this.city = city;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.guestType = guestType;
        this.companyName = companyName;
        this.agencyName = agencyName;
    }

    public static GuestDTO fromGuest(Guest guest) {

        Address address = guest.getAddress();

        return new GuestDTO(guest.getGuestId(),
                guest.getFirstName(),
                guest.getLastName(),
                address.getStreet(),
                address.getZip(),
                address.getCity(),
                address.getCountry(),
                guest.getPhoneNumber(),
                guest.getEmail(),
                guest.getGuestType(),
                guest.getCompanyName(),
                guest.getAgencyName());
    }

    public static List<GuestDTO> fromGuestList(List<Guest> guests){


        return guests
                .stream()
                .map(guest ->
                        new GuestDTO(
                                guest.getGuestId(),
                                guest.getFirstName(),
                                guest.getLastName(),
                                guest.getAddress().getStreet(),
                                guest.getAddress().getZip(),
                                guest.getAddress().getCity(),
                                guest.getAddress().getCountry(),
                                guest.getPhoneNumber(),
                                guest.getEmail(),
                                guest.getGuestType(),
                                guest.getCompanyName(),
                                guest.getAgencyName()
                        ))
                .collect(Collectors.toList());
    }
}
