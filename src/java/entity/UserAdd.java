package entity;

public class UserAdd {
    
    private String FullName;
    private String Gender;
    private String Address;
    private String Phone;
    private String Email;
    private String Role;
    private String Password;

    public UserAdd() {
    }

   

    public UserAdd( String FullName, String Gender, String Address, String Phone, String Email, String Role, String Password) {
        
        this.FullName = FullName;
        this.Gender = Gender;
        this.Address = Address;
        this.Phone = Phone;
        this.Email = Email;
        this.Role = Role;
        this.Password = Password;
    }

  

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
}
