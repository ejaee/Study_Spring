package hello.servlet.domain.member.web.frontcontroller.v3;

import hello.servlet.domain.member.web.frontcontroller.ModelView;
import hello.servlet.domain.member.web.frontcontroller.MyView;
import hello.servlet.domain.member.web.frontcontroller.v3.controller.MamberFormControllerV3;
import hello.servlet.domain.member.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.domain.member.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// http://localhost:8080/dir/new-form -> // /v4//new-form
// http://localhost:8080/front-controller/v3/members/new-form
@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {


    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MamberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        // localhost:8080 이후의 경로를 얻을 수 있다
        // 경로를 key 로 사용한다.
        String requestURI = request.getRequestURI();

        // 값(new ~)을 반환받으면서 컨트롤러를 생성한다.
        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //paramMap - 디테일한 로직은 메서드로 빠는게 좋다
        Map<String, String> paramMap = createParamMap(request);

        ModelView mv = controller.process(paramMap);

        String viewName = mv.getViewName(); // 논리 이름이 저장되어있다. new-form
        MyView view = viewResolver(viewName); // 디테일한 로직은 메서드로 빠는게 좋다

        view.render(mv.getModel(), request, response);
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
