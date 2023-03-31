package jpabook_2nd.jpashop_2nd.Order;

import jakarta.persistence.*;
import jpabook_2nd.jpashop_2nd.Member.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
