package com.example.nlpaisentry;


import com.fasterxml.jackson.annotation.JsonCreator;

import com.fasterxml.jackson.annotation.JsonProperty;



public class InvitationData {
    private final String name;
    private final String instruction;
    private final String location;
    private final String reason;

    @JsonCreator
    public InvitationData(@JsonProperty("name") String name,
                          @JsonProperty("instruction") String instruction,
                          @JsonProperty("location") String location,
                          @JsonProperty("reason") String reason) {
        this.name = name;
        this.instruction = instruction;
        this.location = location;
        this.reason = reason;
    }

    public String getName() {
        return name;
    }

    public String getInstruction() {
        return instruction;
    }

    public String getLocation() {
        return location;
    }

    public String getReason() {
        return reason;
    }
}

