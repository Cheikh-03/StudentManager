import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;


// VERSION 1
public class Main {
    static int prochainNumero = 1;

    //Pour afficher les etudiants
    public static void  afficherEtudiant(ArrayList<Etudiant> etudiants) {
        System.out.println("Etudiant de la liste :");
        if (etudiants.isEmpty()) {
            System.out.println("La liste des etudiants est vide");
            return;
        }
        else {
            for (Etudiant etudiant : etudiants) {
                System.out.println(etudiant);
            }
        }
    }

    //Pour rechercher les etudiants
    public static Etudiant rechercherEtudiant(ArrayList<Etudiant> etudiants,String prenomRechercher,String nomRechercher) {
        for (Etudiant etudiant : etudiants) {
            if(etudiant.getPrenom().trim().equalsIgnoreCase(prenomRechercher.trim())
            && etudiant.getNom().trim().equalsIgnoreCase(nomRechercher.trim())) {
                return etudiant;
            }
        }
       return  null;
    }

    //Pour rechercher les etudiants par leur identifiant unique
    public static Etudiant rechercherParId(ArrayList<Etudiant> etudiants, String idRechercher) {

        for (Etudiant etudiant : etudiants) {

            if (etudiant.getId().equalsIgnoreCase(idRechercher.trim())) {
                return etudiant;
            }
        }

        return null;
    }

    //Pour supprimer des etudiants
//    public static void supprimerEtudiant1(ArrayList<Etudiant> etudiants,String prenomSupprimer, String nomSupprimer) {
//        boolean asupprimer = etudiants.removeIf(e -> e.getPrenom().equalsIgnoreCase(prenomSupprimer) &&
//                e.getNom().equalsIgnoreCase(nomSupprimer));
//        if(asupprimer) {
//            System.out.println("Etudiant supprimer avec succes");
//        }
//        else {
//            System.out.println("Etudiant introuvable");
//        }
//    }

    //Pour modifier les ages
    public static void modifierAge(ArrayList<Etudiant> etudiants, String prenomModifier,String nomModifier  , int nouvelAge) {
        if(nouvelAge <= 0) {
            System.out.println("Age invalide");
            return;
        }
        else {
            Etudiant resultatModif = rechercherEtudiant(etudiants, prenomModifier, nomModifier);
            if (resultatModif != null) {
                resultatModif.setAge(nouvelAge);
                System.out.println("Etudiant modifier avec succes !");
            } else {
                System.out.println("Etudiant introuvable");
            }
        }
    }

    //pour modifier les ages par ID
    public static void modifierAgeParId(ArrayList<Etudiant> etudiants, String id, int nouvelAge) {

        if (nouvelAge <= 0) {
            System.out.println("Age invalide !");
            return;
        }

        Etudiant resultat = rechercherParId(etudiants, id);

        if (resultat != null) {
            resultat.setAge(nouvelAge);
            System.out.println("Etudiant modifie avec succes !");
        } else {
            System.out.println("Etudiant introuvable !");
        }
    }

    //Supprimer un Etudiant
    public static void supprimerEtudiant(ArrayList<Etudiant> etudiants , String prenomSupprimer, String nomSupprimer ) {
        Etudiant resultatSupprimer = rechercherEtudiant(etudiants, prenomSupprimer,  nomSupprimer);
        if (resultatSupprimer != null) {
            etudiants.remove(resultatSupprimer);
            System.out.println("Etudiant supprimé avec succès !");
        }
        else {
            System.out.println("Etudiant introuvable");
        }
    }

