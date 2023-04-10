package com.example.Okul.modal;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class DersResponse {
    private Ders ders;
    public <S extends DersResponse> DersResponse(Object save, HttpStatus ok) {
    }
    private String returnMessage;
    public DersResponse(Ders ders , String returnMessage){
        this.ders=ders;
        this.returnMessage=returnMessage;
    }
}
