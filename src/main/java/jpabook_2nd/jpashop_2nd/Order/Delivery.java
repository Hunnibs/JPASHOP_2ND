package jpabook_2nd.jpashop_2nd.Order;

import jakarta.persistence.*;
import jpabook_2nd.jpashop_2nd.Member.Address;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {
    @Id @GeneratedValue
    private Long id;

    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}
