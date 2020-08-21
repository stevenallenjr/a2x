package com.advisor2x.a2x.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class LaunchRequestHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Welcome to the Advisor2X Alexa app. To use me, say something like: Alexa, ask Advisor2X when the next digital session is. "
        		+ "Or, Alexa, ask Advisor2X what digital session is on September 10th.";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("A2X", speechText)
                .withReprompt(speechText)
                .build();
    }

}
