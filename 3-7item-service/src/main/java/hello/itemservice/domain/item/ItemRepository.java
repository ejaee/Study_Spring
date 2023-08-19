package hello.itemservice.domain.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository // component 스캔의 대상이 된다.
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); // static
    private static long sequence = 0L; // static

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    // 정석으로 하려면 현재 item 내에 id 멤버변수가 포함되어 있다.
    // 그렇기 때문에 ItemParamDto 를 만들고 set 하는 세개의 멤버변수를 담고있는 매개변수를 따로 받는 것이 정석이다.
    // 왜냐하면 updateParam.setId 가 가능하기 때문이다.
    // 중복이냐 명확성이냐 고민될때는 명확성을 선택하자.
    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
