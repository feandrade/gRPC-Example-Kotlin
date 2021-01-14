package com.github.andrad.grpc.greeting.server

import io.grpc.ServerBuilder
import java.io.IOException

object GreetingServer {
    @Throws(IOException::class, InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        println("Hello gRPC")
        val server = ServerBuilder.forPort(50051)
                .addService(GreetServiceImpl())
                .build()
        server.start()
        Runtime.getRuntime().addShutdownHook(Thread {
            println("Received Shutdown Request")
            server.shutdown()
            println("Successfully stopped the server")
        })
        server.awaitTermination()
    }
}