package Garage;
public class Main {
    public static void main(String[] args)
    {
        Modele m = new Modele("Swift", 70, "Essence", 3000);
        Voiture v = new Voiture("Suzuki", m);
        Client c = new Client("Evrard", "Nathan", "0472024396");
        Client c1 = new Client("Hotermans", "Colin", "0123456789");
        Employe e = new Employe("Hoyoux", "Adrien", "adricarry", "zerdiaq12", "Administratif");
        Employe colin = new Employe("Hotermans", "Colin", "CocoDéglingo", "TaHayakZbi", "Grosse pute");
        Option op1 = new Option("ABBA", "Essuie-glace", 120);
        Option op2 = new Option("0MM0", "Pare-choc de la mort", 2000);
        Option op3 = new Option("AKKA", "Bitch", 150);
        Contrat contr = new Contrat("Wesh", c, colin);
        v.ajouteOption(op1);
        v.ajouteOption(op2);
        v.ajouteOption(op3);

        colin.setFonction("Salope");
        System.out.println(v.toString());
        System.out.println(c.toString());
        System.out.println(c1.toString());
        System.out.println(colin.toString());
        System.out.println(contr.toString());
    }
}