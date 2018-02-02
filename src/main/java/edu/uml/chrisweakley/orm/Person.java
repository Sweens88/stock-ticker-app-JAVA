package edu.uml.chrisweakley.orm;

import javax.persistence.*;

/**
 * Models the person table
 */
@Entity
@Table(name="stocks.person")
public class Person {

    private int id;
    private String firstName;
    private String lastName;
    private String stock;

    /**
     * Primary Key - Unique ID for each person within the person table
     *
     * @return an integer value ID
     */
    @Id
    @Column(name = "id", nullable = false)
    public int getId() {return id;}

    /**
     * Primary Key - Unique ID for each person within the person table
     * @param id an integer value ID
     */
    public void setId(int id) {this.id = id;}


    @Basic
    @Column(name = "symbol", nullable = false, length = 256)
    public String getSymbol() {
        return stock;
    }

    /**
     * Specify the stock symbol
     *
     * @param  symbol String value of the stocks symbol
     */
    public void setSymbol(String symbol) {
        this.stock = symbol;
    }

    /**
     * First Name - first name of the person
     * @return a string representation of a first name
     */
    @Basic
    @Column(name = "first_name", nullable = false)
    public String getFirstName() {return firstName;}

    /**
     * First Name - first name of the person
     * @param firstName a string representation of a first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *Last Name - last name of the person
     * @return the person's last name
     */
    @Basic
    @Column(name = "last_name", nullable = false, length = 256)
    public String getLastName() {
        return lastName;
    }

    /**
     * Specify the person's last name
     * @param lastName a String value
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Equals method for comparing two person records
     * @param o a person to be compared to
     * @return true if equality exists, otherwise false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}

        Person person = (Person) o;

        if (id != person.id) {return false;}
        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null){
            return false;
         }
        return lastName != null ? lastName.equals(person.lastName) : person.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

    /**
     * A toString method for converting person attributes to a readable string
     * @return a string of a persons id, first name and last name
     */
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", stock='" + stock + "\'}";
    }


}
