package springbackend.model;

public class ServiceEntrance {
    private String enteredPassword;

    private final String correctPassword = "deal";

    public String getCorrectPassword() {
        return correctPassword;
    }

    public String getEnteredPassword() {
        return enteredPassword;
    }

    public void setEnteredPassword(String enteredPassword) {
        this.enteredPassword = enteredPassword;
    }

    @Override
    public String toString() {
        return "ServiceEntrance{" +
                "enteredPassword='" + enteredPassword + '\'' +
                ", correctPassword='" + correctPassword + '\'' +
                '}';
    }
}