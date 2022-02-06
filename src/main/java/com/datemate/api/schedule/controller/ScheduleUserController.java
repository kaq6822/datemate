package com.datemate.api.schedule.controller;

import com.datemate.api.schedule.model.ScheduleUser;
import com.datemate.api.schedule.model.id.ScheduleUserId;
import com.datemate.api.schedule.service.ScheduleService;
import com.datemate.common.json.JsonMessage;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/schedule/user")
public class ScheduleUserController {
    @Resource
    private ScheduleService scheduleService;

    @RequestMapping(value = "/task", method = RequestMethod.GET)
    @ResponseBody
    public JsonMessage getUserSchedule() {
        JsonMessage jsonMessage = new JsonMessage();

        return jsonMessage;
    }

    @RequestMapping(value = "/task", method = RequestMethod.POST)
    public JsonMessage addUserSchedule(@RequestBody ScheduleUser scheduleUser) {
        JsonMessage jsonMessage = new JsonMessage();

        return jsonMessage;
    }

    @RequestMapping(value = "/task", method = RequestMethod.PUT)
    public JsonMessage updateUserSchedule(@RequestBody ScheduleUser scheduleUser) {
        JsonMessage jsonMessage = new JsonMessage();

        return jsonMessage;
    }

    @RequestMapping(value = "/task", method = RequestMethod.DELETE)
    public JsonMessage deleteUserSchedule(@RequestBody ScheduleUserId scheduleUserId) {
        JsonMessage jsonMessage = new JsonMessage();

        return jsonMessage;
    }
}