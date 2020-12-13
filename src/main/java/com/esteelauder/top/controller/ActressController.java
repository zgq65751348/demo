package com.esteelauder.top.controller;

import com.esteelauder.top.model.Actress;
import com.esteelauder.top.parameter.Params;
import com.esteelauder.top.service.ActressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author 墨茗琦妙
 */
@RestController
@RequestMapping(value = "/act")
public class ActressController {

    @Autowired
    ActressService actressService;

    @GetMapping(value = "/all")
    public Collection<Actress> all(){
        return actressService.all();
    }

    @PostMapping(value = "/up")
    public void up(@RequestBody Params params){
        actressService.up(params);
    }
}
