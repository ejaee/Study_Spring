package hello.servlet.domain.member.web.frontcontroller.v5;

import hello.servlet.domain.member.web.frontcontroller.ModelView;
import hello.servlet.domain.member.web.frontcontroller.MyView;
import hello.servlet.domain.member.web.frontcontroller.v3.controller.MamberFormControllerV3;
import hello.servlet.domain.member.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.domain.member.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.domain.member.web.frontcontroller.v4.controller.MamberFormControllerV4;
import hello.servlet.domain.member.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.domain.member.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.domain.member.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.domain.member.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

//    private Map<String, ControllerV4> controllerMap = new HashMap<>();
    private final Map<String, Object> handlerMappingMap = new HashMap<>();

    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>(); // 추가

    public FrontControllerServletV5() {
        initHandlerMappingMap();
        initHanlderAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MamberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        //v4 추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MamberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHanlderAdapters() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        Object handler = getHandler(request);

        if (handler == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter adapter = getHandlerAdapter(handler);
        ModelView mv = adapter.handle(request, response, handler);

        String viewName = mv.getViewName(); // 논리 이름이 저장되어있다. new-form
        MyView view = viewResolver(viewName); // 디테일한 로직은 메서드로 빠는게 좋다

        view.render(mv.getModel(), request, response);

    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
        throw new IllegalArgumentException("handler adapter 를 찾을 수 없습니다.");
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return  handlerMappingMap.get(requestURI);
    }
}
