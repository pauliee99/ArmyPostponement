package gr.hua.dit.controller;

public class UserNotFoundException extends RuntimeException  {
    public UserNotFoundException(String exception) {
        super(exception);
    }
}
