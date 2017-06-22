/*
 * An example for the MVC1.0m2 on the Glassfish 4.1.2 JEE7
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
 * Process placeholders on Thymeleaf3 templates.
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
