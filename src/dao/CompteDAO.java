package dao;

import database.DatabaseConnection;
import model.Compte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CompteDAO {

    // ===============================
    // AJOUTER COMPTE
    // ===============================
    public void ajouterCompte(Compte compte) {

        try {
            Connection conn = DatabaseConnection.getConnection();

            String sql =
                "INSERT INTO compte(numero_compte, solde, type_compte, id_client) VALUES (?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, compte.getNumeroCompte());
            stmt.setDouble(2, compte.getSolde());
            stmt.setString(3, compte.getTypeCompte());
            stmt.setInt(4, compte.getIdClient());

            stmt.executeUpdate();

            System.out.println("Compte ajouté avec succès !");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===============================
    // RECHERCHER PAR NUMERO
    // ===============================
    public Compte rechercherParNumero(String numero) {

        Compte compte = null;

        try {
            Connection conn = DatabaseConnection.getConnection();

            String sql = "SELECT * FROM compte WHERE numero_compte = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, numero);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                compte = new Compte(
                        rs.getInt("id"),
                        rs.getString("numero_compte"),
                        rs.getDouble("solde"),
                        rs.getString("type_compte"),
                        rs.getInt("id_client")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return compte;
    }

    // ===============================
    // DEPOSER ARGENT
    // ===============================
    public void deposer(String numero, double montant) {

        try {
            Connection conn = DatabaseConnection.getConnection();

            String sql =
                "UPDATE compte SET solde = solde + ? WHERE numero_compte = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, montant);
            ps.setString(2, numero);

            ps.executeUpdate();

            System.out.println("Dépôt effectué");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===============================
    // RETIRER ARGENT
    // ===============================
    public void retirer(String numero, double montant) {

        try {
            Connection conn = DatabaseConnection.getConnection();

            Compte compte = rechercherParNumero(numero);

            if (compte == null) {
                System.out.println("Compte introuvable");
                return;
            }

            if (compte.getSolde() < montant) {
                System.out.println("Solde insuffisant");
                return;
            }

            String sql =
                "UPDATE compte SET solde = solde - ? WHERE numero_compte = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, montant);
            ps.setString(2, numero);

            ps.executeUpdate();

            System.out.println("Retrait effectué");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===============================
    // AFFICHER COMPTES D’UN CLIENT
    // ===============================
    public void afficherComptesClient(int idClient) {

        try {
            Connection conn = DatabaseConnection.getConnection();

            String sql = "SELECT * FROM compte WHERE id_client = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idClient);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                        "Numéro : " + rs.getString("numero_compte") +
                        " | Solde : " + rs.getDouble("solde") +
                        " | Type : " + rs.getString("type_compte")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}