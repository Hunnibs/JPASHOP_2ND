package jpabook_2nd.jpashop_2nd.service;

import jakarta.persistence.EntityManager;
import jpabook_2nd.jpashop_2nd.domain.Item.Movie;
import jpabook_2nd.jpashop_2nd.domain.Member.Address;
import jpabook_2nd.jpashop_2nd.domain.Member.Member;
import jpabook_2nd.jpashop_2nd.domain.Order.Delivery;
import jpabook_2nd.jpashop_2nd.domain.Order.Order;
import jpabook_2nd.jpashop_2nd.domain.Order.OrderItem;
import jpabook_2nd.jpashop_2nd.domain.Order.OrderStatus;
import jpabook_2nd.jpashop_2nd.exception.NotEnoughStockException;
import jpabook_2nd.jpashop_2nd.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception{
        //given
        Member member = new Member();
        member.setName("Lee");
        member.setAddress(new Address("Seoul", "Oguem", "05613"));

        Movie movie = new Movie();
        movie.setName("Parasite");
        movie.setDirector("Park Chan Ho");
        movie.setActor("Song Gang Ho");
        movie.setPrice(17000);
        movie.setStockQuantity(100);

        em.persist(member);
        em.persist(movie);

        //when
        Long orderId = orderService.order(member.getId(), movie.getId(), 10);

        //then
        Order getOrder = orderRepository.find(orderId);
        assertEquals(OrderStatus.ORDER, getOrder.getStatus(), "상품 주문시 상태는 ORDER");
        assertEquals(1, getOrder.getOrderItems().size(), "주문한 상품 종류 수가 정확해야 한다.");
        assertEquals(17000 * 10, getOrder.getTotalPrice(), "주문 가격은 가격 * 수량이다");
        assertEquals(100 - 10, movie.getStockQuantity(), "주문수량만큼 재고는 줄어든다");
    }

    @Test
    public void 재고수량초과() throws Exception{
        //given
        Member member = new Member();
        member.setName("Lee");
        member.setAddress(new Address("Seoul", "Oguem", "05613"));

        Movie movie = new Movie();
        movie.setName("Parasite");
        movie.setDirector("Park Chan Ho");
        movie.setActor("Song Gang Ho");
        movie.setPrice(17000);
        movie.setStockQuantity(100);

        em.persist(member);
        em.persist(movie);

        //when
        assertThrows(NotEnoughStockException.class, ()->{
            Long orderId = orderService.order(member.getId(), movie.getId(), 99);
        });

        //then
    }

    @Test
    public void 주문취소() throws Exception{
        //given
        Member member = new Member();
        member.setName("Lee");
        member.setAddress(new Address("Seoul", "Oguem", "05613"));

        Movie movie = new Movie();
        movie.setName("Parasite");
        movie.setDirector("Park Chan Ho");
        movie.setActor("Song Gang Ho");
        movie.setPrice(17000);
        movie.setStockQuantity(100);

        em.persist(member);
        em.persist(movie);

        //when
        Long orderId = orderService.order(member.getId(), movie.getId(), 10);
        orderService.cancel(orderId);

        //then
        Order getOrder = orderService.find(orderId);

        assertEquals(OrderStatus.CANCEL, getOrder.getStatus(), "상품 주문시 상태는 CANCEL");
        assertEquals(100, movie.getStockQuantity(), "주문수량만큼 재고는 다시 채워진다.");
    }
}