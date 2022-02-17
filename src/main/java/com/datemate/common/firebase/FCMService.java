package com.datemate.common.firebase;

import com.datemate.api.member.service.MemberService;
import com.datemate.common.util.StringUtils;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class FCMService {

    @Resource
    private MemberService memberService;

    public void send(Integer userSeq, String title, String body) {
        String token = memberService.selectUserToken(userSeq);
        if (StringUtils.isNotEmpty(token)) {
            try {
                Message message = Message.builder()
                        .setToken(token)
                        .putData("title", title)
                        .putData("body", body)
                        .build();

                String response = FirebaseMessaging.getInstance().send(message);
                log.debug("Sent FCM: {}", response);
            } catch (FirebaseMessagingException e) {
                log.error("Sent FCM Fail", e);
                log.debug("Remove User Token");
                memberService.removeUserToken(userSeq);
            } catch (Exception e) {
                log.error("Sent FCM Fail", e);
            }
        }
    }
}
