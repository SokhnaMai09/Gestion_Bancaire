<<<<<<< HEAD
=======

>>>>>>> f358e15a0592a232b2535a583158cdf4e017f841
import dao.ClientDAO;
import model.Client;

public class Main {

    public static void main(String[] args) {

        ClientDAO dao = new ClientDAO();

        // ===== 1. CREATION CLIENT =====
        Client client = new Client(
                0,
<<<<<<< HEAD
                "Sene",
                "Sokhna Maimouna",
                "703047016",
                "sokhna@gmail.com"
=======
                "Diagne",
                "Yacine",
                "785111836",
                "diagneyacine201@gmail.com"
>>>>>>> f358e15a0592a232b2535a583158cdf4e017f841
        );

        dao.ajouterClient(client);

        // ===== 2. AFFICHAGE =====
        System.out.println("Liste des clients :");

        dao.getAllClients().forEach(c ->
                System.out.println(c.getNom())
        );

        // ===== 3. RECHERCHE =====
        Client c = dao.getClientById(1);

        if (c != null) {

            // ===== 4. MODIFICATION =====
            c.setNom("NouveauNom");
            dao.modifierClient(c);

            System.out.println("Client modifié !");
        }

        // ===== 5. SUPPRESSION =====
        dao.supprimerClient(1);

        System.out.println("Client supprimé !");
    }
}