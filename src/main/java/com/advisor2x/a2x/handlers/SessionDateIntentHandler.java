package com.advisor2x.a2x.handlers;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.advisor2x.a2x.data.Event;
import com.advisor2x.a2x.utils.DateFormatter;
import com.advisor2x.a2x.utils.DateUtils;
import com.advisor2x.a2x.utils.Retriever;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.request.Predicates;

public class SessionDateIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("SessionDateIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();

        if(slots.get("date").getValue() == null) {
            return input.getResponseBuilder()
                    .withSpeech("Please specify a date for me to check.")
                    .withSimpleCard("A2X", "Please specify a date for me to check.")
                    .build();
        }

        String date = slots.get("date").getValue();
        Date amz = null;
        try {
            amz = DateUtils.fromAmz(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        Date today = new Date();

        List<Event> events = Retriever.getEvents();

        Event e = getEventOnDate(amz, events);
        
        if(e == null) {
            return input.getResponseBuilder()
                    .withSpeech("I couldn't find any events on " + DateFormatter.getAmzDateReadout(date) + ".")
                    .withSimpleCard("A2X", "I couldn't find any events on " + DateFormatter.getAmzDateReadout(date) + ".")
                    .build();
        }

        String speechText = "Join us " + (DateUtils.compare(today, amz) == 0 ? "today " : "on ")
                + DateFormatter.getDateAndTimeReadout(e.getDate(), 
                e.getTime()) + " for the Excel 401K session, " + e.getTitle() + ", with "
                + e.getSpeaker() + ". " + e.getDesc();
        
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("A2X", speechText)
                .build();
    }

    private Event getEventOnDate(Date d, List<Event> events) {
        for(int i=0;i<events.size();i++) {
            
            int c = -999;
            try {
                c = DateUtils.compare(d, DateUtils.toDate(events.get(i).getDate()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            
            if(c == 0) {
                return events.get(i);
            }
        }
        return null;
    }
}
