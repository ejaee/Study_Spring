package hello.servlet.basic.request;

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

//        http://localhost:8080/request-param?username=ejae&age=28
        request.getParameterNames().asIterator()
            .forEachRemaining(paramName -> System.out.println(paramName + ": " + request.getParameter(paramName)));
        
        System.out.println();
        System.out.println(" 단일 조회 ");
        String username = request.getParameter("username");
        System.out.println("username = " + username);
        String age = request.getParameter("age");
        System.out.println("age = " + age);

//        http://localhost:8080/request-param?username=ejae&age=28&username=minseok
        System.out.println(" 복수 값 조회 ");
        String[] names = request.getParameterValues("username");
        for (String name : names) {
            System.out.println("username = " + name);
        }

        response.getWriter().write("ok");


    }
}
