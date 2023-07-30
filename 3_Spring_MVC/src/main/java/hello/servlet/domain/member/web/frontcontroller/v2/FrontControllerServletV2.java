package hello.servlet.domain.member.web.frontcontroller.v2;

import hello.servlet.domain.member.web.frontcontroller.MyView;
import hello.servlet.domain.member.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.domain.member.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.domain.member.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // localhost:8080 이후의 경로를 얻을 수 있다
        // 경로를 key 로 사용한다.
        String requestURI = request.getRequestURI();

        // 값(new ~)을 반환받으면서 컨트롤러를 생성한다.
        ControllerV2 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyView view = controller.process(request, response);
        view.render(request, response);
    }
}
