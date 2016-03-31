package gti310.tp4;

/**
 * Cette classe permet de convertir RVB a YUV
 *
 * @author Benny Chen
 */
public class RVBaYUV {

	private double[][] convertTab = { 
			{ 0.299, 0.587, 0.114, }, /* Y */
			{ -0.168736, -0.331264, 0.5} , /* U */
			{ 0.5, -0.418688, -0.081312 } /* V */ }; 
	
	private double[][][] tabYUV;
	
	
	//fonction pour convertir RVB a YUV
	public double[][][] conversion(int tab[][][]) {	
			
		System.out.println("Number of columns (i) = " + tab[0].length);
		int r, b, g;
		double y,u,v;
		//initialiser la table
		tabYUV = new double[3][tab[0].length][tab[0].length];
		
		for(int i = 0; i < tab[0].length; i++) { //
			for(int j = 0; j <tab[0].length ; j++) {
				r = tab[Main.R][i][j]; 
				g = tab[Main.V][i][j];
				b = tab[Main.B][i][j];
			
				y = r*convertTab[0][0] + convertTab[0][1]*g + convertTab[0][2]*b;
				u = r*convertTab[1][0] + convertTab[1][1]*g + convertTab[1][2]*b + 128;
				v = r*convertTab[2][0] + convertTab[2][1]*g + convertTab[2][2]*b + 128;
				
				System.out.println(" Y : " +y + " U : " +u +" V : " +v);
	
				tabYUV[0][i][j] = y;
				tabYUV[1][i][j] = u;
				tabYUV[2][i][j] = v;
			}	
		}
		return tabYUV;
	}
}
	
