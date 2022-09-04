package se.lexicon.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private BigDecimal personalNumber;
    private String address;
    private LocalDate RegisterDate;
    private boolean status;

    public Student() {
    }

    public Student(int id, String firstName, String lastName, BigDecimal personalNumber, String address, LocalDate registerDate, boolean status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
        this.address = address;
        RegisterDate = registerDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(BigDecimal personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getRegisterDate() {
        return RegisterDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        RegisterDate = registerDate;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return getId() == student.getId() && isStatus() == student.isStatus() && Objects.equals(getFirstName(), student.getFirstName()) && Objects.equals(getLastName(), student.getLastName()) && Objects.equals(getPersonalNumber(), student.getPersonalNumber()) && Objects.equals(getAddress(), student.getAddress()) && Objects.equals(getRegisterDate(), student.getRegisterDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getPersonalNumber(), getAddress(), getRegisterDate(), isStatus());
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", personalNumber=" + personalNumber +
                ", address='" + address + '\'' +
                ", RegisterDate=" + RegisterDate +
                ", status=" + status +
                '}';
    }

}
