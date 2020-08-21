package com.advisor2x.a2x.handlers;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.advisor2x.a2x.data.Event;
import com.advisor2x.a2x.utils.DateFormatter;
import com.advisor2x.a2x.utils.DateUtils;
import com.advisor2x.a2x.utils.Retriever;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

public class WebcastIntentHandler implements RequestHandler {
    
    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("WebcastIntent"));
    }
    
    @Override
    public Optional<Response> handle(HandlerInput input) {
        Date today = new Date();
        Date session = null;
        List<Event> events = Retriever.getEvents();
        Event nextEvent;
        
        nextEvent = Retriever.getNextEvent(events,today);
        
        String speechText;
        
        if(nextEvent == null) {
            speechText = "There are no upcoming digital sessions.";
        }

        else {
            try {
                session = DateUtils.toDate(Retriever.getNextEvent(events, today).getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            speechText = "Join us " + (DateUtils.compare(today, session) == 0 ? "today " : "on ")
                    + DateFormatter.getDateAndTimeReadout(nextEvent.getDate(), 
                    nextEvent.getTime()) + " for the Excel 401K session, " + nextEvent.getTitle() + ", with "
                    + nextEvent.getSpeaker() + ". " + nextEvent.getDesc();
        }
        
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("A2X", speechText)
                .build();
    }
    
}
