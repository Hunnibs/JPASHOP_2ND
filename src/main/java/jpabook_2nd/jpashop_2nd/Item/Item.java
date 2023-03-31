package jpabook_2nd.jpashop_2nd.Item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public abstract class Item {
    @Id @GeneratedValue
    private Long id;

    private String name;

    private int price;
}
