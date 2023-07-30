package hello.servlet.domain.member.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.web.frontcontroller.ModelView;
import hello.servlet.domain.member.web.frontcontroller.v3.ControllerV3;
import java.util.Map;

public class MamberFormControllerV3 implements ControllerV3 {

    @Override
    public ModelView process(Map<String, String> paramMap) {
        // 풀 경로가 아닌, 논리 이름만 정해서 넣는다.
        return new ModelView("new-form");
    }
}
