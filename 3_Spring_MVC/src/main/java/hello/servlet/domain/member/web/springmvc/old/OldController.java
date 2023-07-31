package hello.servlet.domain.member.web.springmvc.old;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

@Component("/springmvc/old-controller") // 스프링 빈 이름 지정
public class OldController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
        throws Exception {

        System.out.println("OldController.handlerRequest");
        return new ModelAndView("new-form");
    }
}
