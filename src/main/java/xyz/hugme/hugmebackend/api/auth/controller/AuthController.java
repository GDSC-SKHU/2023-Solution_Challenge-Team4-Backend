package xyz.hugme.hugmebackend.api.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.hugme.hugmebackend.api.auth.dto.LoginDto;
import xyz.hugme.hugmebackend.api.client.service.ApiClientService;
import xyz.hugme.hugmebackend.api.common.SingleRspsTemplate;
import xyz.hugme.hugmebackend.api.counselor.service.ApiCounselorService;
import xyz.hugme.hugmebackend.domain.user.client.Client;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final ApiCounselorService apiCounselorService;
    private final ApiClientService apiClientService;
    // 상담사 로그인
    @PostMapping("/sign-in/counselors")
    public SingleRspsTemplate<String> signInCounselor(LoginDto loginDto, HttpServletRequest request){
        // username, password 검사
        Counselor validatedCounselor = apiCounselorService.validateSignIn(loginDto);

        // JsessionId 반환
        HttpSession session = request.getSession();
        session.setAttribute("name", validatedCounselor.getName());
        session.setAttribute("email", validatedCounselor.getEmail());
        session.setAttribute("role", Counselor.class.getSimpleName());

        return new SingleRspsTemplate<>(HttpStatus.OK.value(), "login success");
    }

    // 내담자 로그인
    @PostMapping("/sign-in/clients")
    public SingleRspsTemplate<String> signInClient(LoginDto loginDto, HttpServletRequest request){
        // username, password 검사.
        Client validatedClient = apiClientService.validateSignIn(loginDto);
        HttpSession session = request.getSession();
        session.setAttribute("name", validatedClient.getName());
        session.setAttribute("email", validatedClient.getEmail());
        return new SingleRspsTemplate<>(HttpStatus.OK.value(), "login success");
    }



    /** 아래 주석은 실 배포환경에서 CORS 문제가 발생할 시 활용해보자.*/
//    @GetMapping("/sign-in-test")
//    public String test(HttpServletRequest request){
//        StringBuilder sb = new StringBuilder();
//        HttpSession session = request.getSession();
//
//        String name = (String) session.getAttribute("name");
//        System.out.println("name = " + name);
//        sb.append("session: name " + name);
//        String email = (String) session.getAttribute("email");
//        System.out.println("email = " + email);
//        sb.append("session: email " + email);
//
//        return sb.toString();
//    }
//
//    @GetMapping("/auth-header-test")
//    public String authHeaderTest(HttpServletRequest request){
//        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
//        System.out.println("header = " + header);
//
//        HttpSession session = request.getSession();
//
//        String name = (String) session.getAttribute("name");
//        System.out.println("name = " + name);
//        return header+ "그리고 세션name은" + name;
//    }


}










