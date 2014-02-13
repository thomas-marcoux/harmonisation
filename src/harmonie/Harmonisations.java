package harmonie;

import java.util.ArrayList;

public class Harmonisations {
	public static void main(String[]args){
		//Etape1 : Coder Soprano
		int [] soprano = new int [16];
		soprano[0]=21;
		soprano[1]=21;
		soprano[2]=21;
		soprano[3]=22;
		soprano[4]=23;
		soprano[5]=99;
		soprano[6]=22;
		soprano[7]=99;
		soprano[8]=21;
		soprano[9]=23;
		soprano[10]=22;
		soprano[11]=22;
		soprano[12]=21;
		soprano[13]=99;
		soprano[14]=99;
		soprano[15]=50;
		affichage(soprano);

		
		//Etape2-3: Créer tableau jeu contenant toute les jeux de 
		//notes respectant règles d'harmo local.
		int [][] jeu=remplissageJeu(soprano);
		affichageDouble(jeu);
		
		
		//Etape4-5: Créer tableau de liste suivant contenant chaque accord suivant
		//possible pour chaque accord contenue dans la tableau jeu
		ArrayList<Integer> [][] suivant=remplissageSuivant(jeu);
		affichageSuivant(suivant,jeu);
		
		
		//Etape6: Eliminer dans liste suivant, jeux sans harmo.
		supprimerListeSansSuivant(suivant, jeu);
		affichageSuivant(suivant, jeu);
		
		//Etape7:Produire une liste d'harmo.
		
	}

	public static int[][] remplissageJeu(int [] sop){
		int [][] jeu= new int [16][4];
		for (int i=0;i<sop.length;i++){
			switch (sop[i]){
			case 0: case 7: case 14: case 21:
				jeu[i][0]=1;
				jeu[i][1]=4;
				if(!(i==0||i==15)){
					jeu[i][2]=42;
				}else{
					jeu[i][2]=-1;
				}
				jeu[i][3]=6;

				break;
			case 1: case 8: case 15: case 22:
				jeu[i][0]=2;
				jeu[i][1]=5;
				jeu[i][2]=7;
				jeu[i][3]=-1; //Il n'y a que 3 accords possibles, -1=vide  


				break;
			case 2: case 9: case 16: case 23:
				jeu[i][0]=1;
				jeu[i][1]=3;
				jeu[i][2]=6;
				jeu[i][3]=-1;


				break;
			case 3: case 10: case 17: case 24:
				jeu[i][0]=2;
				jeu[i][1]=4;
				if(!(i==0||i==15)){
					jeu[i][2]=42;
				}else{
					jeu[i][2]=-1;
				}
				jeu[i][3]=7;


				break;
			case 4: case 11: case 18: case 25:
				jeu[i][0]=1;
				jeu[i][1]=3;
				jeu[i][2]=5;
				jeu[i][3]=-1;


				break;	
			case 5: case 12: case 19: case 26:
				jeu[i][0]=2;
				jeu[i][1]=4;
				if(!(i==0||i==15)){
					jeu[i][2]=42;
				}else{
					jeu[i][2]=-1;
				}
				jeu[i][3]=6;


				break;
			case 6: case 13: case 20: case 27:
				jeu[i][0]=3;
				jeu[i][1]=5;
				jeu[i][2]=7;
				jeu[i][3]=-1;


				break;
			case 50:
				for(int k=0;k<4;k++){
					jeu[i][k]=-1;
				}
				break;
			
			case 99:
				jeu[i][0]=jeu[i-1][0];
				jeu[i][1]=jeu[i-1][1];
				jeu[i][2]=jeu[i-1][2];
				jeu[i][3]=jeu[i-1][3];
				break;
		
			}
		}
		return jeu;
	}
	public static ArrayList[][] remplissageSuivant(int[][] jeu){
		ArrayList<Integer> [][] suivant=new ArrayList[jeu.length][jeu[0].length];


		for (int i=0; i<jeu.length; i++){
			for (int j=0;j<jeu[i].length;j++){

				switch(jeu[i][j]){

				case 1: case 4:
					suivant[i][j] = new ArrayList<Integer>();  

					for(int k=1;k<8;k++){
						suivant[i][j].add(k);
					}
					break;
				case 2:
					suivant[i][j] = new ArrayList<Integer>(); 
					suivant[i][j].add(5);
					suivant[i][j].add(7);
					break;
				case 3:
					suivant[i][j] = new ArrayList<Integer>();  

					for(int k=2;k<8;k++){
						suivant[i][j].add(k);
					}
					break;
				case 42:
					suivant[i][j] = new ArrayList<Integer>();  

					suivant[i][j].add(1);
					break;
				case 5:
					suivant[i][j] = new ArrayList<Integer>();  

					suivant[i][j].add(1);
					suivant[i][j].add(3);
					suivant[i][j].add(6);
					suivant[i][j].add(42);
					break;
				case 6:
					suivant[i][j] = new ArrayList<Integer>();  

					suivant[i][j].add(2);
					suivant[i][j].add(3);
					suivant[i][j].add(4);
					suivant[i][j].add(5);
					break;
				case 7:
					suivant[i][j] = new ArrayList<Integer>();  

					suivant[i][j].add(1);
					suivant[i][j].add(3);
					break;
				case -1:
					suivant[i][j] = new ArrayList<Integer>(); 
					break;
				case 0:
					suivant[i][j] = suivant[i-1][j];
					break;
				}
			}
		}
		return suivant;
	}
	
	public static void supprimerListeSansSuivant(ArrayList<Integer>[][] suivant, int [][]jeu){
		for (int i=jeu.length-2;i>0;i--){
			for (int j=jeu[i].length-1;j>=0;j--){
				//si l'accord n'appartient pas a une des liste [i-1][1,2,3,4]
				if(!(appartientListePrec(jeu[i][j],suivant,i-1))){
					// alors on vide la liste des suivant de cet accord
					suivant[i][j].clear();
				}
				//sinon on y touche pas !
			}
		}
	}
	
	public static boolean appartientListePrec(int n, ArrayList<Integer>[][] suivant,int i){
		boolean b=false;
		//parcours des quatre listes
		for(int j=0;j<suivant[i].length-1;j++){
			//si une des liste précedente contient n, on renvoie vrai
			if(suivant[i][j].contains(n)){
				return true;
			}
		}
		//sinon on renvoie faux
		return b;
	}



	/*
	 * 
	 * Méthodes de test
	 * 
	 * 
	 */

	public static void affichageDouble(int [][] tab){
		System.out.println("\n\nTableau des jeux\n");  
		for( int i=0; i<tab.length;i++){
			for( int j=0; j<tab[i].length;j++){
				System.out.print(tab[i][j]+"  ");	
			}
			System.out.print("\n");
		}
	}

	public static void affichage(int [] tab){
		System.out.print("Soprano: ");
		for( int i=0; i<tab.length;i++){
			System.out.print(tab[i]+"  ");	
		}
	}

	public static void affichageSuivant(ArrayList<Integer>[][] suiv, int [][] tab){
		System.out.println("\n Listes des Suivants :\n");  
		for(int i=0; i<suiv.length;i++){
			for( int j=0; j<suiv[i].length;j++){

				System.out.print("Accord   " +tab[i][j] + ": ("+i+","+j+")-->");

				System.out.println(suiv[i][j].toString());

			}
			System.out.print("\n");
		}
	}
}
