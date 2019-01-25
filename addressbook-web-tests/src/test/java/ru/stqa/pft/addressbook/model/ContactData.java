package ru.stqa.pft.addressbook.model;

public class ContactData {
    private int id;
    private String firstName;
    private String lastName;
//    private String nickName;
//    private String company;
//    private String address;
//    private String homePhoneNumber;
//    private String mobilePhoneNumber;
//    private String workPhoneNumber;
//    private String email1;
//    private String email2;
//    private String email3;
//    private String homePage;
    private String group;


    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

//    public String getNickName() {
//        return nickName;
//    }
//
//    public String getCompany() {
//        return company;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public String getHomePhoneNumber() {
//        return homePhoneNumber;
//    }
//
//    public String getMobilePhoneNumber() {
//        return mobilePhoneNumber;
//    }
//
//    public String getWorkPhoneNumber() {
//        return workPhoneNumber;
//    }
//
//    public String getEmail1() {
//        return email1;
//    }
//
//    public String getEmail2() {
//        return email2;
//    }
//
//    public String getEmail3() {
//        return email3;
//    }
//
//    public String getHomePage() {
//        return homePage;
//    }

    public String getGroup() {
        return group;
    }


    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

//    public ContactData withNickName(String nickName) {
//        this.nickName = nickName;
//        return this;
//    }
//
//    public ContactData withCompany(String company) {
//        this.company = company;
//        return this;
//    }
//
//    public ContactData withAddress(String address) {
//        this.address = address;
//        return this;
//    }
//
//    public ContactData withHomePhoneNumber(String homePhoneNumber) {
//        this.homePhoneNumber = homePhoneNumber;
//        return this;
//    }
//
//    public ContactData withMobilePhoneNumber(String mobilePhoneNumber) {
//        this.mobilePhoneNumber = mobilePhoneNumber;
//        return this;
//    }
//
//    public ContactData withWorkPhoneNumber(String workPhoneNumber) {
//        this.workPhoneNumber = workPhoneNumber;
//        return this;
//    }
//
//    public ContactData withEmail_1(String email1) {
//        this.email1 = email1;
//        return this;
//    }
//
//    public ContactData withEmail_2(String email2) {
//        this.email2 = email2;
//        return this;
//    }
//
//    public ContactData withEmail_3(String email3) {
//        this.email3 = email3;
//        return this;
//    }
//
//    public ContactData withHomePage(String homePage) {
//        this.homePage = homePage;
//        return this;
//    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }


    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
