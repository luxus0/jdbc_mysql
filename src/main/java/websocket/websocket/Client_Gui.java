package websocket.websocket;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;


@Route
public class Client_Gui extends FormLayout {

    private ClientRepo repo;

    private TextField textFieldId;
    private TextField textFieldName;
    private TextField textFieldSurname;
    private TextField textFieldAge;
    private TextField textFieldPesel;
    private TextField textFieldEmail;
    private TextField textFieldNumberAccount;
    private Grid grid;


    private Button buttonAdd;

    public Client_Gui() {
        textFieldId = new TextField("id");
        textFieldId.getStyle().set("Color", "Blue").set("padding-left", "20px").set("padding-right", "20px");
        textFieldId.setMaxWidth("150px");

        textFieldName = new TextField("Name");
        textFieldName.getStyle().set("Color", "Blue").set("padding-left", "20px").set("padding-right", "20px");
        textFieldName.setMaxWidth("150px");


        textFieldSurname = new TextField("Surname");
        textFieldSurname.getStyle().set("Color", "Blue").set("padding-left", "20px").set("padding-right", "20px");
        textFieldSurname.setMaxWidth("150px");


        textFieldAge = new TextField("Age");
        textFieldAge.getStyle().set("Color", "Blue").set("padding-left", "20px").set("padding-right", "20px");
        textFieldAge.setMaxWidth("150px");


        textFieldPesel = new TextField("Pesel");
        textFieldPesel.getStyle().set("Color", "Blue").set("padding-left", "20px").set("padding-right", "20px");
        textFieldPesel.setMaxWidth("150px");


        textFieldEmail = new TextField("Email");
        textFieldEmail.getStyle().set("Color", "Blue").set("padding-left", "20px").set("padding-right", "20px");
        textFieldEmail.setMaxWidth("150px");

        textFieldNumberAccount = new TextField("Number Account");
        textFieldNumberAccount.getStyle().set("Color", "Blue").set("padding-left", "20px").set("padding-right", "20px");
        textFieldNumberAccount.setMaxWidth("150px");

        grid = new Grid();
        grid.addColumn("ID");

        buttonAdd = new Button("ADD", VaadinIcon.AIRPLANE.create());
        buttonAdd.getStyle().set("Color", "Blue").set("padding-left", "20px").set("padding-right", "20px");
        buttonAdd.setMaxWidth("150px");


        addComponentAtIndex(0, textFieldId);
        addComponentAtIndex(1, textFieldName);
        addComponentAtIndex(2, textFieldSurname);
        addComponentAtIndex(3, textFieldAge);
        addComponentAtIndex(4, textFieldPesel);
        addComponentAtIndex(5, textFieldEmail);
        addComponentAtIndex(6, textFieldNumberAccount);
        addComponentAtIndex(7, buttonAdd);
        addComponentAtIndex(8,grid);





        buttonAdd.
                addClickListener(p ->
                {
                    textFieldId.getValue();
                });



    };

    @EventListener(ApplicationReadyEvent.class)
    public void initDB()
    {
        Client client = new Client(Integer.parseInt(textFieldId.getValue() ),textFieldName.getValue(),textFieldSurname.getValue(),Integer.parseInt(textFieldAge.getValue() ),Integer.parseInt(textFieldPesel.getValue() ),textFieldEmail.getValue(),Long.parseLong(textFieldNumberAccount.getValue() ));
        repo.save(client);
    }



}