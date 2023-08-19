package hello.itemservice.domain.item;

import lombok.Data;

// 프로퍼티를 만들어 준다. 그런데 과하게 만들어줘서 핵심 도메인 모델에 사용하기에는 위험하다
@Data // 예제니까 일단 쓴다. 원래 DTO에 사용된다.
public class Item {

    private Long id;
    private String itemName;
    private Integer price; // int 로 선언하면 값이 안들어갈 때 null이 적용될 텐데 문제가 발생한다
    private Integer quantity; // int 로 선언하려면 처음부터 0으로 초기화를 해줘야 하는데 가격이 0원은 상황과 맞지 않다.

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
