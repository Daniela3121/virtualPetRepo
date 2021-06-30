package com.example.virtualpetmonster;
//class
public class virtualPet {
    String name;
    boolean energy;
    int health;
    int mood;

    //constructor
    public virtualPet (String name, boolean energy, int health, int mood){
        this.name=name;
        this.health=health;
        this.energy=energy;
        this.mood=mood;
    }
    //sets and gets

    public void setName (String newName){//setter
        this.name = newName;
    }

    public String getName(){//getter
        return this.name;
    }

    public void setEnergy (boolean newEnergy){
        this.energy=newEnergy;
    }
    public boolean getEnergy (){
        return this.energy;
    }

    public void setHealth (int newHealth){
        this.health = newHealth;
    }

    public int getHealth (){
        return this.health;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

}

