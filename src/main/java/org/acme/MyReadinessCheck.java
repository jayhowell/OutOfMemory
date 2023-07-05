package org.acme;


import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

import jakarta.enterprise.context.ApplicationScoped;

@Readiness
@ApplicationScoped
public class MyReadinessCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        if(GreetingResource.ready==true)
            return HealthCheckResponse.up("alive");
        else return HealthCheckResponse.down("dead");
    }
}