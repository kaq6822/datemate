package com.datemate.api.schedule.controller;

import com.datemate.api.group.service.GroupService;
import com.datemate.api.schedule.model.ScheduleGroup;
import com.datemate.api.schedule.model.ScheduleGroupUser;
import com.datemate.api.schedule.model.id.ScheduleGroupId;
import com.datemate.api.schedule.model.id.ScheduleGroupUserId;
import com.datemate.api.schedule.service.ScheduleService;
import com.datemate.common.constants.Constants;
import com.datemate.common.controller.CommonController;
import com.datemate.common.json.JsonMessage;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/schedule/group")
public class ScheduleGroupController extends CommonController {

    @Resource
    private ScheduleService scheduleService;

    @Resource
    private GroupService groupService;

    @RequestMapping(value = "/checkList", method = RequestMethod.GET)
    @ResponseBody
    public JsonMessage getCheckScheduleList(@RequestParam(name = "groupId") Integer groupId) {
        JsonMessage jsonMessage = new JsonMessage();

        try {
            List<ScheduleGroupUser> scheduleGroupUser = scheduleService.selectCheckScheduleListByGroupId(groupId);
            jsonMessage.addAttribute("checkList", scheduleGroupUser);
            jsonMessage.setResponseCode(Constants.SUCCESS);
        } catch (Exception e) {
            log.error("get Check Schedules By Group ID ", e);
            jsonMessage.setErrorMsgWithCode(this.getMessage("DEFAULT_EXCEPTION"));
        }

        return jsonMessage;
    }

    @RequestMapping(value = "/check", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage checkSchedule(@RequestBody List<ScheduleGroupId> scheduleGroupIdList) {
        JsonMessage jsonMessage = new JsonMessage();

        if (log.isDebugEnabled()) {
            log.debug("Request Body : {}", scheduleGroupIdList);
        }

        try {
            // TODO: 해당 그룹 소속원인지 확인 로직 추가
            for (ScheduleGroupId scheduleGroupId : scheduleGroupIdList) {
                scheduleService.checkGroupSchedule(new ScheduleGroupUserId(scheduleGroupId, this.getLoginUserSeq()));
            }
            jsonMessage.setResponseCode(Constants.SUCCESS);
        } catch (Exception e) {
            log.error("check Group Schedule Fail");
            jsonMessage.setErrorMsgWithCode(this.getMessage("DEFAULT_EXCEPTION"));
        }

        return jsonMessage;
    }

    @RequestMapping(value = "/check", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonMessage uncheckSchedule(@RequestBody List<ScheduleGroupId> scheduleGroupIdList) {
        JsonMessage jsonMessage = new JsonMessage();

        if (log.isDebugEnabled()) {
            log.debug("Request Body : {}", scheduleGroupIdList);
        }

        try {
            for (ScheduleGroupId scheduleGroupId : scheduleGroupIdList) {
                scheduleService.uncheckGroupSchedule(new ScheduleGroupUserId(scheduleGroupId, this.getLoginUserSeq()));
            }
            jsonMessage.setResponseCode(Constants.SUCCESS);
        } catch (Exception e) {
            log.error("uncheck Group Schedule Fail", e);
            jsonMessage.setErrorMsgWithCode(this.getMessage("DEFAULT_EXCEPTION"));
        }

        return jsonMessage;
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.GET)
    @ResponseBody
    public JsonMessage getConfirmScheduleList(@RequestParam(name = "groupId") Integer groupId) {
        JsonMessage jsonMessage = new JsonMessage();

        try {
            List<ScheduleGroup> scheduleGroup = scheduleService.selectConfirmScheduleByGroupId(groupId);
            jsonMessage.addAttribute("scheduleList", scheduleGroup);
            jsonMessage.setResponseCode(Constants.SUCCESS);
        } catch (Exception e) {
            log.error("get Confirm Group Schedule Fail", e);
            jsonMessage.setErrorMsgWithCode(this.getMessage("DEFAULT_EXCEPTION"));
        }

        return jsonMessage;
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.POST)
    @ResponseBody
    public JsonMessage confirmSchedule(@RequestBody ScheduleGroupId scheduleGroupId) {
        JsonMessage jsonMessage = new JsonMessage();

        if (log.isDebugEnabled()) {
            log.debug("Request Body : {}", scheduleGroupId);
        }

        try {
            scheduleService.confirmGroupScheduleYN(new ScheduleGroupUserId(scheduleGroupId, this.getLoginUserSeq()), 'Y');

            if (scheduleService.countConfirmGroupSchedule(scheduleGroupId) >= groupService.countGroupUser(scheduleGroupId.getGroupId())) {
                scheduleService.saveConfirmSchedule(new ScheduleGroup(scheduleGroupId.getScheduleDate(), scheduleGroupId.getGroupId()));
            }

            jsonMessage.setResponseCode(Constants.SUCCESS);
        } catch (Exception e) {
            log.error("Confirm Schedule Fail", e);
            jsonMessage.setErrorMsgWithCode(this.getMessage("DEFAULT_EXCEPTION"));
        }

        return jsonMessage;
    }

    @RequestMapping(value = "/confirm", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonMessage unconfirmSchedule(@RequestBody ScheduleGroupId scheduleGroupId) {
        JsonMessage jsonMessage = new JsonMessage();

        if (log.isDebugEnabled()) {
            log.debug("Request Body : {}", scheduleGroupId);
        }

        try {
            scheduleService.confirmGroupScheduleYN(new ScheduleGroupUserId(scheduleGroupId, this.getLoginUserSeq()), 'N');

            if(scheduleService.selectConfirmSchedule(scheduleGroupId) != null) {
                scheduleService.deleteConfirmSchedule(scheduleGroupId);
            }

            jsonMessage.setResponseCode(Constants.SUCCESS);
        } catch (Exception e) {
            log.error("Confirm Schedule Fail", e);
            jsonMessage.setErrorMsgWithCode(this.getMessage("DEFAULT_EXCEPTION"));
        }

        return jsonMessage;
    }

}
