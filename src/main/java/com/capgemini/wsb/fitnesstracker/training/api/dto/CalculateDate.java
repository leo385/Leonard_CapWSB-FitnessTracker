package com.capgemini.wsb.fitnesstracker.training.api.dto;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalculateDate extends JsonDeserializer<Date> {

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String date = p.getText();

        for (SimpleDateFormat dateFormat : DATE_FORMATS) {
            try {
                return dateFormat.parse(date);
            } catch (ParseException e) {
            }
        }
        throw new IOException("Unable to parse date: " + date);
    }

    private static final SimpleDateFormat[] DATE_FORMATS = new SimpleDateFormat[]{
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX"),
            new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    };


}
