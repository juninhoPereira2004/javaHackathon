package models;

public class Visit {
    private int id;
    private int userId;
    private String visitDate;

    public Visit(int id, int userId, String visitDate) {
        this.id = id;
        this.userId = userId;
        this.visitDate = visitDate;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Data da Visita: " + visitDate;
    }
}
