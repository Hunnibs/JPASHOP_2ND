package jpabook_2nd.jpashop_2nd.service;

import jpabook_2nd.jpashop_2nd.domain.Item.Item;
import jpabook_2nd.jpashop_2nd.domain.Member.Member;
import jpabook_2nd.jpashop_2nd.domain.Order.*;
import jpabook_2nd.jpashop_2nd.repository.ItemRepository;
import jpabook_2nd.jpashop_2nd.repository.MemberRepository;
import jpabook_2nd.jpashop_2nd.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.find(memberId);
        Item item = itemRepository.find(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());
        delivery.setStatus(DeliveryStatus.READY);

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);
        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);
        return order.getId();
    }

    @Transactional
    public void cancel(Long orderId) {
        Order order = orderRepository.find(orderId);
        order.cancel();
    }

    public Order find(Long id){
        return orderRepository.find(id);
    }

    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }
}

