package models;

public class Alert {
    private int id;
    private int userId;
    private String alertMessage;

    public Alert(int id, int userId, String alertMessage) {
        this.id = id;
        this.userId = userId;
        this.alertMessage = alertMessage;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Alerta: " + alertMessage;
    }
}
