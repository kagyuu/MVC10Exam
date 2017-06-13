/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.mvcexam.ext.ozark;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.messageresolver.AbstractMessageResolver;
import org.thymeleaf.util.Validate;

/**
 *
 * @author hondou.atsushi
 */
@Slf4j
public class MessageResolver extends AbstractMessageResolver {

    @Override
    public String resolveMessage(ITemplateContext context, Class<?> origin, String key, Object[] messageParameters) {
        ResourceBundle.Control control = 
            ResourceBundle.Control.getNoFallbackControl(
                    ResourceBundle.Control.FORMAT_DEFAULT);
        
        Locale locale = context.getLocale();
        log.debug("Message Locale={}", locale.toString());
        ResourceBundle bundle = ResourceBundle.getBundle("msg", locale, control);
        String message = bundle.getString(key);
        
        if (null == messageParameters || 0 == messageParameters.length) {
            return message;
        }
        
        final MessageFormat messageFormat = new MessageFormat(message, locale);
        return messageFormat.format(messageParameters);        
    }

    @Override
    public String createAbsentMessageRepresentation(ITemplateContext context, Class<?> origin, String key, Object[] messageParameters) {
        Validate.notNull(key, "Message key cannot be null");
        if (context.getLocale() != null) {
            return "??"+key+"_" + context.getLocale().toString() + "??";
        }
        return "??"+key+"_" + "??";
    }
    
}
