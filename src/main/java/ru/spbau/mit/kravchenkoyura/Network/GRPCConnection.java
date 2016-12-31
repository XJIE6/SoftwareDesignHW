package ru.spbau.mit.kravchenkoyura.Network;

import java.io.IOException;

import io.grpc.*;
import io.grpc.stub.StreamObserver;
import ru.spbau.mit.kravchenkoyura.Control.Message;
import ru.spbau.mit.kravchenkoyura.Control.Messenger;
import ru.spbau.mit.kravchenkoyura.Control.NetworkListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.spbau.mit.kravchenkoyura.proto.MessengerGrpc;
import ru.spbau.mit.kravchenkoyura.proto.MessengerOuterClass;

/**
 * Created by YuryKravchenko on 21/12/2016.
 */
public class GRPCConnection implements Network {
    private Server server;
    private ManagedChannel channel;
    private StreamObserver<MessengerOuterClass.Message> streamObserver;

    private NetworkListener listener;

    private final Logger LOGGER = LoggerFactory.getLogger(Messenger.class);

    @Override
    public void start(NetworkListener listener) {
        this.listener = listener;
    }

    @Override
    public void disconnect() {
        LOGGER.info("Disconnecting.");
        if (streamObserver != null) {
            streamObserver.onCompleted();
            streamObserver = null;
        }
        if (server != null) {
            server.shutdown();
        }
        if (channel != null) {
            channel.shutdown();
        }
    }

    @Override
    public void connect(String ip, String port) {
        LOGGER.info("Connecting.");
        channel = ManagedChannelBuilder.forAddress(ip, Integer.parseInt(port)).usePlaintext(true).build();
        MessengerGrpc.MessengerStub stub = MessengerGrpc.newStub(channel);
        streamObserver = stub.chat(getStreamObserver());
    }

    @Override
    public void wait(String port) {
        LOGGER.info("Connecting.");
        server = ServerBuilder.forPort(Integer.parseInt(port))
                .addService(new MessengerGrpc.MessengerImplBase() {
                    @Override
                    public StreamObserver<MessengerOuterClass.Message> chat(StreamObserver<MessengerOuterClass.Message> responseObserver) {
                        streamObserver = responseObserver;
                        return getStreamObserver();
                    }
                })
                .build();
        try {
            server.start();
        } catch (IOException e) {
            LOGGER.error("Connection failed.");
            listener.onError("Connection failed.");
        }
    }

    @Override
    public void send(Message message) {
        LOGGER.info("Sending.");
        streamObserver.onNext(MessengerOuterClass.Message.newBuilder().setName(message.getName()).setText(message.getMessage()).build());
    }

    private StreamObserver<MessengerOuterClass.Message> getStreamObserver() {
        return new StreamObserver<MessengerOuterClass.Message>() {
            @Override
            public void onNext(MessengerOuterClass.Message message) {
                listener.onReceive(new Message(message.getName(), message.getText()));
            }

            @Override
            public void onError(Throwable throwable) {
                listener.onError("Connecting error.");
                disconnect();
                listener.onDisconnect();
            }

            @Override
            public void onCompleted() {
                streamObserver = null;
                listener.onDisconnect();
            }
        };
    }
}
