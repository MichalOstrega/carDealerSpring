package pl.sdacademy.spring.car_dealer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;


@Entity
public class Customer extends BaseModel implements Serializable {
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column (name = "document_no")
    private String documentNo;
    @Column (name = "telephone")
    private String telephone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
