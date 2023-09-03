package com.bvhardworker.telegrambot.observers;

import com.bvhardworker.messages.drawgenerator.Response;
import io.grpc.stub.StreamObserver;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Getter
public class GenerateDrawResponseObserver implements StreamObserver<Response.GenerateDrawResponse> {
    private static final Logger logger = LoggerFactory.getLogger(GenerateDrawResponseObserver.class);
    Response.GenerateDrawResponse response;

    @Override
    public void onNext(Response.GenerateDrawResponse value) {
        response = value;
    }

    @Override
    public void onError(Throwable t) {
        logger.error("Received error. Unable to generate draw", t);
    }

    @Override
    public void onCompleted() {}
}
