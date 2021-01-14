package com.github.andrad.grpc.greeting.server

import com.proto.greet.GreetServiceGrpc.GreetServiceImplBase
import com.proto.greet.GreatRequest
import io.grpc.stub.StreamObserver
import com.proto.greet.GreatResponse
import com.proto.greet.Greeting

class GreetServiceImpl : GreetServiceImplBase() {
    override fun greet(request: GreatRequest, responseObserver: StreamObserver<GreatResponse>) {
        // extract the fields we need
        val greeting = request.greeting
        val firstName = greeting.firstName

        // create the response
        val result = "Hello $firstName"
        val response = GreatResponse.newBuilder()
                .setResult(result)
                .build()

        // send the response
        responseObserver.onNext(response)

        // complete the RPC call
        responseObserver.onCompleted()


        //super.greet(request, responseObserver);
    }
}