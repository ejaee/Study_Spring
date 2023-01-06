package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // 정적 컨텐츠 방식은 고객에게 그대로 파일을 전달하는 방식
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    // mvc 와 template 엔진은 서버에서 변형해서 html 을 바꿔서 전달하는 방식
    // 템플릿 엔진을 mvc 방식으로 쪼개서 view 를 템플릿엔진으로 html 을 좀 더 랜더링된 html 을 전달
    @GetMapping("hello-mvc")
    // 외부에서 인자를 받고 싶을 때 @RequestParam
    // command + p 를 통해 파라미터의 정보를 확인할 수 있다
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API 는 서버간의 교류에는 데이터만 내려주면 될때 사용되는 방식. html 이 필요없다
    // API 는 HttpMessageConverter 를 통해 view 없이 그대로 반환
    @GetMapping("hello-string")
    // 아래 annotation 이 있으면 viewResolver 대신에 HttpMessageConverter 가 동작
    @ResponseBody   // body 부에 리턴 값을 그대로 넣어주겠다
                    // 페이지의 소스파일을 보면 그냥 리턴값만 적혀있다
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // 데이터를 달라고 하면 일반적으로 객체를 반환
    @GetMapping("hello-api")
    @ResponseBody   // string 이 아닌 객체가 올 경우 JSON 으로 처리한다
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        // getter / setter 자바빈 규약... 프로퍼티 접근 방식
    }
//
//    API 의 ResponseBody 방식의 사용 원리
//
//        웹 브라우저에서 localhost:8080/hello-api 를 치면
//        태장 톰켓 서버에서 spring 에 전달하고
//        controller 에 맵핑된 hello-api 를 찾고
//        responseBody 어노테이션을 확인하면 viewResolver 가 아닌 HttpMessageConverter 를 사용
//        문자이냐 객체이냐 경우의 수에서
//        문자가 오면 hello-string 처럼 그냥 문자값을 http 응답에 바로 넣어서 전달 StringConverter
//        객체가 오면 JSON 방식으로 데이터를 만들어 http 응답에 전달 {name: spring} JsonConverter
//        대표적으로 MappingJackson2HttpMessageConverter
}

// command + shift + enter 자동완성
