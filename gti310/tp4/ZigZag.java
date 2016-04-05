package gti310.tp4;

import javax.swing.JOptionPane;

public class ZigZag {
	
	private final int N=8;
	private int dir = 1;
	private int r = 0;
	private int c = 0;
	int tabAC[][][];
	int tabDC[][] = new int [3][];
	int temp[][][] = new int [3][8][8];
	int x, cols, rows;
	
	public ZigZag(double[][][] imgQTF){
		
		rows = imgQTF[0][0].length;
		cols = imgQTF[0].length;
		tabAC = new int [3][rows/8][(8*8)-1]; // (8*8)-1 car on met le DC dans un tableau apart
		tabDC = new int [3][rows/8];
		
		diviserMatrice(imgQTF);
	}
	
	//source : http://ideone.com/xKRTwU
	public  void  diviserMatrice(double[][][] imgQTF){
		
		int  rangee=0;

		for(int x=0; x<3;x++){//change de matrice [Y - Cr - Cb ]	
			rangee=0;
			//l'image au complet séparer en blocs 8x8
			for(int m=0;m<rows;m+=N)
			{
				for(int n=0;n<cols;n+=N)
				{
					//pour chaque pixel dans le bloc[m][n]
					for(int u=m;u<m+N;u++)
					{ 
						for(int v=n;v<n+N;v++)
						{													
							//prend les données de la quantification du bloc 8x8 et le stock dans un tab tempo
							temp[x][u%8][v%8] = (int)imgQTF[x][u][v]; 
						}
					}			
			
					//faire le parcour du bloc 8x8 en zig zag
					if(rangee<32){	
						appliquerZigZag(temp,rangee,x);
						rangee++; //prochain bloc
					}
				}
			}
		}
		/*
		System.out.println("ZIG ZAG");
		for(int g=0;g<64;g++)
			System.out.println(zigzag[0][1][g]);
		*/
	}
	
	//fonction qui fait le parcour en zig zag
	public void appliquerZigZag(int[][][] tempo, int rangee, int niv){
		
		int dc=0;
	//affiche le tableau avant le zig zag
	/*	for (int i=0; i<8; i++){
			for (int j=0; j<8; j++) {
				System.out.format("%3d",temp[niv][i][j]);
				System.out.format(" ");
			}
			System.out.println();
		}
		JOptionPane.showMessageDialog(null, "niveau "+niv+" block "+row);
		*/
		r=0;c=0;dir=1; int col=0;
		while (r < 8 && c < 8){
			
			//mettre la 1ere valeur dans la matrice des DC
			if( col == 0){
				dc = temp[niv][r][c];
				tabDC[niv][rangee] = dc;
				System.out.println("DC : "+dc);
			}
			else
				tabAC[niv][rangee][col-1] = temp[niv][r][c];
			
			col++;
			
			if (dir == 1) {
				if (c == 8 - 1) {
					r++;
					dir = -1;
				} else if (r == 0) {
					c++;
					dir = -1;
				} else {
					r--;
					c++;
				}
			} else {
				if (r == 8 - 1) {
					c++;
					dir = 1;
				} else if (c == 0) {
					r++;
					dir = 1;
				} else {
					c--;
					r++;
				}
			}
		}
	}
	
	//retourne la table de DC
	public int [][] getTabDC(){
		return tabDC;
	}
	
	//retourne la table de AC
	public int [][][] getTabAC(){
		return tabAC;
	}
	
	
	
}
