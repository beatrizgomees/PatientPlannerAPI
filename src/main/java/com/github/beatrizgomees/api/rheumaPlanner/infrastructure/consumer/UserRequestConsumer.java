package com.github.beatrizgomees.api.rheumaPlanner.infrastructure.consumer;


import jakarta.inject.Singleton;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@Singleton
public class UserRequestConsumer {


    @Incoming("user-connect")
    public void consumer(String message){
        System.out.println("==== Message Recebida: " + message);
    }
}
