package com.example.hoover.response;

import com.example.hoover.interfaces.Hoover;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.util.List;

public class HooverResponse implements Hoover {

    @JsonProperty("coords")
    @JsonPropertyDescription("defines X,Y coordinates")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer[] coordinates;

    @JsonProperty("patches")
    @JsonPropertyDescription("defines the number of cleaned spots")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer nrOfCleanedSpots;

    @JsonProperty("errors")
    @JsonPropertyDescription("defines the validation errors")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> errorList;

    public HooverResponse() {
    }

    public HooverResponse(Integer[] coordinates, Integer nrOfCleanedSpots) {
        this.coordinates = coordinates;
        this.nrOfCleanedSpots = nrOfCleanedSpots;
    }

    public Integer[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Integer[] coordinates) {
        this.coordinates = coordinates;
    }

    public Integer getNrOfCleanedSpots() {
        return nrOfCleanedSpots;
    }

    public void setNrOfCleanedSpots(Integer nrOfCleanedSpots) {
        this.nrOfCleanedSpots = nrOfCleanedSpots;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }
}
