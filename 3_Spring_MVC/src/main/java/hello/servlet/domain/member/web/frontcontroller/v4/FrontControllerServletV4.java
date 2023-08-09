package hello.servlet.domain.member.web.frontcontroller.v4;

import hello.servlet.domain.member.web.frontcontroller.MyView;
import hello.servlet.domain.member.web.frontcontroller.v4.controller.MamberFormControllerV4;
import hello.servlet.domain.member.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.domain.member.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MamberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // localhost:8080 이후의 경로를 얻을 수 있다
        // 경로를 key 로 사용한다.
        String requestURI = request.getRequestURI();

        // 값(new ~)을 반환받으면서 컨트롤러를 생성한다.
        ControllerV4 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //paramMap - 디테일한 로직은 메서드로 빠는게 좋다
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>(); // 추가

        String viewName = controller.process(paramMap, model);

        MyView view = viewResolver(viewName); // 디테일한 로직은 메서드로 빠는게 좋다
        view.render(model, request, response);
    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private static HashMap<String, String> createParamMap(HttpServletRequest request) {
        HashMap<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
            .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
