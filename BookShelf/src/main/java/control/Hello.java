package control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Hello {

    @RequestMapping(value = "/hello/{name}", method = RequestMethod.GET)
    public ModelAndView greeting(@PathVariable String name) {

        ModelMap model = new ModelMap();
        model.put("name", name);
        return new ModelAndView("hello", model);

    }

}
