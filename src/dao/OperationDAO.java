package dao;

import database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OperationDAO {

    Connection conn;

    public OperationDAO() {
        conn = DatabaseConnection.getConnection();
    }

    // DEPOT
    public void depot(int compteId, double montant) {
        try {

            String updateSolde = "UPDATE compte SET solde = solde + ? WHERE id = ?";
            PreparedStatement ps1 = conn.prepareStatement(updateSolde);
            ps1.setDouble(1, montant);
            ps1.setInt(2, compteId);
            ps1.executeUpdate();

            String insertOperation = "INSERT INTO operation(type_operation, montant, id_compte) VALUES('DEPOT', ?, ?)";
            PreparedStatement ps2 = conn.prepareStatement(insertOperation);
            ps2.setDouble(1, montant);
            ps2.setInt(2, compteId);
            ps2.executeUpdate();

            System.out.println("Depot effectué avec succès");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // RETRAIT
    public void retrait(int compteId, double montant) {
        try {

            String updateSolde = "UPDATE compte SET solde = solde - ? WHERE id = ?";
            PreparedStatement ps1 = conn.prepareStatement(updateSolde);
            ps1.setDouble(1, montant);
            ps1.setInt(2, compteId);
            ps1.executeUpdate();

            String insertOperation = "INSERT INTO operation(type_operation, montant, id_compte) VALUES('RETRAIT', ?, ?)";
            PreparedStatement ps2 = conn.prepareStatement(insertOperation);
            ps2.setDouble(1, montant);
            ps2.setInt(2, compteId);
            ps2.executeUpdate();

            System.out.println("Retrait effectué avec succès");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // VIREMENT
    public void virement(int compteSource, int compteDestination, double montant) {
        try {

            String retrait = "UPDATE compte SET solde = solde - ? WHERE id = ?";
            PreparedStatement ps1 = conn.prepareStatement(retrait);
            ps1.setDouble(1, montant);
            ps1.setInt(2, compteSource);
            ps1.executeUpdate();

            String depot = "UPDATE compte SET solde = solde + ? WHERE id = ?";
            PreparedStatement ps2 = conn.prepareStatement(depot);
            ps2.setDouble(1, montant);
            ps2.setInt(2, compteDestination);
            ps2.executeUpdate();

            String insertOperation = "INSERT INTO operation(type_operation, montant, id_compte) VALUES('VIREMENT', ?, ?)";
            PreparedStatement ps3 = conn.prepareStatement(insertOperation);
            ps3.setDouble(1, montant);
            ps3.setInt(2, compteSource);
            ps3.executeUpdate();

            System.out.println("Virement effectué avec succès");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // HISTORIQUE
    public void historique(int compteId) {
        try {

            String sql = "SELECT * FROM operation WHERE id_compte = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, compteId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                        "Type: " + rs.getString("type_operation") +
                        " | Montant: " + rs.getDouble("montant") +
                        " | Date: " + rs.getTimestamp("date_operation")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}