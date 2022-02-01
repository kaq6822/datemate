package com.datemate.api.member.controller;

import com.datemate.api.member.model.Member;
import com.datemate.api.member.service.MemberService;
import com.datemate.common.constants.Constants;
import com.datemate.common.controller.CommonController;
import com.datemate.common.json.JsonMessage;
import com.datemate.common.security.JwtTokenUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@Api(value = "Member Api")
@Slf4j
@RestController
@RequestMapping("/api/member/")
public class MemberController extends CommonController {

    @Resource
    private MemberService memberService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "email-check", method = RequestMethod.GET)
    @ResponseBody
    public JsonMessage checkUserExist(@RequestParam String email) {
        JsonMessage jsonMessage = new JsonMessage();
        if (log.isDebugEnabled()) {
            log.debug("Request Param : {}", email);
        }

        try {
            if (memberService.checkUserExist(email)) {
                jsonMessage.setErrorMsgWithCode(this.getMessage("DUPLICATION_EMAIL"));
            } else {
                jsonMessage.setResponseCode(Constants.SUCCESS);
            }

        } catch (Exception e) {
            log.error("Email Check Fail", e);
            jsonMessage.setErrorMsgWithCode(this.getMessage("DEFAULT_EXCEPTION"));
        }

        return jsonMessage;
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage signUp(@RequestBody Member member) {
        JsonMessage jsonMessage = new JsonMessage();

        if (log.isDebugEnabled()) {
            log.debug("Request Body : {}", member);
        }

        try {
            memberService.addUser(member);
            jsonMessage.setResponseCode(Constants.SUCCESS);
        } catch (Exception e) {
            log.error("Signup ERROR", e);
            jsonMessage.setErrorMsgWithCode(this.getMessage("DEFAULT_EXCEPTION"));
        }

        return jsonMessage;
    }

    @RequestMapping(value = "signin", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage signIn(@RequestBody Member member) {
        JsonMessage jsonMessage = new JsonMessage();

        if (log.isDebugEnabled()) {
            log.debug("Request Body : {}", member);
        }

        try {
            if (memberService.authenticateByEmailAndPassword(member.getEmail(), member.getPassword())) {
                String token = jwtTokenUtil.generateToken(member.getEmail());
                jsonMessage.addAttribute("token", token);
            }
            jsonMessage.setResponseCode(Constants.SUCCESS);
        } catch (Exception e) {
            log.error("Login error", e);
            jsonMessage.setErrorMsgWithCode(this.getMessage("DEFAULT_EXCEPTION"));
        }

        return jsonMessage;
    }

    @RequestMapping(value = "signout", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonMessage signOut(@RequestBody Map<String, Object> paramMap) {
        JsonMessage jsonMessage = new JsonMessage();

        if (log.isDebugEnabled()) {
            log.debug("Request Body : {}", paramMap);
        }

        try {
            memberService.disableUser(this.getLoginUserEmail());
            jsonMessage.setResponseCode(Constants.SUCCESS);
        } catch (Exception e) {
            jsonMessage.setErrorMsgWithCode(this.getMessage("DEFAULT_EXCEPTION"));
        }

        return jsonMessage;
    }
}
