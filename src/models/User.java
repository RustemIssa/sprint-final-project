package models;

public class User {
    private int id;
    private String email;
    private String password;
    private String name;
    private int roleId;

    public User() {
    }

    public User(int id, String email, String password, String name, int roleId) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.roleId = roleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
