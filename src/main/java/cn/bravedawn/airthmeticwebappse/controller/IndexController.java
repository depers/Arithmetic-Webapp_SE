package cn.bravedawn.airthmeticwebappse.controller;


        import cn.bravedawn.airthmeticwebappse.common.app.Arithmetic;
        import cn.bravedawn.airthmeticwebappse.service.UserScoreService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.ModelMap;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.ResponseBody;

        import java.util.ArrayList;
        import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private UserScoreService userScoreService;


    @GetMapping("/")
    public String index(){
        return "/index";
    }

    @GetMapping("/answer.html")
    @ResponseBody
    public String answer(ModelMap map){
        List<String> expresses = new ArrayList<>();
        for (int i = 0; i < 20; i++){
            expresses.add(Arithmetic.generate());
        }
        map.addAttribute("expresses", expresses);
        return "/answer";
    }
}
