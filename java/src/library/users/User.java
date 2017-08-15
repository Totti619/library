package library.users;

public class User implements Comparable<User> {
	
    String name, surnames, userName, password, idCard, telephone, email, address;

    public User() {}
    public User(String userName, String password) {
            this.userName = userName;
            this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
	
	
	
    @Override
    public boolean equals(Object obj) throws ClassCastException, NullPointerException {
            if (this == obj) return true;
            if (getUserName().equals(((User)obj).getUserName())) return true;
            return false;
    }
    @Override
    public int compareTo(User obj) throws ClassCastException, NullPointerException {
            return getUserName().compareTo(obj.getUserName());
    }

    public String getType() {
            return getClass().getSimpleName();
    }

    @Override
    public String toString() {
            return "NAME: " + getUserName() + "\nTYPE: " + getType();
    }

}
