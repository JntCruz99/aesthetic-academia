package br.com.aesthetic.aesthetic.academia.service.exceptions;

public class DuplicateEnrollmentException extends RuntimeException{

    public DuplicateEnrollmentException(String msg){
        super(msg);
    }
}
