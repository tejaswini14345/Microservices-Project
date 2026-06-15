package com.microservices.cart_service.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncCartService {

    @Async
    public CompletableFuture<String> processCartAsync() {

        try {

            Thread.sleep(2000);

        } catch (InterruptedException e) {

            e.printStackTrace();
        }

        return CompletableFuture.completedFuture(
                "Cart processed asynchronously"
        );
    }
}