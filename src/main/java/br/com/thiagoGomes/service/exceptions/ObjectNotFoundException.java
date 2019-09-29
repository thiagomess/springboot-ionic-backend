package br.com.thiagoGomes.service.exceptions;

public class ObjectNotFoundException extends RuntimeException {

    private static long serialVersionUID = 1L;

    public ObjectNotFoundException(String msg){
        super(msg);
    }

    public ObjectNotFoundException(String msg, Throwable cause){
        super(msg,cause);
    }

}
