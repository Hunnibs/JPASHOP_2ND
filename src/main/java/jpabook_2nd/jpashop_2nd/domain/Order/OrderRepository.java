package jpabook_2nd.jpashop_2nd.domain.Order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {
    @PersistenceContext
    EntityManager em;

    public Long save(Order order){
        em.persist(order);
        return order.getId();
    }

    public Order find(Long id) {
        return em.find(Order.class, id);
    }
}
