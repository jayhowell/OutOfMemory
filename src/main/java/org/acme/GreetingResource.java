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
public class GreetingResource {
    public static boolean ready=true;
    public static boolean alive=true;

    public static void main(String[] args) {
        Quarkus.run(args);
    }
    
    @GET
    @Path("status")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return System.getenv("HOSTNAME")+"Current status Ready/Alive("+GreetingResource.ready+"/"+GreetingResource.alive+")";
    }

    @GET
    @Path("setReady")
    @Produces(MediaType.TEXT_PLAIN)
    public String setReady() {
        GreetingResource.ready=true;
        return System.getenv("HOSTNAME")+":Ready = " + GreetingResource.ready;
    }

    @GET
    @Path("setNotReady")
    @Produces(MediaType.TEXT_PLAIN)
    public String setNotReady() {
        GreetingResource.ready=false;
        return System.getenv("HOSTNAME")+":Ready = " + GreetingResource.ready; 
    }

    @GET
    @Path("setAlive")
    public String setAlive()
    {
        GreetingResource.alive=true;
        return System.getenv("HOSTNAME")+":Ready = " + GreetingResource.alive;
    }

    @GET
    @Path("setNotAlive")
    public String setNotAlive()
    {
        GreetingResource.alive=false;
        return System.getenv("HOSTNAME")+":Ready = " + GreetingResource.alive;
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
