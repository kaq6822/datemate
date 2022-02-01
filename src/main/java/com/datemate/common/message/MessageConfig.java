package com.datemate.common.message;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessageConfig {
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:message/message");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(600);

        return messageSource;
    }

    @Bean
    public MessageSourceAccessor messageSourceAccessor() {
        MessageSourceAccessor messageSourceAccessor = new MessageSourceAccessor(messageSource());

        return messageSourceAccessor;
    }
}
