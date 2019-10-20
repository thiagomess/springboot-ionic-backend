package br.com.thiagoGomes.service.exceptions;

public class DataIntegrityException extends RuntimeException {

	private static final long serialVersionUID = -4680762495158048958L;

    public DataIntegrityException(String msg){
        super(msg);
    }

    public DataIntegrityException(String msg, Throwable cause){
        super(msg,cause);
    }

}
