package Garage;
public class Main {
    public static void main(String[] args)
    {
        Modele m = new Modele("Swift", 70, "Essence", 3000);
        Modele m2 = new Modele("Corsa", 75, "Diesel", 4500);
        Option op1 = new Option("ABBA", "Essuie-glace", 120);
        Option op2 = new Option("0MM0", "Pare-choc solide", 2000);
        Option op3 = new Option("AKKA", "Vitres teintées", 150);

        Voiture v = new Voiture("Suzuki", m);
        Client c = new Client("Evrard", "Nathan", "0472024396");
        Client c1 = new Client("Hotermans", "Colin", "0123456789");
        Employe e = new Employe("Hoyoux", "Adrien", "adricarry", "zerdiaq12", "Administratif");
        Employe colin = new Employe("Hotermans", "Colin", "CocoDéglingo", "TaHayakZbi", "Vendeur");
        Contrat contr = new Contrat("Wesh", c, colin);

        Garage.getInstance().ajouteModele(m);
        Garage.getInstance().ajouteModele(m2);
        Garage.getInstance().ajouteOption(op1);
        Garage.getInstance().ajouteOption(op2);
        Garage.getInstance().ajouteOption(op3);
        Garage.getInstance().ajouteClient(c);
        Garage.getInstance().ajouteClient(c1);
        Garage.getInstance().ajouteEmploye(e);
        Garage.getInstance().ajouteEmploye(colin);
        Garage.getInstance().ajouteContrat(contr);

        Garage.getInstance().Save();

        v.ajouteOption(op1);
        v.ajouteOption(op2);
        v.ajouteOption(op3);

        colin.setFonction("Administratif");

        System.out.println(v.toString());
        System.out.println(c.toString());
        System.out.println(c1.toString());
        System.out.println(colin.toString());
        System.out.println(contr.toString());
    }
}