package com.example.demo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@RestController
@RequestMapping("/TEST")
public class Controller {

    @GetMapping("/VCDR/{id}")
    public ResponseEntity<Object> getVDCR2(@PathVariable String id){
        Map<String, Boolean> response = new HashMap<>();
        Float value = new Float(id);
        if (value>0 && value <=1) {
            if (value >= 0.6 && value < 1) {
                response.put("glucoma-suspect", true);
            } else if(value<0.6){
                response.put("glucoma-suspect", false);
            }
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/VCDR")
    public ResponseEntity<Object> getVDCR1(@RequestParam(name = "ratio") String ratio){
        Map<String, Boolean> response = new HashMap<>();
        Float value = new Float(ratio);
        if (value>0 && value <=1) {
            if (value >= 0.6 && value <= 1) {
                response.put("glucoma-suspect", true);
            } else if(value<0.6){
                response.put("glucoma-suspect", false);
            }
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/VCDR/")
    public ResponseEntity<Object> getVDCR(@RequestBody String ratio) {
        Float value=0.0F;
        Map<String, Boolean> response = new HashMap<>();
        JsonParser parser= new JsonParser();
       JsonObject value1= parser.parse(ratio).getAsJsonObject();
       if(value1.get("ratio")!=null){
            value = new Float(value1.get("ratio").getAsString());
       }

        //Long value=ratio;
        if (value>0 && value <=1) {
            if (value >= 0.6 && value < 1) {
                response.put("glucoma-suspect", true);
            } else if (value < 0.6) {
                response.put("glucoma-suspect", false);
            }
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }
}
