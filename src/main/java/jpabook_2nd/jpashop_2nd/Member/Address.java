package jpabook_2nd.jpashop_2nd.Member;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
