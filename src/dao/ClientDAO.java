package dao;

import database.DatabaseConnection;
import model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    // AJOUTER CLIENT
    public void ajouterClient(Client client) {

        String sql = "INSERT INTO client(nom, prenom, telephone, email) VALUES (?,?,?,?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, client.getNom());
            ps.setString(2, client.getPrenom());
            ps.setString(3, client.getTelephone());
            ps.setString(4, client.getEmail());

            ps.executeUpdate();
            System.out.println("Client ajouté !");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // AFFICHER CLIENTS
    public List<Client> getAllClients() {

        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Client c = new Client(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("telephone"),
                        rs.getString("email")
                );

                clients.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return clients;
    }
    public Client getClientById(int id) {
    Client client = null;

    try {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM client WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            client = new Client();
            client.setId(rs.getInt("id"));
            client.setNom(rs.getString("nom"));
            client.setPrenom(rs.getString("prenom"));
        }

    } catch(Exception e){
        e.printStackTrace();
    }

    return client;
}
public void supprimerClient(int id) {

    try {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE FROM client WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, id);
        ps.executeUpdate();

    } catch(Exception e){
        e.printStackTrace();
    }
}
public void modifierClient(Client client) {

    try {
        Connection conn = DatabaseConnection.getConnection();
        String sql =
            "UPDATE client SET nom=?, prenom=? WHERE id=?";

        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, client.getNom());
        ps.setString(2, client.getPrenom());
        ps.setInt(3, client.getId());

        ps.executeUpdate();

    } catch(Exception e){
        e.printStackTrace();
    }
}
}
