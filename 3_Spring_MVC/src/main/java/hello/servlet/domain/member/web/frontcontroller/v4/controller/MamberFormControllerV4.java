package hello.servlet.domain.member.web.frontcontroller.v4.controller;

import hello.servlet.domain.member.web.frontcontroller.v4.ControllerV4;
import java.util.Map;

public class MamberFormControllerV4 implements ControllerV4 {


    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        return "new-form";
    }
}
