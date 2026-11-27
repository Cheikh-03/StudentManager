public class Etudiant {
    private String id;
    private String nom;
    private String prenom;
    private int age;

    public Etudiant(String id, String nom, String prenom, int age) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }

    public String getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return  "| ID "+id+
                "\n|nom "+nom+
                "\n|prenom "+prenom+
                "\n|,age "+age;
    }
}
