package models;

public class HealthRecord {
    private int id;
    private int userId;
    private String record;

    public HealthRecord(int id, int userId, String record) {
        this.id = id;
        this.userId = userId;
        this.record = record;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Registro: " + record;
    }
}
