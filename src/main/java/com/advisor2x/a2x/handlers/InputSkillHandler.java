package com.advisor2x.a2x.handlers;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.SkillStreamHandler;

public class InputSkillHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new LaunchRequestHandler(),
                        new FallbackIntentHandler(),
                        new WebcastIntentHandler(),
                        new LaunchRequestHandler(),
                        new SessionDateIntentHandler())
                .build();
    }

    public InputSkillHandler() {
        super(getSkill());
    }

}