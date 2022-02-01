package com.datemate.common.controller;

import com.datemate.api.member.model.Member;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.annotation.Resource;
import java.util.Locale;

public class CommonController {
    @Resource
    private MessageSourceAccessor messageSourceAccessor;

    public String getLoginUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public Integer getLoginUserSeq() {
        Member member = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return member.getUserSeq();
    }

    /**
     * message.properties 에서 키에 해당하는 값을 찾아서 리턴한다. db 설정결과에 따라 locale세팅 (logger 전용)
     *
     * @param messageKey
     *            키
     * @return 키에 해당하는 값
     */
    public String getMessage(String messageKey) {
        String msg = null;
        try {
            String strLocale =  "ko_KR";
            Locale locale = new Locale(strLocale);
            msg = getMessage(messageKey,locale);
        } catch (NoSuchMessageException nme) {
            msg = messageKey;
        }
        return msg;
    }

    /**
     * message.properties 에서 키에 해당하는 값을 찾아서 리턴한다. 
     *
     * @param messageKey
     *            키
     * @param locale
     *            언어 세팅
     * @return 키에 해당하는 값
     */
    public String getMessage(String messageKey, Locale locale) {
        String msg = null;
        try {
            msg = messageSourceAccessor.getMessage(messageKey,locale);
        } catch (NoSuchMessageException nme) {
            msg = messageKey;
        }
        return msg;
    }

    /**
     * message.properties 에서 키에 해당하는 값을 찾아서 파라미터 값이 들어갈 위치에 값을 세팅. db 설정결과에 따라 locale세팅 (logger 전용)
     *
     * @param messageKey
     *            키
     * @param params
     *            결과의 변수에 들어갈 값의 배열
     * @return 키에 해당하는 값
     */
    public String getMessage(String messageKey, Object[] params) {
        String msg = null;
        try {
            String strLocale =  "ko_KR";
            Locale locale = new Locale(strLocale);
            msg = getMessage(messageKey, params, locale);
        } catch (NoSuchMessageException nme) {
            msg = messageKey;
        }
        return msg;
    }

    /**
     * message.properties 에서 키에 해당하는 값을 찾아서 파라미터 값이 들어갈 위치에 값을 세팅하여 Locale을 지정해서 결과를 리턴한다.
     *
     * @param messageKey
     *            키
     * @param params
     *            결과의 변수에 들어갈 값의 배열
     * @param locale
     *            언어 세팅
     * @return 키에 해당하는 값
     */
    public String getMessage(String messageKey, Object[] params, Locale locale) {
        String msg = null;
        try {
            msg = messageSourceAccessor.getMessage(messageKey, params, locale);
        } catch (NoSuchMessageException nme) {
            msg = messageKey;
        }
        return msg;
    }

    /**
     * message.properties 에서 키에 해당하는 값을 찾아서 <br>
     * 파라미터 값이 들어갈 위치에 값을 세팅하고, appendMessage를 뒤에 붙인뒤 Locale을 지정해서 결과를 리턴한다.
     *
     * @param messageKey
     *            키(예 : invalid.password.exception)
     * @param paramas
     *            결과의 변수에 들어갈 값의 배열
     * @param appendMessage
     *            결과의 뒤에 붙을 문자열
     * @param locale
     *            언어 세팅
     * @return 키에 해당하는 값
     */
    public String getMessage(String messageKey, Object[] paramas, String appendMessage, Locale locale) {
        String msg = null;
        try {
            msg = messageSourceAccessor.getMessage(messageKey, paramas, appendMessage, locale);
        } catch (NoSuchMessageException nme) {
            msg = messageKey;
        }
        return msg;
    }
}
