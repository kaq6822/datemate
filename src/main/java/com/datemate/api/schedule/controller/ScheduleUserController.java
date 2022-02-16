package com.datemate.api.schedule.controller;

import com.datemate.api.schedule.model.ScheduleUser;
import com.datemate.api.schedule.model.id.ScheduleUserId;
import com.datemate.api.schedule.service.ScheduleService;
import com.datemate.api.user.model.UserRelation;
import com.datemate.api.user.service.UserService;
import com.datemate.common.ServiceException;
import com.datemate.common.constants.Constants;
import com.datemate.common.controller.CommonController;
import com.datemate.common.json.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/schedule/user")
public class ScheduleUserController extends CommonController {
    @Resource
    private ScheduleService scheduleService;

    @Resource
    private UserService userService;

    @RequestMapping(value = "/task", method = RequestMethod.GET)
    @ResponseBody
    public JsonMessage getUserScheduleList() {
        JsonMessage jsonMessage = new JsonMessage();

        try {
            List<ScheduleUser> scheduleUserList = scheduleService.selectScheduleListByUserSeq(this.getLoginUserSeq());
            jsonMessage.addAttribute("taskList", scheduleUserList);
            jsonMessage.setResponseCode(Constants.SUCCESS);
        } catch (Exception e) {
            log.error("getUserScheduleList Fail", e);
            jsonMessage.setErrorMsgWithCode(this.getMessage("DEFAULT_EXCEPTION"));
        }

        return jsonMessage;
    }

    @RequestMapping(value = "/task", method = RequestMethod.GET, params = "userSeq")
    @ResponseBody
    public JsonMessage getOtherUserScheduleList(@RequestParam(name = "userSeq") Integer userSeq) {
        JsonMessage jsonMessage = new JsonMessage();

        try {
            if (userSeq != this.getLoginUserSeq()) {
                UserRelation userRelation = userService.selectRelationUser(userSeq, this.getLoginUserSeq());
                if (!userRelation.getStatus().equals(Constants.COMPLETE)) {
                    throw new ServiceException(this.getMessage("NO_FRIEND_EXCEPTION"));
                }
            }

            List<ScheduleUser> scheduleUserList = scheduleService.selectScheduleListByUserSeq(this.getLoginUserSeq());
            jsonMessage.addAttribute("taskList", scheduleUserList);
            jsonMessage.setResponseCode(Constants.SUCCESS);
        } catch (EntityNotFoundException nfe) {
            jsonMessage.setErrorMsgWithCode(this.getMessage("NO_FRIEND_EXCEPTION"));
        } catch (ServiceException se) {
            jsonMessage.setErrorMsgWithCode(se.getMessage());
        } catch (Exception e) {
            log.error("getOtherUserScheduleList Fail", e);
            jsonMessage.setErrorMsgWithCode(this.getMessage("DEFAULT_EXCEPTION"));
        }

        return jsonMessage;
    }

