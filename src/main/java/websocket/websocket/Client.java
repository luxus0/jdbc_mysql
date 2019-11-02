package websocket.websocket;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {


@Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String surname;

    private int age;

    private int pesel;

    private String email;

    private Long numberAccount;

    public Client(){}

    public Client(int id, String name, String surname, int age, int pesel, String email, Long numberAccount) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.pesel = pesel;
        this.email = email;
        this.numberAccount = numberAccount;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPesel() {
        return pesel;
    }

    public void setPesel(int pesel) {
        this.pesel = pesel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(Long numberAccount) {
        this.numberAccount = numberAccount;
    }

    @Override
    public String toString() {
        return "Client_Bank{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", pesel=" + pesel +
                ", email='" + email + '\'' +
                ", numberAccount=" + numberAccount +
                '}';
    }
}