    // Sauvegarder
    public static void enregisterEtudiant(ArrayList<Etudiant> etudiants) {
        try(FileWriter writer = new FileWriter("etudiants.txt",false)) {

            //Au lieu d'utiliser writer.close() manuellement,
            // Java propose try-with-resources, qui ferme automatiquement le fichier

            for(Etudiant etudiant : etudiants) {
//              writer.write(etudiants+ "\n");
                String ligne =etudiant.getId()+";" +etudiant.getNom()+";" + etudiant.getPrenom()+";" + etudiant.getAge();
                writer.write(ligne+ "\n");
            }
//            writer.close();
            System.out.println("Sauvegarde réussi avec succes!");

        } catch (IOException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    //Charger Etudiants
    public static void chargerEtudiants(ArrayList<Etudiant> etudiants) {

        etudiants.clear();

        prochainNumero = 1;

        try (BufferedReader reader = new BufferedReader(new FileReader("etudiants.txt"))) {

            String ligne;

            while ((ligne = reader.readLine()) != null) {

                String[] informations = ligne.split(";");

                // Vérifier qu'il y a exactement 4 informations
                if (informations.length != 4) {
                    System.out.println("Ligne invalide !");
                    continue;
                }

                // Convertir l'age en entier et ID
                int age;
                try {
                    age = Integer.parseInt(informations[3]);

                } catch (NumberFormatException e) {
                    System.out.println("Age invalide !");
                    continue;
                }

                // Vérifier que l'age est positif
                if (age <= 0) {
                    System.out.println("Age invalide !");
                    continue;
                }

                String id =informations[0];
                // Créer l'objet Etudiant et l'ajouter à la liste
                Etudiant etudiant = new Etudiant(id,informations[1].trim(), informations[2].trim(), age);

                etudiants.add(etudiant);

                String prefixe = "ETU" + LocalDate.now().getYear();

                if (id.startsWith(prefixe)) {
                    //startsWith(prefixe) vérifie si l'identifiant commence par le préfixe de l'année actuelle.
                    int numero = Integer.parseInt(id.substring(prefixe.length()));
                    //substring(prefixe.length()) récupère tout ce qui vient après ce préfixe.

                    if (prochainNumero <= numero) {
                        prochainNumero = numero + 1;
                    }
                }
            }

            System.out.println("Chargement terminé !");

        } catch (IOException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    //Generation de numero de carte
    public static String genererNumeroCarte() {

        int annee = LocalDate.now().getYear();

        String numero = String.format("%04d", prochainNumero);

        prochainNumero++;

        return "ETU" + annee + numero;
    }




//Commencement du programme avec la methode main()

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Etudiant> etudiants = new ArrayList<>();

        chargerEtudiants(etudiants);//charger les etudiants



        //  NOTRE MENU
        int choixMenu;
        do {
            System.out.println("======= GESTION DES ETUDIANTS =======");
            System.out.println("1. Ajouter un etudiant");
            System.out.println("2. Afficher les etudiants");
            System.out.println("3. Rechercher un etudiant");
            System.out.println("4. Rechercher par identifiant");
            System.out.println("5. Modifier l'age");
            System.out.println("6. Supprimer un etudiant");
            System.out.println("7. Quitter");
            System.out.println("========= END ========");
            System.out.println("");
            System.out.print("Que voulez vous faire : ");

            if (sc.hasNextInt()) { // permet de vérifier si la saisie est un entier
                choixMenu = sc.nextInt();
                sc.nextLine(); // permet de consomme la saisie incorrecte.
            }
            else {
                System.out.println("Veuillez saisir un nombre !");
                sc.nextLine();
                choixMenu = 0;
            }

            switch (choixMenu) {
                case 1:
                    System.out.println("Ajouter un etudiant");
                    System.out.print("Entrer votre nom : ");
                    String nom = sc.nextLine();
                    System.out.print("Entrer votre prenom : ");
                    String prenom = sc.nextLine();

                    if (nom.trim().isEmpty() || prenom.trim().isEmpty()) {
                        System.out.println("Veuillez remplir tous les champs !");                    }
                    else {
                        System.out.print("Entrer votre age : ");
                        if(sc.hasNextInt()) {
                            int age = sc.nextInt();
                            sc.nextLine();

                            if (age > 0) {
                                // Ajout dans la liste
                                String id = genererNumeroCarte();

                                etudiants.add(new Etudiant(id, nom, prenom, age));

                                enregisterEtudiant(etudiants);

                                System.out.println("Etudiant ajouter avec succes !");
                            }
                            else  {
                                System.out.println("Age invalide !");
                            }
                        }
                        else{
                            System.out.println("Veuillez saisir un nombre !");
                            sc.nextLine();
                        }

                    }
                    break;

                case 2:
                    afficherEtudiant(etudiants);
                    break;

                case 3:
                    System.out.print("Entrer le prenom de l'étudiant dont vous cherchez : ");
                    String prenomRechercher = sc.nextLine();
                    System.out.println("Entrer le nom de l'étudiant dont vous cherchez : ");
                    String nomRechercher = sc.nextLine();

                    if(prenomRechercher.trim().isEmpty()|| nomRechercher.trim().isEmpty()) {
                        System.out.println("Le nom de l'etudiant est obligatoire !");
                    }
                    else{
                        Etudiant resultatRecherche = rechercherEtudiant(etudiants, prenomRechercher, nomRechercher);
                        if (resultatRecherche != null) {
                            System.out.println(resultatRecherche);
                        }
                        else {
                            System.out.println("Etudiant introuvable");
                        }
                    }
                    break;

                case 4:
                    System.out.print("Entrer le numero de carte : ");
                    String idRechercher = sc.nextLine();
                    String prefixeCatre = "ETU202";

                    if (idRechercher.trim().isEmpty()) {
                        System.out.println("le numero de carte est obligatoire !");
                    }
                    else{
                            Etudiant resultat = rechercherParId(etudiants, idRechercher);

                            if (resultat != null) {
                                System.out.println(resultat);
                            } else {
                                System.out.println("Etudiant introuvable !");
                            }
                    }
                    break;

                case 5:
                    System.out.print("Entrer le prenom de l'étudiant dont vous voulez modifier son age : ");
                    String prenomModifier = sc.nextLine();
                    System.out.println("Entre le nom de l'étudiant dont vous voulez modifier son age : ");
                    String nomModifier = sc.nextLine();

                    if(prenomModifier.trim().isEmpty() || nomModifier.trim().isEmpty()){
                        System.out.println("Le nom de l'etudiant est obligatoire !");
                    }
                    else{
                        Etudiant validite =  rechercherEtudiant(etudiants, prenomModifier,  nomModifier);

                        if (validite != null) {
                            System.out.print("Entrer le nouvel age : ");
                            if (sc.hasNextInt()) {
                                int nouvelAge = sc.nextInt();
                                sc.nextLine();

                                if (nouvelAge > 0) {
                                    modifierAge(etudiants, prenomModifier, nomModifier, nouvelAge);
                                    enregisterEtudiant(etudiants);
                                }
                                else {
                                    System.out.println("Age invalide !");
                                }
                            }
                            else{
                                System.out.println("Veuillez saisir un nombre !");
                                sc.nextLine();
                            }

                        }
                        else
                        {
                            System.out.println("Le prenom est introuvable");
                        }
                    }

                    break;

                case 6:
                    System.out.print("Entre le prenom de l'étudiant que vous voulez supprimer : ");
                    String prenomSupprimer = sc.nextLine();
                    System.out.println("Entre le nom de l'étudiant que vous voulez supprimer : ");
                    String nomSupprimer = sc.nextLine();

                    if(prenomSupprimer.trim().isEmpty() || nomSupprimer.trim().isEmpty()) {
                        System.out.println("Le nom de l'etudiant est obligatoire !");
                    }
                    else{
                        supprimerEtudiant(etudiants, prenomSupprimer, nomSupprimer);

                        enregisterEtudiant(etudiants);
                    }
                    break;

                case 7:
                    System.out.println("Au revoir");
                    break;
                default:
                    System.out.println("choix invalide");
                    break;
            }

        } while (choixMenu!= 7);

        enregisterEtudiant(etudiants);

        sc.close();
    }
}