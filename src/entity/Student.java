package entity;

public class Student extends User {
    private String name;
    private String email;
    private String phone;
    private String address;

    public Student() {}

    public Student(int userId, String username, String password, String role, String name, String email, String phone, String address) {
        super(userId, username, password, role);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}