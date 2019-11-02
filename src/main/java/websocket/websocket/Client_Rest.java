package websocket.websocket;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class Client_Rest {

private ClientRepo repo;
private Client_Gui gui;


    @GetMapping("/client")
    public @ResponseStatus List<Client> client()
    {
        return repo.findAll();
    }

    @PostMapping("/addClient")
    public @ResponseStatus void addClient(@RequestBody Client client)
    {
        repo.save(client);
    }

    @PutMapping("/updateClient/{id}")
    public @ResponseStatus void updateClient(@PathVariable Integer id)
    {
        List<Client> listClient = new ArrayList<>();
        listClient.add(1,new Client(0,"Marek","Fsatra",23,765444333,"luxeronit@oertpl.zd",5434545566L));
        listClient.add(2,new Client(1,"Dareto","Fsatra",28,765444354,"lfsdfdsfd",5434545566L));
        listClient.add(3,new Client(2,"Jarenta","Kazder",35,7654443,"luxerosdfd@o2",54345455455L));

        Client client = new Client();
        repo.findAll().addAll(listClient);



    }

    @DeleteMapping("/deleteClient/{id}")
    public @ResponseStatus void deleteClient(@PathVariable Integer id)
    {
        repo.deleteById(id);
    }

    @DeleteMapping("/deleteAllClient")
    public @ResponseStatus void deleteAllClient()
    {
        repo.deleteAll();
    }

}
