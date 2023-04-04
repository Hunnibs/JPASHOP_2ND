package jpabook_2nd.jpashop_2nd.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook_2nd.jpashop_2nd.domain.Item.Item;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {
    @PersistenceContext
    EntityManager em;

    public void save(Item item) {
        em.persist(item);
    }
}
