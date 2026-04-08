package model;

public class Compte {

    private String numeroCompte;
    private double solde;
    private String typeCompte;
    private int idClient;

    public Compte(int id, String numeroCompte, double solde,
                  String typeCompte, int idClient) {

        this.numeroCompte = numeroCompte;
        this.solde = solde;
        this.typeCompte = typeCompte;
        this.idClient = idClient;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public double getSolde() {
        return solde;
    }

    public String getTypeCompte() {
        return typeCompte;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public void supprimerCompte(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'supprimerCompte'");
    }
}