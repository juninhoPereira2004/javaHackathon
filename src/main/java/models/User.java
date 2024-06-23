package models;

public class User {
    private int id;
    private String name;
    private String role;

    public User(int id, String name, String role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + name + ", Função: " + role;
    }
}
