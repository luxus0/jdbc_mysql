package websocket.websocket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client,Integer> {

    public Client findByName(String name);
    public Client findByUsername(String username);
    public Client findByAge(Integer age);
    public Client findByPesel(Integer pesel);
    public Client findByEmail(String email);
    public Client findByNumberAccount(Long numberAccount);

    public void addName();
    public void addSurname();
    public void addAge();
    public void addPesel();
    public void addEmail();
    public void addNumberAccound();

    public void deleteName();
    public void deleteSurname();
    public void deleteAge();
    public void deletePesel();
    public void deleteEmail();
    public void deleteNumberAccound();



}
