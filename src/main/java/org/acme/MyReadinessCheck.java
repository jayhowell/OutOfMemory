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
        if(HealthChecksDemo.ready==true)
            return HealthCheckResponse.up("readiness:ready");
        else return HealthCheckResponse.down("readiness:notready");
    }
}