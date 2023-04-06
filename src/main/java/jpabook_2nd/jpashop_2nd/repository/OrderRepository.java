package jpabook_2nd.jpashop_2nd.repository;

import jakarta.persistence.EntityManager;
import jpabook_2nd.jpashop_2nd.domain.Order.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    public Long save(Order order){
        em.persist(order);
        return order.getId();
    }

    public Order find(Long id) {
        return em.find(Order.class, id);
    }
}
