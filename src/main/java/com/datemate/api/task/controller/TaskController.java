package com.datemate.api.task.controller;

import com.datemate.api.task.service.TaskService;
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
@RequestMapping("/calender")
public class TaskController extends CommonController {

    @Resource
    private TaskService taskService;

    @RequestMapping(name = "/detail", method = RequestMethod.POST)
    public JsonMessage calenderDetail(@RequestBody String taskId) {
        JsonMessage jsonMessage = new JsonMessage();

        try {
            jsonMessage.setResponseCode(Constants.SUCCESS);
        } catch (Exception e) {
            log.error("calenderDetail Fail", e);
            jsonMessage.setErrorMsgWithCode(this.getMessage("DEFAULT_EXCEPTION"));
        }

        return jsonMessage;
    }
}
