package dev.shubham.productcatalog.exceptions;

public class NotFoundException extends Exception{
    public NotFoundException(String message){
        super(message);
    }
}