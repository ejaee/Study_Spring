package hello.servlet.domain.member.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {

    /**
     *
     * @param paramMap
     * @param model
     * @return ViewName
     */
    String process(Map<String, String> paramMap, Map<String, Object> model);

}
