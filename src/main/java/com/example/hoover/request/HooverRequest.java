package com.example.hoover.request;

import com.example.hoover.interfaces.Hoover;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import org.springframework.lang.NonNull;

import java.util.Arrays;

public class HooverRequest implements Hoover {
    @JsonProperty("roomSize")
    @JsonPropertyDescription("defines X,Y right upper corner coordinates for the room")
    @NonNull
    Integer[] roomSize;
    @JsonProperty("coords")
    @JsonPropertyDescription("defines X,Y coordinates for the vacuum cleaner")
    @NonNull
    Integer[] vacuumCoordinates;
    @JsonProperty("patches")
    @JsonPropertyDescription("defines X,Y coordinates for the spots")
    @NonNull
    Integer[][] spotsCoordinates;
    @JsonProperty("instructions")
    @JsonPropertyDescription("defines the directions followed by the vacuum cleaner")
    @NonNull
    String instructions;

    public HooverRequest() {
    }

    public Integer[] getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(Integer[] roomSize) {
        this.roomSize = roomSize;
    }

    public Integer[] getVacuumCoordinates() {
        return vacuumCoordinates;
    }

    public void setVacuumCoordinates(Integer[] vacuumCoordinates) {
        this.vacuumCoordinates = vacuumCoordinates;
    }

    public Integer[][] getSpotsCoordinates() {
        return spotsCoordinates;
    }

    public void setSpotsCoordinates(Integer[][] spotsCoordinates) {
        this.spotsCoordinates = spotsCoordinates;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @Override
    public String toString() {
        return "HooverRequest{" +
                "roomSize=" + Arrays.toString(roomSize) +
                ", vacuumCoordinates=" + Arrays.toString(vacuumCoordinates) +
                ", spotsCoordinates=" + Arrays.toString(spotsCoordinates) +
                ", instructions='" + instructions + '\'' +
                '}';
    }
}
