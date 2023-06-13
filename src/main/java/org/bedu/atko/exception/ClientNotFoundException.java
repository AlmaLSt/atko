package org.bedu.atko.exception;

public class ClientNotFoundException extends RuntimeException{
    private String name;

    public ClientNotFoundException(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
