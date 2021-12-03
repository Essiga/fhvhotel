package at.fhv.hotelsoftware.domain.model.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String street;
    private String zip;
    private String city;
    private String country;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return street.equals(address.street) && zip.equals(address.zip) && city.equals(address.city) && country.equals(address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, zip, city, country);
    }
}
