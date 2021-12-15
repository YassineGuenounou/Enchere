package miniprojet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Traitement extends Thread {
	private Socket s;
	private Membre m = null;

	public Traitement(Socket s) {
		this.s = s;
	}

	public void run() {

		try

		{
			InputStreamReader in = new InputStreamReader(s.getInputStream());
			BufferedReader in_sc = new BufferedReader(in);

			OutputStreamWriter out = new OutputStreamWriter(s.getOutputStream());
			PrintWriter out_sc = new PrintWriter(new BufferedWriter(out), true);
			while (true) {
				String msg = in_sc.readLine();
				if (msg.startsWith("CREATE"))
				{
					String cd=msg.substring(7);
            		String t[]=cd.split("##");
            		Serveur.listeEn.add(new Enchere(t[0], Double.parseDouble(t[1]),Integer.parseInt(t[2]), Integer.parseInt(t[3])));
            		out_sc.println("Une nouvelle enchere est ajoutee !!"+Enchere.c);
            		
				}
				
        
				else if (msg.startsWith("ENCHERES")) {
					String s = "";
					for (Enchere e : Serveur.listeEn) {
						s += e.getId()+"##"+e.getDesc()+"##"+e.getPrixD()+"##"+e.getHeueF()+"##"+e.getEtat()+"##"+"///";
						
					}
					if (s.length()>0) {
						out_sc.println(s);
					} else {
						out_sc.println("**Aucun Enchere disponible**");
					}
				} else if (msg.startsWith("JOIN ")) {
					String cd = msg.substring(5);
					int res = Integer.parseInt(cd);
					boolean test = true;
					for (Enchere e : Serveur.listeEn) {
						if (e.getDesc().equals(msg.substring(5))) {
							e.lim.add(this.m);
							test = true;
							out_sc.println("Ajout dans l'enchere avec succes!!Profitez bien de nos Offres");
						}
					}
					if (test == false) {
						out_sc.println("**Enchere introuvable**");
					}

				} else if (msg.startsWith("OFFRE ")) {
					String cd = msg.substring(6);
					String[] t = cd.split("##");
					int res = Integer.parseInt(t[0]);
					Enchere en = null;

					boolean test = false;
					for (Enchere e : Serveur.listeEn) {
						if (e.getId() == res) {
							en = e;
							test = true;
							
							
						}
					}
					double doub = Double.parseDouble(t[1]);
					if (test == false) {
						out_sc.println("Enchere introuvable");
					} else {
						if (en.getPrixD() < doub) {
							en.setPrixD(doub);
							Offre o = new Offre(doub, this.m, en);
							Serveur.listeOff.add(o);
							out_sc.println("-Offre Accepte ;)");
						} else {
							out_sc.println("-Offre non accepte :( ");
						}
					}
				} else if (msg.startsWith("LIST ")) {
					String cd = msg.substring(5);
					String s = "";
					int res = Integer.parseInt(cd);
					Enchere en = null;
					boolean test = false;
					for (Enchere e : Serveur.listeEn) {
						if (e.getId() == res) {
							en = e;
							test = true;
							String s1="";
		         			
		         				s1+=e.getId()+"#"+e.getDesc()+"#"+e.getPrixD()+"#"+e.getEtat()+"///";
		         			out_sc.println(s1);
						}
					}
					if (test == false) {
						out_sc.println("**Enchere introuvable**");
					} else {
						for (Offre o : Serveur.listeOff) {
							if (o.getE() == en) {
								s += o.info();
							}
						}

						if (s.length() > 0) {
							out_sc.println(s);
						} else {
							out_sc.println("Aucun offre dispo pour cette enchere");
						}
					}
					

				} else if (msg.startsWith("LOGIN ")) {
					String nom = msg.substring(6);
					this.m = new Membre(nom);
					out_sc.println("NOTIFICATION : 1 Membre ajoute !!");
				} else {
					out_sc.println("ATTENTION : Syntaxe Invalide !!");
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
