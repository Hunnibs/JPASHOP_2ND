package jpabook_2nd.jpashop_2nd.service;

import jpabook_2nd.jpashop_2nd.domain.Item.Item;
import jpabook_2nd.jpashop_2nd.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    private final ItemRepository itemRepository;

    /**
     * 아이템 등록
     */
    @Transactional
    public void save(Item item) {
        itemRepository.save(item);
    }

    /**
     * 아이템 조회
     */
    public List<Item> findItems(){
        return itemRepository.findAll();
    }

    public Item find(Long itemId){
        return itemRepository.find(itemId);
    }
}
