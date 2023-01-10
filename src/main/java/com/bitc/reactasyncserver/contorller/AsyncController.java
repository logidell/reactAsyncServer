package com.bitc.reactasyncserver.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//CORS 오류 :    Cross Origin Resource Sharing Policy  줄임말, 동일한 주소 내에서만 리소르를 공유할 수 있음.
//              sprng은 CROS 허용을 위해 @CrossOrigin 어노테이션을 사용해 처리한다.
//              => http의 보안정책중 하나. ip로 리소스를 요청하는 구조.
//              => 간단하게 get / post 등등의 요청 파악이 제대로 안되면 발생하는 것 중 하나.
//              => 그 과정 중 적절한 대처가 일어나지 않았을 시 일어나는 보안 에러 - 지금은 이렇게 이해하는 걸로
//

//origin : 요청이 시작된 서버의 위치를 나타냄.
// 클라 서버 (ex : localhost:8080 )/ 로그인 서버  (localhost:3000)
// 클라 서버 -> 로그인 서버에 http 요청 보냄 : Origin 이 다름. = 요청 시작된 서버 위치가 다름. = CrossOrigin


//@CrossOrigin : 어노테이션 사용하기 위해선  지정한 도메인에 대해 접근을 허용한다.
//              @CrossOrigin 어노테이션은  메소드, 클래스, configur에 설정할 수 있다.
//              메소드에 사용 시 지정한 메소드만 접근을 허용한다. 클래스에 사용 시 지정한 컨트롤러에 대해서만 접근을 허용
//              configur에 사용시 모든 곳에 접근을 허용한다.
//              ** 옵션으로 origins에 접근할 서버의 주소를 입력.

//CORS 오류  - spring이랑 react 한 컴퓨터에 다른 서버로 돌려서 생기는 오류임.
// 1. 스프링에서 처리 - @CrossOrigin
//      여기서도 3가지로 나뉨.

// 2. react 에서 flush 사용해 처리 방법
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:4000"})
@RestController
@RequestMapping("/async")
//localhost:8080/async 가 됨.
public class AsyncController {
    public String index() throws Exception {
        return "index";
    }

//    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseBody
    @RequestMapping(value = "/data1", method = RequestMethod.POST)
    public String asyncData1() throws Exception {
        return "서버와 통신 성공";
    }

    @RequestMapping(value = "/sendDataGet", method = RequestMethod.GET)
    public Object sendDataGet() throws Exception {
        Map<String, String> result = new HashMap<>();
        result.put("result", "success");
        result.put("data", "");

        return result;
    }

    @RequestMapping(value = "/sendDataPost", method = RequestMethod.POST)
    public Object sendDataPost(@RequestParam("id") String id, @RequestParam("pw")String pw) throws Exception {
        //dto 객체 그대로 받아도 됨.  현재는 2개 이상이기에 map으로 사용함.
        Map<String, String>user = new HashMap<>();
        user.put("id", "test1");
        user.put("pw", "asdf1234");

        Map<String, Object>result = new HashMap<>();
        result.put("result", "success");
        result.put("data", user);

        return result;
    }

    @RequestMapping(value = "/sendDataPut", method = RequestMethod.PUT)
    public Object sendDataPut(@RequestParam int idx) throws Exception {
        Map<String, Object>result = new HashMap<>();
        result.put("result", "success");
        result.put("idx", idx);
        return  result;
    }
    @RequestMapping(value = "/sendDataDelete", method = RequestMethod.DELETE)
    public Object sendDataDelete(@RequestParam("idx") int idx) throws Exception {
        Map<String, Object>result = new HashMap<>();
        result.put("result", "success");
        result.put("idx", idx);
        return  result;
    }
}
//