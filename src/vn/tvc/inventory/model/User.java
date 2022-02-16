package vn.tvc.inventory.model;

public class User {
    private long id;
    private String fullName;
    private String phone;
    private String email;
    private Role role;

    public User(long id, String fullName, String phone, String email, Role role) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return id + "," + fullName + "," + phone + "," + email + "," + role.getValue();
    }

    public static User parseUser(String raw) {
        String[] strings = raw.split(",");
        long id = Long.parseLong(strings[0]);
        String fullName = strings[1];
        String phone = strings[2];
        String email = strings[3];
        Role role = Role.parseRole(strings[4]);
        return new User(id, fullName, phone, email, role);
    }

}
