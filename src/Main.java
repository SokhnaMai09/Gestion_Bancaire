import java.sql.Connection;

import dao.ClientDAO;
import dao.CompteDAO;
import database.DatabaseConnection;
import model.Client;
import model.Compte;

public class Main {

    public static void main(String[] args) {

        ClientDAO clientDAO = new ClientDAO();

        // 1. Créer un client
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
                1 // id client existant
        );

        CompteDAO dao = new CompteDAO();
        dao.ajouterCompte(compte);

        // 3. Tester connexion
        Connection c = DatabaseConnection.getConnection();

        if (c != null)
            System.out.println("Connexion OK");
        else
            System.out.println("Connexion échouée");

        // 4. Opérations sur le compte
        dao.deposer("SN10001", 5000);
        dao.retirer("SN10001", 1000);
        dao.afficherComptesClient(1);
    }
}