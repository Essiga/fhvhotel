package at.fhv.hotelsoftware.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BookingDataDTO {

    private String gname;
    private String voucher;
    private String firstName;
    private String lastName;
    private String streetAdr;
    private String zip;
    private String city;
    private String country;
    private String phone;
    private String email;
    private Integer singleRoomCount;
    private Integer doubleRoomCount;
    private Integer superiorRoomCount;
    private String checkInDate;
    private String checkOutDate;
}
