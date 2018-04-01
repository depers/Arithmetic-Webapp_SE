package cn.bravedawn.airthmeticwebappse.controller;

import cn.bravedawn.airthmeticwebappse.bean.front.Axis;
import cn.bravedawn.airthmeticwebappse.bean.front.Series;
import cn.bravedawn.airthmeticwebappse.common.app.Arithmetic;
import cn.bravedawn.airthmeticwebappse.common.util.Const;
import cn.bravedawn.airthmeticwebappse.common.util.CookiesUtil;
import cn.bravedawn.airthmeticwebappse.common.util.InfixToSuffixUtil;
import cn.bravedawn.airthmeticwebappse.common.util.SuffixToValueUtil;
import cn.bravedawn.airthmeticwebappse.service.UserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class IndexController {

    @Autowired
    private UserScoreService userScoreService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;


    @GetMapping("/")
    public String index(ModelMap map) {
        Cookie ck = CookiesUtil.getCookieByName(request, Const.LOGIN_USER);
        if (ck != null){
            map.addAttribute("flag", 1);
        }
        else{
            map.addAttribute("flag", 0);
        }

        return "/index";
    }

    @GetMapping("/answer.html")
    public String answer(ModelMap map) {
        List<String> expresses = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            expresses.add(Arithmetic.generate());
        }
        map.addAttribute("expresses", expresses);
        return "/answer";
    }

    @PostMapping("/answer.html")
    @ResponseBody
    public Map<String, Object> postAnswer(@RequestParam(value = "val", required = true) String val,
                                          @RequestParam(value = "result", required = true) String result) {
        Map<String, Object> body = new HashMap<>();
        int score = 0;
        val = val.replace('a', '+');
        String[] valArray = val.split(",");
        String[] resultArray = result.split(",");
        if (valArray.length != result.length()){
            body.put("success", false);
            body.put("msg", "param error!");
        }

        // compute
        for (int i = 0; i < 20; i++){
            String temp = valArray[i].replace(' ', ',');
            System.out.println(temp);
            try {
                int res = SuffixToValueUtil.compute(InfixToSuffixUtil.prefixToSuffix(temp));
                if (res == Integer.parseInt(resultArray[i])){
                    score += 5;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // store record
        boolean outcome = false;
        Cookie ck = CookiesUtil.getCookieByName(request, Const.LOGIN_USER);
        if (ck != null){
             outcome = userScoreService.insertUser(ck.getValue(), userScoreService.findUserTimesByUuId(ck.getValue()), score);
        }
        else{
            String uuid = UUID.randomUUID().toString();
            CookiesUtil.addCookie(response, Const.LOGIN_USER, uuid, Const.SESSION_TIME);
            outcome = userScoreService.insertUser(uuid, 1, score);
        }

        if (outcome){
            body.put("success", true);
            body.put("msg", "submit success!");
        }
        return body;
    }


    @GetMapping("/result.html")
    public String result(){
        Cookie ck = CookiesUtil.getCookieByName(request, Const.LOGIN_USER);
        if (ck != null){
            return "/result";
        }
        else{
            return "/";
        }
    }

    @GetMapping("/resultData")
    @ResponseBody
    public Map<String, Object> getDate(){
        Map<String, Object> result = new HashMap<>();
        Cookie ck = CookiesUtil.getCookieByName(request, Const.LOGIN_USER);
        String uuId = ck.getValue();
        // xAxis
        int times = userScoreService.findUserTimesByUuId(uuId);
        List<String> xAxis = new ArrayList<>();
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < times-1; i++){
            xAxis.add("第"+(i+1)+"次测试");
            System.out.println("************"+uuId+i);
            data.add(userScoreService.findScoreByUUidAndTimes(uuId, i+1));
        }
        Axis xaxis = new Axis();
        xaxis.setData(xAxis);

        // series
        Series series = new Series();
        series.setName("得分");
        series.setType("bar");
        series.setData(data);
        List<Series> seriesList = new ArrayList<>();
        seriesList.add(series);

        result.put("tooltip", null);
        result.put("xAxis", xaxis);
        result.put("yAxis", null);
        result.put("series", seriesList);

        return result;
    }


}
