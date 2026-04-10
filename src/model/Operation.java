package model;

public class Operation {

    private int id;
    private String type;
    private double montant;
    private int compteId;

    public Operation(String type, double montant, int compteId) {
        this.type = type;
        this.montant = montant;
        this.compteId = compteId;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getMontant() {
        return montant;
    }

    public int getCompteId() {
        return compteId;
    }
}