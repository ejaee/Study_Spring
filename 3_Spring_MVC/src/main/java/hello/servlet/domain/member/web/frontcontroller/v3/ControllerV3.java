package hello.servlet.domain.member.web.frontcontroller.v3;

import hello.servlet.domain.member.web.frontcontroller.ModelView;
import java.util.Map;

public interface ControllerV3 {

    ModelView process(Map<String, String> paramMap);

}
