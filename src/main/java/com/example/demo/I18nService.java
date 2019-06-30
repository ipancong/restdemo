package com.example.demo;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Service
@Slf4j
public class I18nService {
    private MessageSource messageSource;
    @Value("${spring.messages.basename}")
    private String basename;

    @Value("${spring.messages.cache-seconds}")
    private long cacheMillis;

    @Value("${spring.messages.encoding}")
    private String encoding;

    /**
     * 初始化
     *
     * @return
     */
    private MessageSource initMessageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename(basename);
        messageSource.setDefaultEncoding(encoding);
        messageSource.setCacheMillis(cacheMillis);
        return messageSource;
    }

    /**
     * 设置当前的返回信息
     *
     * @param request
     * @param code
     * @return
     */
    public String getMessage(HttpServletRequest request, String code) {
        if (messageSource == null) {
            messageSource = initMessageSource();
        }
       // String language = request.getHeader("Accept-Language");
        String language = CookieUtils.getCookieValue(request,"lang");
        //默认没有就是请求地区的语言
        Locale locale = null;
        if (language == null) {
            locale = request.getLocale();
        } else if ("en-US".equals(language)) {
            locale = Locale.ENGLISH;
        } else if ("zh-CN".equals(language)) {
            locale = Locale.CHINA;
        }
        //其余的不正确的默认就是本地的语言
        else {
            locale = request.getLocale();
        }
        String result = null;
        try {
            result = messageSource.getMessage(code, null, locale);
        } catch (NoSuchMessageException e) {
            log.error("Cannot find the error message of internationalization, return the original error message.");
        }
        if (result == null) {
            return code;
        }
        return result;
    }

}
