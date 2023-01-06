package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {   // 먼저 Controller 에서 맵팽된 html 을 찾아보고 없으면 static html 을 찾아보기 때문에
                                // static 의 index.html 보다 templates 의 home.html 이 우선된다

    @GetMapping("/") // 주소창에 있는 그 /
    public String home() {
        return "home";
    }

}
