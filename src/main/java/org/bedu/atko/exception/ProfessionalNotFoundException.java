package org.bedu.atko.exception;

public class ProfessionalNotFoundException extends RuntimeException{
    private String name;

    public ProfessionalNotFoundException(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
