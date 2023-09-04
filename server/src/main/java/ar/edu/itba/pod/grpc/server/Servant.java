package ar.edu.itba.pod.grpc.server;

import ar.edu.itba.pod.grpc.health.HealthServiceGrpc;
import ar.edu.itba.pod.grpc.health.PingRequest;
import ar.edu.itba.pod.grpc.health.PingResponse;
import io.grpc.stub.StreamObserver;

public class Servant extends HealthServiceGrpc.HealthServiceImplBase {

    @Override
    public void ping(PingRequest req, StreamObserver<PingResponse> responseObserver) {
        PingResponse reply = PingResponse.newBuilder().setMessage("Hello " + req).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
