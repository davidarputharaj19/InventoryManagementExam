package com.inventory.product.component;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/*
 * Custom health actuator is available at http://localhost:9090/manage/health
 * 
 */

@Component
public class CustomHealthCheckIndicator implements HealthIndicator {
        private final String messageKey = "Product Service";
        
    @Override
    public Health health() {
        if (!isRunningProductService()) {
            return Health.down().withDetail(messageKey, "Not Available").build();
        } 
        return Health.up().withDetail(messageKey, "Available").build();
    }
    
    private Boolean isRunningProductService() {
        Boolean isRunning = true;
        return isRunning;
    }
}
