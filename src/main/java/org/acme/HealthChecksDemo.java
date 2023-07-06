package org.acme;

import java.util.LinkedList;
import java.util.List;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
@QuarkusMain
public class HealthChecksDemo {
    public static boolean ready=true;
    public static boolean alive=true;

    public static void main(String[] args) {
        Quarkus.run(args);
    }
    
    @GET
    @Path("status")
    @Produces(MediaType.TEXT_PLAIN)
    public String getStatus() {
        String ready_status;
        String alive_status;
        if(HealthChecksDemo.alive)
            alive_status="Alive";
        else
            alive_status="Not Alive";
        if(HealthChecksDemo.ready)
            ready_status="Ready";
        else
            ready_status="Not Ready";
            
        return System.getenv("HOSTNAME")+": Current status :" + alive_status + " / "+ready_status;
    }

    @GET
    @Path("setReady")
    @Produces(MediaType.TEXT_PLAIN)
    public String setReady() {
        HealthChecksDemo.ready=true;
        return getStatus();
    }

    @GET
    @Path("setNotReady")
    @Produces(MediaType.TEXT_PLAIN)
    public String setNotReady() {
        HealthChecksDemo.ready=false;
        return getStatus(); 
    }

    @GET
    @Path("setAlive")
    public String setAlive()
    {
        HealthChecksDemo.alive=true;
        return getStatus();
    }

    @GET
    @Path("setNotAlive")
    public String setNotAlive()
    {
        HealthChecksDemo.alive=false;
        return getStatus();
    }


    @GET
    @Path("oom")
    @Produces(MediaType.TEXT_PLAIN)
    public void oom() {
        List<byte[]> list = new LinkedList<>();
        int index = 1;
        
        while (true) {
            byte[] b = new byte[10 * 1024 * 1024]; // 10MB byte object
            list.add(b);
            Runtime rt = Runtime.getRuntime();
            System.out.printf("[%3s] Available heap memory: %s%n", index++, rt.freeMemory());
        }
            
    }
}
