package co.edu.cesde.ga.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public abstract class Person {
    @NotNull
    private Long userId;
    private String code;
    @NotBlank
    private String documentNumber;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String status;

    public Person() {}

    public Person(Long userId, String code, String documentNumber, String firstName, String lastName, String status) {
        this.userId = userId;
        this.code = code;
        this.documentNumber = documentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public String getCode() {
        return code;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStatus() {
        return status;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public abstract String getRole();

    @Override
    public String toString() {
        return "=== STUDENT INFO ===\n" +
                "ID: " + getUserId() + "\n" +
                "Código: " + getCode() + "\n" +
                "Documento: " + getDocumentNumber() + "\n" +
                "Nombre: " + getFirstName() + " " + getLastName() + "\n" +
                "Estado: " + getStatus();
    }
}
