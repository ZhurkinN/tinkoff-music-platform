package ru.tinkoff.tinkoffmusicplatform.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class LoggingConfiguration {


    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();

        loggingFilter.setIncludeClientInfo(false);
        loggingFilter.setIncludeHeaders(true);
        loggingFilter.setIncludePayload(false);
        loggingFilter.setMaxPayloadLength(64000);
        loggingFilter.setIncludeQueryString(true);

        return loggingFilter;
    }

}
