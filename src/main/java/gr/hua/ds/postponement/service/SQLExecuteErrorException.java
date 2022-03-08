package gr.hua.ds.postponement.service;

public class SQLExecuteErrorException extends RuntimeException {

    public SQLExecuteErrorException(String exception) {
        super(exception);
    }
}
