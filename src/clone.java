//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class clone{
//    public static void main(String[] args) {
//
//    Scanner sc = new Scanner(System.in);
//
//    ArrayList<Etudiant> etudiants = new ArrayList<>();
//
//    etudiants.add(new Etudiant("LO", "Cheikh Tidiane", 23));
//    etudiants.add(new Etudiant("Diop", "Mamadou", 22));
//    etudiants.add(new Etudiant("Fall", "Fatou", 21));
//
//    // MODIFICATION
//    System.out.print("Nom de l'etudiant a modifier : ");
//    String nomEtudiantModifier = sc.nextLine();
//
//    System.out.print("Nouvel age : ");
//    int ageEtudiant = sc.nextInt();
//    sc.nextLine();
//
//    boolean trouve = false;
//
//    for (Etudiant etudiant : etudiants) {
//        if (etudiant.getNom().equalsIgnoreCase(nomEtudiantModifier)) {
//            etudiant.setAge(ageEtudiant);
//            trouve = true;
//            System.out.println("Etudiant modifie !");
//            break;
//        }
//    }
//
//    if (!trouve) {
//        System.out.println("Etudiant introuvable pour modification !");
//    }
//
//    // SUPPRESSION
//    System.out.print("Nom de l'etudiant a supprimer : ");
//    String nomEtudiantSupprimer = sc.nextLine();
//
//    boolean supprime = etudiants.removeIf(etudiant -> etudiant.getNom().equalsIgnoreCase(nomEtudiantSupprimer));
//
//    if (supprime) {
//        System.out.println("Etudiant supprime !");
//    } else {
//        System.out.println("Etudiant introuvable pour suppression !");
//    }
//
//    // AFFICHAGE FINAL
//    System.out.println("\nListe finale des etudiants :");
//
//    for (Etudiant etudiant : etudiants) {
//        System.out.println(etudiant);
//    }
//
//    sc.close();
//    }
//
//}
