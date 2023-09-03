package com.bvhardworker.telegrambot.services;

import com.bvhardworker.messages.drawgenerator.DrawGeneratorServiceGrpc;
import com.bvhardworker.messages.drawgenerator.DrawType;
import com.bvhardworker.messages.drawgenerator.Request;
import com.bvhardworker.messages.drawgenerator.Response;
import com.bvhardworker.telegrambot.config.DrawGeneratorServiceConfig;
import com.bvhardworker.telegrambot.observers.GenerateDrawResponseObserver;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrawGeneratorService {
    Logger logger = LoggerFactory.getLogger(DrawGeneratorService.class);
    private final ManagedChannel channel;
    public DrawGeneratorService(DrawGeneratorServiceConfig config) {
        channel = ManagedChannelBuilder.forAddress(config.getUrl(), config.getPort())
                .usePlaintext()
                .build();
    }

    public Response.GenerateDrawResponse generateDraw(@NonNull List<String> participants, @NonNull DrawType drawType, int groupsCount) {
        DrawGeneratorServiceGrpc.DrawGeneratorServiceStub stub = DrawGeneratorServiceGrpc.newStub(channel);
        Request.GenerateDrawRequest.Builder requestBuilder = Request.GenerateDrawRequest.newBuilder()
                .addAllParticipants(participants)
                .setDrawType(drawType);

        if (DrawType.GROUPS == drawType) {
            if (groupsCount < 1) {
                logger.error("Received invalid group counts = {}. Using 1 by default", groupsCount);
                groupsCount = 1;
            }

            requestBuilder.setGroupsCount(groupsCount);
        }

        GenerateDrawResponseObserver observer = new GenerateDrawResponseObserver();
        stub.generate(requestBuilder.build(), observer);
        return observer.getResponse();
    }
}
