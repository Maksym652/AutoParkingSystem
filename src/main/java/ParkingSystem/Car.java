/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParkingSystem;


import java.util.*;

public class Car{
    String ownerName;
    String number;
    ArrayList<Visit> visits = new ArrayList<Visit>();

    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String name){ownerName=name;}
    public String getNumber() {
        return number;
    }

    public ArrayList<Visit> getVisits(){
        return visits;
    }

    public boolean addVisit(Visit visit){
        return visits.add(visit);
    }

    public boolean isOnParking() {
        if(visits.isEmpty())
            return false;
        return visits.get(visits.size() - 1).includes(new Visit(new Date(), new Date()));
    }

    public Car(String ownerName, String number)
    {
        this.ownerName = ownerName;
        this.number = number;
    }
    public Car(String ownerName, String number, Date beginTime) {
        this.ownerName = ownerName;
        this.number = number;
        visits.add(new Visit(beginTime, null));
    }

    public ArrayList<Visit> visitsForPeriod(Date from, Date to)
    {
        ArrayList<Visit> filteredList = new ArrayList<Visit>();
        for (Visit visit:
             visits) {
            if(new Visit(from, to).includes(visit))
            {
                filteredList.add(visit);
            }
        }
        return filteredList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return number.equals(car.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
