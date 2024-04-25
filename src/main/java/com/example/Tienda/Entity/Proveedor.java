package com.example.Tienda.Entity;

public class Proveedor {
    private int id;
    private String name;
    private String lastName;
    private String telephone;
    private String mail;
    private String country;
    private String type;

    public Proveedor(String name, String lastName, String telephone, String mail, String country, String type) {
        this.name = name;
        this.lastName = lastName;
        this.telephone = telephone;
        this.mail = mail;
        this.country = country;
        this.type = type;
    }

    public Proveedor(int id, String name, String lastName, String telephone, String mail, String country, String type) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.telephone = telephone;
        this.mail = mail;
        this.country = country;
        this.type = type;
    }

    public Proveedor() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Proveedor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", telephone='" + telephone + '\'' +
                ", mail='" + mail + '\'' +
                ", country='" + country + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
