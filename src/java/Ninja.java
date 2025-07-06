package Affichage;
public class Ninja extends Composant {
    private String prenom;  
    private String clan;
    private String chakraNature;
    private int puissance;

    // Getter and Setter for prenom
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    // Getter and Setter for clan
    public String getClan() {
        return clan;
    }

    public void setClan(String clan) {
        this.clan = clan;
    }

    // Getter and Setter for chakraNature
    public String getChakraNature() {
        return chakraNature;
    }

    public void setChakraNature(String chakraNature) {
        this.chakraNature = chakraNature;
    }

    // Getter and Setter for puissance
    public int getPuissance() {
        return puissance;
    }

    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }
}
