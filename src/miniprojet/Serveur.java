package miniprojet;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Serveur {

	public static List<Membre> listeMbr = new ArrayList<>();

	public static List<Enchere> listeEn = new ArrayList<>();
	public static List<Offre> listeOff = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		ServerSocket sc = new ServerSocket(4000);
		while (true) {
			Socket s = sc.accept();
			Traitement t = new Traitement(s);
			t.start();
			System.out.println("Mini_Projet : DHIFALLAH Ali & GUENOUNOU Yassine  ");
			System.out.println("Avec ENCHERES  ==> Un membre peut consulter la liste de toutes les enchères en cours" );
			System.out.println("Avec JOIN idEnchere  ==> Un membre peut participer à une enchère sur un produit  	" );
			System.out.println("Avec OFFRE idProd##Prix ==> Un membre peut proposer une offre de prix pour un produit en enchère	" );
			System.out.println("Avec LIST idEnchere ==>  Un membre peut consulter les offres proposées sur une enchère donnée.   	" );
			System.out.println("Avec LOGIN nom  ==> Un membre peut s'authentifier  	" );
			System.out.println("Avec CREATE idEnchere#TitreProduit#Prix#HeureFin  ==> Un membre peut créer une enchère  	" );

			
		}

	}

}
