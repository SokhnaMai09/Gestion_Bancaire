import dao.ClientDAO;
import model.Client;

public class Main {

    public static void main(String[] args) {

        ClientDAO dao = new ClientDAO();

        // ===== 1. CREATION CLIENT =====
        Client client = new Client(
                0,
                "Sene",
                "Sokhna Maimouna",
                "703047016",
                "sokhna@gmail.com"
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