package org.acme;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.Readiness;

@Liveness
@Readiness
public class MyLivenessCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        if(HealthChecksDemo.alive==true)
            return HealthCheckResponse.up("Liveliness: alive");
        else return HealthCheckResponse.down("Liveliness: dead");
    }



}