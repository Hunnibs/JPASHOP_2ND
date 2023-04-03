package jpabook_2nd.jpashop_2nd.domain.Item;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {
    @PersistenceContext
    EntityManager em;

    public void save(Item item) {
        em.persist(item);
    }
}
