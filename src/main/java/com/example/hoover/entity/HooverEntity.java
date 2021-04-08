package com.example.hoover.entity;


import javax.persistence.*;

@Entity
@Table(name="HOOVER")
public class HooverEntity {
    @Id
    @GeneratedValue()
    private Integer id;
    @Column(name="Request")
    private String request;
    @Column(name="Response")
    private String response;

    private HooverEntity(String request, String response) {
        this.request = request;
        this.response = response;
    }

    public HooverEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public static class Builder{
        private String request,response;

        public Builder() {
        }

        public Builder setRequest(String request) {
            this.request = request;
            return this;
        }

        public Builder setResponse(String response) {
            this.response = response;
            return this;
        }
        public HooverEntity build(){
            return new HooverEntity(request,response);
        }
    }
}
