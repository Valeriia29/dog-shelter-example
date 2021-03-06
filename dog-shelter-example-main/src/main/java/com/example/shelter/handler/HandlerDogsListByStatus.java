package com.example.shelter.handler;

import com.example.shelter.animal.Dog;
import com.example.shelter.db.ShelterDataAccess;
import com.example.shelter.db.dogs.select.DogSelectDataAccess;
import com.example.shelter.db.dogs.select.DogSelectDataAccessImpl;
import io.javalin.http.Context;
import io.javalin.http.Handler;

import java.util.List;

public class HandlerDogsListByStatus implements Handler
{
    private DogSelectDataAccess dogSelectDataAccess = new DogSelectDataAccessImpl();

    @Override
    public void handle(final Context ctx) throws Exception {
        String status = ctx.pathParam("dogStatus");
        List<Dog> dogList = dogSelectDataAccess.getDogByStatus(status);
        ctx.json(dogList);
    }
}
