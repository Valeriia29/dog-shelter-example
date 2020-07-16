package com.example.shelter;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;


import java.util.Set;


import com.example.shelter.db.*;
import com.example.shelter.db.dogs.update.DogUpdateDataAccess;
import com.example.shelter.db.dogs.update.DogUpdateDataAccessImplByNL;
import com.example.shelter.handler.HandlerCountDog;

public class Main {

    private static DogInsertDataAccessInterface dogInsertDataAccess = new DogInsertDataAccess();

    private static ShelterDataAccessInterface shelterDataAccess = new ShelterDataAccess();

    private static DogUpdateDataAccess dogUpdateDataAccess = new DogUpdateDataAccessImplByNL();

    public static void main(String... args) {
        Javalin app = Javalin.create().start(7000);
        app.get("/", ctx -> ctx.result("Hello World 2 "));
        app.get("/example", new Handler()
        {
            @Override
            public void handle(final Context ctx) throws Exception
            {
                ctx.result("This is example");
            }
        });

        Handler handlerCountDogs = new HandlerCountDog();
        app.get("/count", handlerCountDogs);

        app.get("/dogs", new Handler()
        {
            @Override
            public void handle(final Context ctx) throws Exception
            {
                Set<String> names =  shelterDataAccess.getUniqueDogNames();

                ctx.json(names);
            }
        });

        app.get("/html2", ctx -> ctx.html("<h1>Hello <br>World 2</h1>"));
    }


}
