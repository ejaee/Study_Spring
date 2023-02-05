package hello.core.singleton;

// 싱그톤 패턴 주의할 점
// 상태를 유지하면 안된다
public class StatefulService {
    private int price; // 상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; // 여기가 문제
    }

    public int getPrice() {
        return price;
    }

    // 상태 -> 무상태 바꾸려면
//    public int order(String name, int price) { // 상태를 유지하지 않고 싶다면
//        // private int price 를 사용하지 않고
//        System.out.println("name = " + name + " price = " + price);
//        return price; // 바로 리턴하면 된다
//    }


}
