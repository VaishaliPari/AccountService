package com.example.AccountService.Entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@RefreshScope
public class RefreshableProperties {

    @Value("${hystrix.connectionTimeout}")
    private int requestTimeout;
    @Value("${hystrix.requestTimeout}")
    private int connectionTimeout;
    @Value("${accountHolder.service}")
    private String restCall;

    public int getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(int requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public String getRestCall() {
        return restCall;
    }

    public void setRestCall(String restCall) {
        this.restCall = restCall;
    }
}
