package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class ContactData {

    @Id
    @Column(name = "id")
    private int id;

    @Expose
    @Column(name = "firstname")
    private String firstName;

    @Expose
    @Column(name = "lastname")
    private String lastName;

    @Column(name = "address")
    @Type(type = "text")
    private String address;

    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homePhoneNumber;

    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhoneNumber;

    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String workPhoneNumber;

    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email1;

    @Expose
    @Column(name = "email2")
    @Type(type = "text")
    private String email2;

    @Expose
    @Column(name = "email3")
    @Type(type = "text")
    private String email3;

    @Transient
    private String allPhones;

    @Transient
    private String allEmails;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();


//    @Column(name = "photo")
//    @Type(type = "text")
//    private String photo;


    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homePhoneNumber;
    }

    public String getMobilePhone() {
        return mobilePhoneNumber;
    }

    public String getWorkPhone() {
        return workPhoneNumber;
    }

    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getAllEmails() { return allEmails; }

    public Groups getGroups() {
        return new Groups(groups);
    }

//    public File getPhoto() {
//        return new File(photo);
//    }


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

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withHomePhone(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
        return this;
    }

    public ContactData withWorkPhone(String workPhoneNumber) {
        this.workPhoneNumber = workPhoneNumber;
        return this;
    }

    public ContactData withEmail_1(String email1) {
        this.email1 = email1;
        return this;
    }

    public ContactData withEmail_2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail_3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }

//    public ContactData withPhoto(File photo) {
//        this.photo = photo.getPath();
//        return this;
//    }


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
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(address, that.address) &&
                Objects.equals(homePhoneNumber, that.homePhoneNumber) &&
                Objects.equals(mobilePhoneNumber, that.mobilePhoneNumber) &&
                Objects.equals(workPhoneNumber, that.workPhoneNumber) &&
                Objects.equals(email1, that.email1) &&
                Objects.equals(email2, that.email2) &&
                Objects.equals(email3, that.email3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, address, homePhoneNumber, mobilePhoneNumber, workPhoneNumber, email1, email2, email3);
    }

}
