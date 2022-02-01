package com.datemate.api.group.controller;

import com.datemate.api.group.model.Group;
import com.datemate.api.group.service.GroupService;
import com.datemate.common.constants.Constants;
import com.datemate.common.controller.CommonController;
import com.datemate.common.json.JsonMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/api/group/")
public class GroupController extends CommonController {

    @Resource
    private GroupService groupService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public JsonMessage addGroup(@RequestBody Group group)  {
        JsonMessage jsonMessage = new JsonMessage();
        if (log.isDebugEnabled()) {
            log.debug("Request Body : {}", group);
        }

        try {
            groupService.addGroup(group);
            jsonMessage.setResponseCode(Constants.SUCCESS);
        } catch (Exception e) {
            log.error("addGroup Fail", e);
            jsonMessage.setErrorMsgWithCode(this.getMessage("DEFAULT_EXCEPTION"));
        }

        return jsonMessage;
    }
}
