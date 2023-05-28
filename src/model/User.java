package model;

import java.util.Calendar;

public abstract class User {
    
    private String id;
    private String name;
    private Calendar signUp;
    
    public User(String id, String name, Calendar signUp) {
        this.id = id;
        this.name = name;
        this.signUp = signUp;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Calendar getSignUp() {
        return signUp;
    }
    public void setSignUp(Calendar signUp) {
        this.signUp = signUp;
    }
}
