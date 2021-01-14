package com.github.andrad.grpc.greeting.client

import kotlin.jvm.JvmStatic
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import com.proto.greet.GreetServiceGrpc.GreetServiceBlockingStub
import com.proto.greet.GreetServiceGrpc
import com.proto.greet.Greeting
import com.proto.greet.GreatRequest
import com.proto.greet.GreatResponse

object GreetingClient {
    @JvmStatic
    fun main(args: Array<String>) {
        println("Hello I'm a gRPC Client")
        val channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build()
        println("Creating stub")
        //DummyServiceGrpc.DummyServiceBlockingStub syncClient = DummyServiceGrpc.newBlockingStub(channel);
        //DummyServiceGrpc.DummyServiceFutureStub asyncClient = DummyServiceGrpc.newFutureStub(channel);

        // created a greet service client (blocking - synchronous)
        val greetClient = GreetServiceGrpc.newBlockingStub(channel)

        // created a protocol buffer greeting message
        val greeting = Greeting.newBuilder()
                .setFirstName("Felipe")
                .setLastName("Andrade")
                .build()

        // do the same for a GreerRequest
        val greetRequest = GreatRequest.newBuilder()
                .setGreeting(greeting)
                .build()

        // call the RPC and get back a GreetResponse (protocol buffers)
        val greetResponse = greetClient.greet(greetRequest)
        println(greetResponse.result)
        println("Shutting down channel")
        channel.shutdown()
    }
}