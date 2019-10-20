package br.com.thiagoGomes.service.exceptions;

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4680762495158048958L;

    public ObjectNotFoundException(String msg){
        super(msg);
    }

    public ObjectNotFoundException(String msg, Throwable cause){
        super(msg,cause);
    }

}
