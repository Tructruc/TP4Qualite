import java.util.Scanner;

public class JourSuivant {
    /*
     * Jeu d'essais:
     * 13 2 2019 : 14/02/2019
     * 28 2 2019 : 01/03/2019
     * 31 12 2019 : 01/01/2020
     * 0 12 2020 : date invalide
     * 10 -5 2020 : date invalide
     * 10 2 -3: date invalide
     */
    public static void main(String[] args) {
        int[] date1 = {10, 5, 2020};
        int[] date2 = {9, 4, 2019};
        int[] dateout = new int[3];

        barycentre(date1, date2, dateout);
        System.out.println(dateout[0] + "/" + dateout[1] + "/"+ dateout[2]);
    }

    public static void main1(String[] args) {

        /* Déclaration des variables */
        int[] date = new int[3];
        int[] dateSuivante = new int[3];
        int[] dateSurlendemain = new int[3];
        int[] dateJourNSuivant = new int[3];
        int nbJours = 0;
        boolean valide = false;
        Scanner clavier = new Scanner(System.in);

        /* -- Etape 1 -- */
        /* Saisie d'une date */
        saisieDate(date);
        // saisie du nombre de jours suivant la date
        System.out.print("Saisir le nombre de jours : ");
        nbJours = clavier.nextInt();

        /* -- Etape 2 -- */
        /* Vérification de la date saisie */
        valide = dateValide(date);

        /* -- Etape 3 -- */
        if (valide) {
            /* Calcul du jour suivant */
            jourSuivant(date, dateSuivante);
            surLendemain(date, dateSurlendemain);
            nIemeJour(date, dateJourNSuivant, nbJours);
            /* Affichage du jour suivant */
            System.out.println(
                    "Le jour suivant est le : " + dateSuivante[0] + "/" + dateSuivante[1] + "/" + dateSuivante[2]);
            System.out.println("Le surlendemain est le : " + dateSurlendemain[0] + "/" + dateSurlendemain[1] + "/"
                    + dateSurlendemain[2]);
            System.out.println("Le " + nbJours + "ième jour suivant est le : " + dateJourNSuivant[0] + "/"
                    + dateJourNSuivant[1] + "/" + dateJourNSuivant[2]);
        } else {
            System.out.println("La date du "
                    + date[0] + "/" + date[1] + "/" + date[2]
                    + " n'est pas une date valide.");
        }
    }

    /**
     * Fait saisir une date à l'utilisateur
     *
     * @param pfDate OUT : un tableau de trois cases représentant une date. 1ere case : jour, 2nde case : mois, 3eme case : annee
     */
    public static void saisieDate(int[] pfDate) {
        if (pfDate.length != 3) {
            System.out.print("Le tableau représentant la date a une taille inattendue : ");
            System.out.println(pfDate.length + " case(s) au lieu de 3 !");
        }
        Scanner clavier = new Scanner(System.in);

        System.out.print("Saisir le jour : ");
        pfDate[0] = clavier.nextInt();

        System.out.print("Saisir le mois : ");
        pfDate[1] = clavier.nextInt();

        System.out.print("Saisir l'année : ");
        pfDate[2] = clavier.nextInt();
    }

    /**
     * Calcul la validité d'une date
     *
     * @param pfDate IN : date initiale
     * @return true si et seulement si pfDate est valide
     */
    public static boolean dateValide(int[] pfDate) {
        if (pfDate.length != 3) {
            System.out.print("Un tableau représentant une date a une taille inattendue : ");
            System.out.println(pfDate + " case(s) au lieu de 3 !");
        }

        boolean valide = estValide(pfDate[0], pfDate[1], pfDate[2]);
        return valide;
    }

    /**
     * Fonction aidant a saisir un entier.
     * A priori, pas besoin de la modifier
     * 
     * @param message message qui apparait avant que l’utilisateur saisise l’entier
     * @param clavier element scanner, passé en parametre pour ne pas devoir l’ouvrir a chaque utilisation de la variable
     * @return entier saisi par l'utilisateur
     */
    public static int saisieEntier(Scanner clavier, String message) {
        System.out.print(message);
        int nombreSaisi = clavier.nextInt();
        return nombreSaisi;
    }

    /**
     * @param annee IN : une année
     * @return true si l'année est bissextile, faux sinon
     */
    public static boolean estBissextile(int annee) {
        boolean bissextile = false;
        if (annee % 4 != 0) {
            bissextile = false;
        } else if (annee % 100 == 0) {
            if (annee % 400 == 0) {
                bissextile = true;
            } else {
                bissextile = false;
            }

        } else {
            bissextile = true;
        }

        return bissextile;
    }

    /**
     * Détermine le nombre de jours dans un mois d'une année donnée
     * 
     * @param mois IN : mois
     * @param annee IN : année
     * @return le nombre de jours dans le mois
     */

