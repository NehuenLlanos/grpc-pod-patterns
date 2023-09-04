package ar.edu.itba.pod.grpc.client;

import ar.edu.itba.pod.grpc.health.HealthServiceGrpc;
import ar.edu.itba.pod.grpc.health.HealthServiceOuterClass;
import ar.edu.itba.pod.grpc.health.PingRequest;
import ar.edu.itba.pod.grpc.health.PingResponse;
import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class Client {
//    private static Logger logger = LoggerFactory.getLogger(Client.class);
//
//    public static void main(String[] args) throws InterruptedException {
//        logger.info("grpc-com-patterns Client Starting ...");
//        logger.info("grpc-com-patterns Client Starting ...");
//        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
//                .usePlaintext()
//                .build();
//
//        try {
//
//        } finally {
//            channel.shutdown().awaitTermination(10, TimeUnit.SECONDS);
//        }
//    }

    private static Logger logger = LoggerFactory.getLogger(Client.class);

    private final HealthServiceGrpc.HealthServiceBlockingStub blockingStub;

    public Client(Channel channel) {
        // Nos apropiamos de un stub que me va a permitir mandar un mensaje
        blockingStub = HealthServiceGrpc.newBlockingStub(channel);
    }
    public void greet(String name) {
        PingRequest request = PingRequest.newBuilder().build();
        PingResponse response;
        try {
            response = blockingStub.ping(request);
            System.out.println(response.getMessage());
        } catch (StatusRuntimeException e) {}
    }

    public static void main(String[] args) throws InterruptedException {
        logger.info("grpc-class-example Client Starting ...");
        String user = "pod";
        // Access a service running on the local machine on port 50051
        String target = "10.9.69.194:50051";

        ManagedChannel channel = ManagedChannelBuilder.forTarget(target)
                .usePlaintext().build();
        logger.info("grpc-class-example Client Starting ...");

        try {
            logger.info("grpc-class-example Client Starting ...");
            Client client = new Client(channel);
            client.greet(user);
        } finally {
            channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
        }
    }
}
