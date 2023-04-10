package jpabook_2nd.jpashop_2nd.controller;

import jpabook_2nd.jpashop_2nd.domain.Item.Book;
import jpabook_2nd.jpashop_2nd.form.BookForm;
import jpabook_2nd.jpashop_2nd.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping(value = "/items/new")
    public String CreateItem(Model model) {
        model.addAttribute("form", new BookForm());
        return "/items/createItemForm";
    }

    @PostMapping(value = "/items/new")
    public String create(BookForm form) {
        Book book = new Book();

        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.save(book);
        return "redirect:/";
    }
}
