package backend.model;


import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;

@Component
public class ServiceEntrance {

    private String enteredPassword;
    private final String correctPassword = "deal";

    public ServiceEntrance() {
    }

    public String getCorrectPassword() {
        return correctPassword;
    }

    public String getEnteredPassword() {
        return enteredPassword;
    }

    public void setEnteredPassword(String enteredPassword) {
        this.enteredPassword = enteredPassword;
    }
}