    public static int nbJour(int mois, int annee) {
        int nombreDeJours = 0;
        if (mois == 2) {
            if (estBissextile(annee)) {
                nombreDeJours = 29;
            } else {
                nombreDeJours = 28;
            }
        } else if (mois == 4 || mois == 6 || mois == 9 || mois == 11) {
            nombreDeJours = 30;
        } else {
            nombreDeJours = 31;
        }

        return nombreDeJours;
    }

    /**
     * Détermine si une date est valide
     * 
     * @param jour IN : jour de la date
     * @param mois IN : mois de la date
     * @param annee IN :année de la date
     * @return vrai si la date est valide, faux sinon
     */
    public static boolean estValide(int jour, int mois, int annee) {
        if (annee >= 1582 && jour > 0 && jour <= nbJour(mois, annee) && mois >= 1 && mois <= 12) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Calcul du jour suivant
     *
     * @param pfDateJourCourant IN : date initiale
     * @param pfDateJourSuivant OUT : date du jour suivant
     *
     */
    public static void jourSuivant(int[] pfDateJourCourant, int[] pfDateJourSuivant) {
        if (pfDateJourCourant.length != 3 || pfDateJourSuivant.length != 3) {
            System.out.print("Un tableau représentant une date a une taille inattendue : ");
            System.out.println(pfDateJourCourant.length + " ou " + pfDateJourSuivant.length
                    + " case(s) au lieu de 3 !");
        }
        if (pfDateJourCourant[0] < nbJour(pfDateJourCourant[1], pfDateJourCourant[2])) {
            pfDateJourSuivant[0] = pfDateJourCourant[0] + 1;
            pfDateJourSuivant[1] = pfDateJourCourant[1];
            pfDateJourSuivant[2] = pfDateJourCourant[2];
        } else if (pfDateJourCourant[1] < 12) {
            pfDateJourSuivant[0] = 1;
            pfDateJourSuivant[1] = pfDateJourCourant[1] + 1;
            pfDateJourSuivant[2] = pfDateJourCourant[2];
        } else {
            pfDateJourSuivant[0] = 1;
            pfDateJourSuivant[1] = 1;
            pfDateJourSuivant[2] = pfDateJourCourant[2] + 1;
        }
    }

    /**
     * Calcul du surlendemain
     *
     * @param pfDateJourCourant IN : date initiale
     * @param pfDateSurlendemain OUT : date du surlendemain
     */
    public static void surLendemain(int[] pfDateJourCourant, int[] pfDateSurlendemain) {
        jourSuivant(pfDateJourCourant, pfDateSurlendemain);
        jourSuivant(pfDateSurlendemain, pfDateSurlendemain);
    }

    /**
     * Calcul du niem jour apres la date donné
     *
     * @param pfDateJourCourant IN : date initiale
     * @param pfDateSurlendemain OUT : date du surlendemain
     * @param n IN : nombre de jour apres la date donné
     */
    public static void nIemeJour(int[] pfDateJourCourant, int[] pfDateSurlendemain, int n) {
        if (n > 0) {
            jourSuivant(pfDateJourCourant, pfDateSurlendemain);
            for (int i = 1; i < n; i++) {
                jourSuivant(pfDateSurlendemain, pfDateSurlendemain);
            }
        }else{
            pfDateSurlendemain = pfDateJourCourant;
        }
    }


    /**
     * Calcule la date au centre des 2 dates fournies
     *
     * @param pfdate1 IN : une date
     * @param pfdate2 IN : une autre date
     * @param pfdateout OUT : la date au centre
     */
    public static void barycentre(int[] pfdate1, int[] pfdate2, int[] pfdateout){
        int[] Pdate = pfdate1; //première date
        int[] Ddate = pfdate2; //deuxième date

        if (pfdate1[2] > pfdate2[2]) {
            Pdate = pfdate2;
            Ddate = pfdate1;
        }else if (pfdate2[2] > pfdate1[2]){
            Pdate = pfdate1;
            Ddate = pfdate2;
        }else{
            if (pfdate1[1] > pfdate2[1]) {
                Pdate = pfdate2;
                Ddate = pfdate1;
            }else if (pfdate2[1] > pfdate1[1]){
                Pdate = pfdate1;
                Ddate = pfdate2;
            }else{
                if (pfdate1[0] > pfdate2[0]) {
                    Pdate = pfdate2;
                    Ddate = pfdate1;
                }else if (pfdate2[0] > pfdate1[0]){
                    Pdate = pfdate1;
                    Ddate = pfdate2;
                }
            }
        }
        int[] dateTemp = {Pdate[0], Pdate[1], Pdate[2]};
        int i =0;
        while ((dateTemp[0]!=Ddate[0])  || (dateTemp[1]!=Ddate[1]) || (dateTemp[2]!=Ddate[2])){
            jourSuivant(dateTemp, dateTemp);
            i++;
            System.out.print(i+"\r");
        }
        int dist = i/2;
        pfdateout[0] = Pdate[0];
        pfdateout[1] = Pdate[1];
        pfdateout[2] = Pdate[2];

        for (int j = 0; j < dist; j++) {
            jourSuivant(pfdateout, pfdateout);
        }
        System.out.print("");
    }

}