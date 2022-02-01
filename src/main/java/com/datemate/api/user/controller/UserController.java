package com.datemate.api.user.controller;

import com.datemate.api.member.model.Member;
import com.datemate.api.user.model.User;
import com.datemate.api.user.service.UserService;
import com.datemate.common.constants.Constants;
import com.datemate.common.controller.CommonController;
import com.datemate.common.json.JsonMessage;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@Api("User Api")
@Slf4j
@RestController
@RequestMapping("/api/user/")
public class UserController extends CommonController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "profile", method = RequestMethod.GET)
    @ResponseBody
    public JsonMessage getProfile(@RequestParam Map<String, Object> param) {
        JsonMessage jsonMessage = new JsonMessage();

        if (log.isDebugEnabled()) {
            log.debug("Request Body : {}", param);
        }

        try {
            User user = userService.selectUserByEmail(this.getLoginUserEmail());

            jsonMessage.addAttribute("profile", user);
            jsonMessage.setResponseCode(Constants.SUCCESS);
        } catch (Exception e) {
            log.error("Login error",e);
            jsonMessage.setErrorMsgWithCode(this.getMessage("DEFAULT_EXCEPTION"));
        }

        return jsonMessage;
    }

    @RequestMapping(value = "profile", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage modProfile(@RequestBody User user) {
        JsonMessage jsonMessage = new JsonMessage();

        if (log.isDebugEnabled()) {
            log.debug("Request Body : {}", user);
        }

        try {
            userService.updateUser(user);
            jsonMessage.setResponseCode(Constants.SUCCESS);
        } catch (Exception e) {
            log.error("Login error",e);
            jsonMessage.setErrorMsgWithCode(this.getMessage("DEFAULT_EXCEPTION"));
        }

        return jsonMessage;
    }
}
