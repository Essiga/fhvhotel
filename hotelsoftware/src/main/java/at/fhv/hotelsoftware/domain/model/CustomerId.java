package at.fhv.hotelsoftware.domain.model;

import java.util.UUID;

public class CustomerId {

    private UUID customerId;


    public CustomerId() {}

    public CustomerId(UUID customerId) {this.customerId = customerId;}


    public UUID getCustomerId() {return customerId;}

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }
}
