package entity;

public class Student extends User {
    private String name;
    private String email;
    private String phone;
    private String address;

    // 无参构造器
    public Student() {}

    // 全参构造器
    public Student(int userId, String username, String password, String role, String name, String email, String phone, String address) {
        super(userId, username, password, role);
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;

        // 同步 username 和 name
        syncUsernameWithName();
    }

    // Getter 和 Setter 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

        // 每次设置 name 时同步更新 username
        syncUsernameWithName();
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

    // 自定义方法：同步 username 和 name
    private void syncUsernameWithName() {
        super.setUsername(this.name); // 调用 User 类的 setUsername 方法
    }
}