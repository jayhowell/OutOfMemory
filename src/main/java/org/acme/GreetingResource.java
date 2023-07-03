package org.acme;

import java.util.LinkedList;
import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @GET
    @Path("status")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
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
