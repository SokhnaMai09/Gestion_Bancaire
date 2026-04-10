import java.sql.Connection;

import dao.ClientDAO;
import dao.CompteDAO;
import dao.OperationDAO;
import database.DatabaseConnection;
import model.Client;
import model.Compte;

public class Main {

    public static void main(String[] args) {

        // 1. Créer un client
        ClientDAO clientDAO = new ClientDAO();

        Client client = new Client();
        client.setNom("Sene");
        client.setPrenom("Sokhna");
        client.setTelephone("703047016");
        client.setEmail("sokhna@gmail.com");

        clientDAO.ajouterClient(client);
        System.out.println("Client ajouté");

        // 2. Créer un compte
        Compte compte = new Compte(
                0,
                "SN10001",
                75000,
                "COURANT",
                1
        );

        CompteDAO compteDAO = new CompteDAO();
        compteDAO.ajouterCompte(compte);

        // 3. Tester connexion
        Connection c = DatabaseConnection.getConnection();

        if (c != null)
            System.out.println("Connexion OK");
        else
            System.out.println("Connexion échouée");

        // 4. TA PARTIE (Operation)
        OperationDAO operationDAO = new OperationDAO();

        operationDAO.depot(1, 5000);
        operationDAO.retrait(1, 1000);
        operationDAO.virement(1, 2, 2000);
        operationDAO.historique(1);
    }
}