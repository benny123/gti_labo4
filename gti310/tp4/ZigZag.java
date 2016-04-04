package gti310.tp4;

import javax.swing.JOptionPane;

public class ZigZag {
	
	private final int N=8;
	private int dir = 1;
	private int r = 0;
	private int c = 0;
	int zigzag[][][];
	int temp[][][] = new int [3][8][8];
	int x;
	
	//source : http://ideone.com/xKRTwU
	public void diviserMatrice(double[][][] imgQTF){
		
		int  rangee=0;
		int rows = imgQTF[0][0].length;
		int cols = imgQTF[0].length;
		zigzag = new int [3][rows/8][8*8];

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
					//faire le parcour du bloc
					appliquerZigZag(temp,rangee,x);
					rangee++; //prochain bloc
				}
			}
		}
		
		for(int g=0;g<64;g++)
			System.out.println(zigzag[1][0][g]);
	}
	
	//fonction qui fait le parcour en zig zag
	public void appliquerZigZag(int[][][] tempo, int row, int niv){
		
		
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
		r=0;c=0;dir=1; int indice=0;
		while (r < 8 && c < 8){
			
			zigzag[niv][row][indice] = temp[niv][r][c];
			indice++;
			
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
		/*System.out.println(" ZIG ZAG ");
		//affiche le tableau
		for(int g=0;g<64;g++)	
			System.out.println(zigzag[0][0][g]);
		JOptionPane.showMessageDialog(null, "My Goodness, this is so concise");*/
	}
}
