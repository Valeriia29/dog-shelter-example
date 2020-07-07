package com.example.shelter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

import com.example.shelter.animal.CurrentDogStatus;
import com.example.shelter.animal.Dog;
import com.example.shelter.animal.DogTime;
import com.example.shelter.db.ShelterDataAccess;

import java.util.List;

public class Main
{
    public static void main(String... args)
    {
        //Работа с БД
        System.out.println("текущее количество dogs = "  + ShelterDataAccess.getCountDogs());

        int dogId = 3;
        String dbDogName = ShelterDataAccess.getDogNameById(dogId);
        System.out.println("Dog с id = " + dogId + " зовут " + dbDogName );

        System.out.println("все имена собак: " + ShelterDataAccess.getAllDogNames());

        System.out.println("все уникальные имена собак: " + ShelterDataAccess.getUniqueDogNames());

        System.out.println("вся информация о собаках: " + ShelterDataAccess.getAllDogs());

        //Работа с БД закончена

        System.out.println("Выберете сохранять к коллекцию(1) или в массив(2)?");
        Scanner in = new Scanner(System.in);
        int selectedCase = in.nextInt();
        if (selectedCase == 1)
        {
            caseWithArrayList();
        }
        else if (selectedCase == 2)
        {
            caseWithArray();
        }
        else
        {
            System.out.println("некорректный ввод. Пока");
        }
    }

    private static void caseWithArrayList()
    {
        Scanner in = new Scanner(System.in);

        List<Dog> dogs = new ArrayList<>();

        String string = null;

        while (!"exit".equals(string))
        {
            System.out.println("dog new name:");
            string = in.nextLine();
            Dog newDog = new Dog();
            newDog.name = string;
            newDog.dogStatus = CurrentDogStatus.getStatus();
            try
            {
                newDog.visitTime = DogTime.dogAdmissionTime();
            }
            catch (Exception e)
            {
                System.out.println("wrong date format");
                newDog.visitTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
            }
            dogs.add(newDog);
        }

        System.out.println(dogs);
    }

    private static void caseWithArray()
    {
        Scanner in = new Scanner(System.in);

        Dog[] dogs = new Dog[5];
        int index = 0;
        String string = null;
        while (!"exit".equals(string))
        {
            System.out.println("dog new name:");
            string = in.nextLine();
            Dog newDog = new Dog();
            newDog.name = string;
            newDog.dogStatus = CurrentDogStatus.getStatus();
            try
            {
                newDog.visitTime = DogTime.dogAdmissionTime();
            }
            catch (Exception e)
            {
                System.out.println("wrong date format");
                newDog.visitTime = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
            }
            dogs[index] = newDog;
            index++;
        }

        //вывод результата на экран
        for (final Dog dog : dogs)
        {
            System.out.println(dog);
        }
    }
}
