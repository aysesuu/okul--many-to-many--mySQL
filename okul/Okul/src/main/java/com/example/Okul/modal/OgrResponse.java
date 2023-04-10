package com.example.Okul.modal;

import org.springframework.http.HttpStatus;

public class OgrResponse {
    private Ogr ogr;
    public <S extends OgrResponse> OgrResponse(Object save, HttpStatus ok) {
    }
    private String returnMessage;
    public OgrResponse(Ogr ogr , String returnMessage){
        this.ogr=ogr;
        this.returnMessage=returnMessage;
    }


    public Ogr getOgr() {
        return ogr;
    }

    public void setOgr(Ogr ogr) {
        this.ogr = ogr;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }
}
