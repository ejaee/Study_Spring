package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // spring 이 controller 를 관리하도록 한다. 스프링 빈이 관리된다고 말한다
public class MemberController {

    private final MemberService memberService;

    @Autowired
    // 멤버 컨트롤러가 생성이 될 때 스프링 빈에 등록되어있는 스프링 서비스를 memberService 와 연결해준다(Dependency Injection)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new") // get 방식으로 members/new에 들어가면
    public String createForm() {
        return "members/createMemberForm";
        // 해당 메서드는 createMemberForm을 리턴 -> resources/tem 에서 createMemberForm 을 찾는다
    }

    @PostMapping("members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/"; // 회원가입이 끝나니까 홈 화면으로 보낸다
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}


/*
* 스프링 빈을 등록하는 2가지 방법
* 1. 컴포넌트 스캔(위 코드처럼)
*   애노테이션이 있으면 스프링 빈으로 자동 등록된다
*   @controller ... @ Service ...
*
* 2. 자바 코드로 직접 스프링 빈 등록하기
*
*
* 스프링은 스프링 컨테이너에 스프링 빈을 등록할 때, 기본으로 싱글톤으로 등록한다
* 따라서 같은 스프링 빈이면 모두 같은 인스턴스다
*
* */