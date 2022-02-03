package com.datemate.api.user.controller;

import com.datemate.api.member.model.Member;
import com.datemate.api.user.model.User;
import com.datemate.api.user.model.UserRelation;
import com.datemate.api.user.service.UserService;
import com.datemate.common.ServiceException;
import com.datemate.common.constants.Constants;
import com.datemate.common.controller.CommonController;
import com.datemate.common.json.JsonMessage;
import com.datemate.common.util.StringUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Api("User Api")
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController extends CommonController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    @ResponseBody
    public JsonMessage getProfile(@RequestParam(name = "email") String email) {
        JsonMessage jsonMessage = new JsonMessage();

        if (log.isDebugEnabled()) {
            log.debug("Request Body : {}", email);
        }

        try {
            User user = userService.selectUserByEmail(email);

            jsonMessage.addAttribute("profile", user);
            jsonMessage.setResponseCode(Constants.SUCCESS);
        } catch (UsernameNotFoundException ue) {
            log.debug("User Not Found : {}", email);
            jsonMessage.setErrorMsgWithCode(this.getMessage("NOT_FOUND_EXCEPTION", new Object[]{this.getMessage("COMMON_USER")}));
        } catch (Exception e) {
            log.error("Login error", e);
            jsonMessage.setErrorMsgWithCode(this.getMessage("DEFAULT_EXCEPTION"));
        }

        return jsonMessage;
    }

    @RequestMapping(value = "/profile", method = RequestMethod.PUT)
    @ResponseBody
    public JsonMessage modProfile(@RequestBody User user) {
        JsonMessage jsonMessage = new JsonMessage();

        if (log.isDebugEnabled()) {
            log.debug("Request Body : {}", user);
        }

        try {
            user.setUserSeq(this.getLoginUserSeq());
            user.setEmail(this.getLoginUserEmail());
            userService.updateUser(user);
            jsonMessage.setResponseCode(Constants.SUCCESS);
        } catch (Exception e) {
            log.error("Login error", e);
            jsonMessage.setErrorMsgWithCode(this.getMessage("DEFAULT_EXCEPTION"));
        }

        return jsonMessage;
    }

    @RequestMapping(value = "/friend", method = RequestMethod.GET)
    @ResponseBody
    public JsonMessage getFriendList() {
        JsonMessage jsonMessage = new JsonMessage();

        try {
            List<UserRelation> userList = userService.selectRelationUserList(this.getLoginUserSeq());

            jsonMessage.addAttribute("userList", userList);
            jsonMessage.setResponseCode(Constants.SUCCESS);
        } catch (Exception e) {
            log.error("get Friend List Error", e);
            jsonMessage.setErrorMsgWithCode(this.getMessage("DEFAULT_EXCEPTION"));
        }

        return jsonMessage;
    }

    @RequestMapping(value = "/friend", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage requestAddFriend(@RequestBody Map<String, Object> paramMap) {
        JsonMessage jsonMessage = new JsonMessage();

        if (log.isDebugEnabled()) {
            log.debug("Request Body : {}", paramMap);
        }

        try {
            Integer targetUserSeq = (int) paramMap.get("targetUserSeq");
            if (this.getLoginUserSeq() == targetUserSeq) {
                throw new ServiceException(this.getMessage("CANNOT_SELF_EXCEPTION"));
            }

            userService.addUserRelation(this.getLoginUserSeq(), targetUserSeq);

            jsonMessage.setResponseCode(Constants.SUCCESS);
        } catch (ServiceException se) {
            log.debug(se.getMessage());
            jsonMessage.setErrorMsgWithCode(se.getMessage());
        } catch (Exception e) {
            log.error("get Friend List Error", e);
            jsonMessage.setErrorMsgWithCode(this.getMessage("DEFAULT_EXCEPTION"));
        }

        return jsonMessage;
    }

    @RequestMapping(value = "/friend/accept", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage acceptFriend(@RequestParam(name = "targetUserSeq") int targetUserSeq) {
        JsonMessage jsonMessage = new JsonMessage();

        if (log.isDebugEnabled()) {
            log.debug("Request Param : {}", targetUserSeq);
        }

        try {
            if (this.getLoginUserSeq() == targetUserSeq) {
                throw new ServiceException(this.getMessage("CANNOT_SELF_EXCEPTION"));
            }

            UserRelation userRelation = userService.selectRelationUser(this.getLoginUserSeq(), targetUserSeq);

            if (userRelation.getStatus().equals(Constants.WAITING)) {
                userService.acceptUserRelation(this.getLoginUserSeq(), targetUserSeq);
            } else if (userRelation.getStatus().equals(Constants.COMPLETE)) {
                throw new ServiceException(this.getMessage("ALREADY_ACCEPT_EXCEPTION"));
            } else {
                throw new Exception("User Relation ERROR: " + userRelation);
            }
            jsonMessage.setResponseCode(Constants.SUCCESS);
        } catch (ServiceException se) {
            log.error("{}", se.getMessage());
            jsonMessage.setErrorMsgWithCode(se.getMessage());
        } catch (Exception e) {
            log.error("ERROR", e);
            jsonMessage.setErrorMsgWithCode(this.getMessage("DEFAULT_EXCEPTION"));
        }

        return jsonMessage;
    }

    @RequestMapping(value = "/friend", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonMessage unfriend(@RequestBody Map<String, Object> paramMap) {
        JsonMessage jsonMessage = new JsonMessage();

        if (log.isDebugEnabled()) {
            log.debug("Request Param : {}", paramMap);
        }

        try {
            userService.deleteUserRelation(this.getLoginUserSeq(), (int) paramMap.get("targetUserSeq"));
            jsonMessage.setResponseCode(Constants.SUCCESS);
        } catch (Exception e) {
            log.error("get Friend List Error", e);
            jsonMessage.setErrorMsgWithCode(this.getMessage("DEFAULT_EXCEPTION"));
        }

        return jsonMessage;
    }
}