    @RequestMapping(value = "/task", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage addUserSchedule(@RequestBody ScheduleUser scheduleUser) {
        JsonMessage jsonMessage = new JsonMessage();

        if (log.isDebugEnabled()) {
            log.debug("Request Body : {}", scheduleUser);
        }

        try {
            if (scheduleUser.getUserSeq() != this.getLoginUserSeq()) {
                log.error("An attempt was made to perform an unauthorized operation: {} => {}", this.getLoginUserSeq(), scheduleUser);
                throw new ServiceException(this.getMessage("NO_PERMISSION_EXCEPTION"));
            }

            scheduleService.saveScheduleUser(scheduleUser);

            jsonMessage.setResponseCode(Constants.SUCCESS);
        } catch (ServiceException se) {
            jsonMessage.setErrorMsgWithCode(se.getMessage());
        } catch (Exception e) {
            log.error("addUserSchedule Fail", e);
            jsonMessage.setErrorMsgWithCode(this.getMessage("DEFAULT_EXCEPTION"));
        }

        return jsonMessage;
    }

    @RequestMapping(value = "/task", method = RequestMethod.PUT)
    @ResponseBody
    public JsonMessage updateUserSchedule(@RequestBody ScheduleUser scheduleUser) {
        JsonMessage jsonMessage = new JsonMessage();

        if (log.isDebugEnabled()) {
            log.debug("Request Body : {}", scheduleUser);
        }

        try {
            ScheduleUser originSchedule = scheduleService.selectSchedule(scheduleUser.getScheduleSeq());

            if (originSchedule.getUserSeq() != this.getLoginUserSeq() || originSchedule.getTargetUserSeq() != scheduleUser.getTargetUserSeq()) {
                log.error("An attempt was made to perform an unauthorized operation: {} => {}", this.getLoginUserSeq(), scheduleUser);
                throw new ServiceException(this.getMessage("NO_PERMISSION_EXCEPTION"));
            }

            if (originSchedule.getStatus() == Constants.APPROVE_REQUEST && originSchedule.getStatus() != scheduleUser.getStatus()) {
                log.error("Try to change Status without accept schedule request");
                throw new ServiceException(this.getMessage("NOT_ACCEPT_EXCEPTION"));
            }

            scheduleService.saveScheduleUser(scheduleUser);

            if (originSchedule.getTargetUserSeq() != null) {
                // TODO: 약속 신청자 상태에게 스케줄 변경 Push 알람
            }

            jsonMessage.setResponseCode(Constants.SUCCESS);
        } catch (ServiceException se) {
            jsonMessage.setErrorMsgWithCode(se.getMessage());
        } catch (Exception e) {
            log.error("updateUserSchedule Fail", e);
            jsonMessage.setErrorMsgWithCode(this.getMessage("DEFAULT_EXCEPTION"));
        }

        return jsonMessage;
    }

    @RequestMapping(value = "/task", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonMessage deleteUserSchedule(@RequestBody ScheduleUserId scheduleUserId) {
        JsonMessage jsonMessage = new JsonMessage();

        try {
            if (scheduleUserId.getUserSeq() != this.getLoginUserSeq()) {
                log.error("An attempt was made to perform an unauthorized operation: {} => {}", this.getLoginUserSeq(), scheduleUserId);
                throw new ServiceException(this.getMessage("NO_PERMISSION_EXCEPTION"));
            }
            scheduleService.deleteScheduleUser(scheduleUserId);
            jsonMessage.setResponseCode(Constants.SUCCESS);
        } catch (ServiceException se) {
            jsonMessage.setErrorMsgWithCode(se.getMessage());
        } catch (Exception e) {
            log.error("deleteUserSchedule Fail", e);
            jsonMessage.setErrorMsgWithCode(this.getMessage("DEFAULT_EXCEPTION"));
        }

        return jsonMessage;
    }

    @RequestMapping(value = "/task/invite", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage inviteUserSchedule(@RequestBody ScheduleUserId scheduleUserId) {
        JsonMessage jsonMessage = new JsonMessage();

        try {
            if (scheduleUserId.getUserSeq() == this.getLoginUserSeq()) {
                throw new ServiceException(this.getMessage("CANNOT_SELF_EXCEPTION"));
            }

            UserRelation userRelation = userService.selectRelationUser(scheduleUserId.getUserSeq(), this.getLoginUserSeq());
            if (!userRelation.getStatus().equals(Constants.COMPLETE)) {
                throw new ServiceException(this.getMessage("NO_FRIEND_EXCEPTION"));
            }
            ScheduleUser scheduleUser = scheduleService.selectSchedule(scheduleUserId.getScheduleSeq());
            scheduleUser.setTargetUserSeq(scheduleUserId.getUserSeq());
            scheduleUser.setStatus(Constants.APPROVE_REQUEST);
            scheduleService.saveScheduleUser(scheduleUser);

            jsonMessage.setResponseCode(Constants.SUCCESS);
        } catch (ServiceException se) {
            jsonMessage.setErrorMsgWithCode(se.getMessage());
        } catch (Exception e) {
            log.error("inviteUserSchedule Fail", e);
            jsonMessage.setErrorMsgWithCode(this.getMessage("DEFAULT_EXCEPTION"));
        }

        return jsonMessage;
    }

    @RequestMapping(value = "/task/accept", method = RequestMethod.PUT)
    @ResponseBody
    public JsonMessage acceptUserSchedule(@RequestBody ScheduleUserId scheduleUserId) {
        JsonMessage jsonMessage = new JsonMessage();

        try {
            ScheduleUser scheduleUser = scheduleService.selectSchedule(scheduleUserId.getScheduleSeq());

//            if (scheduleUserId.getUserSeq() != this.getLoginUserSeq()) {
            if (scheduleUser.getTargetUserSeq() != this.getLoginUserSeq()) {
                log.error("An attempt was made to perform an unauthorized operation: {} => {}", this.getLoginUserSeq(), scheduleUserId);
                throw new ServiceException(this.getMessage("NO_PERMISSION_EXCEPTION"));
            }

            if (scheduleUser.getStatus().equals(Constants.APPROVE_REQUEST)) {
                scheduleUser.setStatus(Constants.ACTIVE);
                scheduleService.saveScheduleUser(scheduleUser);
            } else if (scheduleUser.getStatus().equals(Constants.ACTIVE)) {
                throw new ServiceException(this.getMessage("ALREADY_ACCEPT_EXCEPTION"));
            } else {
                throw new Exception("User Relation ERROR: " + scheduleUser);
            }
            jsonMessage.setResponseCode(Constants.SUCCESS);
        } catch (Exception e) {
            log.error("acceptUserSchedule Fail", e);
            jsonMessage.setErrorMsgWithCode(this.getMessage("DEFAULT_EXCEPTION"));
        }

        return jsonMessage;
    }

}