package jpabook_2nd.jpashop_2nd.controller;

import jpabook_2nd.jpashop_2nd.domain.Item.Item;
import jpabook_2nd.jpashop_2nd.domain.Member.Member;
import jpabook_2nd.jpashop_2nd.domain.Order.Order;
import jpabook_2nd.jpashop_2nd.domain.Order.OrderSearch;
import jpabook_2nd.jpashop_2nd.service.ItemService;
import jpabook_2nd.jpashop_2nd.service.MemberService;
import jpabook_2nd.jpashop_2nd.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final MemberService memberService;
    private final ItemService itemService;
    private final OrderService orderService;

    @GetMapping(value = "/order")
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping(value = "/order")
    public String order(@RequestParam("memberId") Long memberId, @RequestParam("itemId") Long itemId, @RequestParam("count") int count) {
        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }

    @GetMapping(value = "/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);

        return "order/orderList";
    }

    @PostMapping(value = "/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable("orderId") Long orderId){
        orderService.cancel(orderId);

        return "redirect:/orders";
    }
}
