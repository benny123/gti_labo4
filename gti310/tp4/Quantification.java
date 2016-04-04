package gti310.tp4;

public class Quantification {
	
	private static final int N = 8;
	private int tab[][] = new int[N][N];
	private double tabQfc[][][];
	
	//table de quantification pour Y
	private static final int[][] qtfQ = {
		{ 16, 11, 10, 16, 24, 40, 51, 61}, 
		{ 12, 12, 14, 19, 26, 58, 60, 55},
		{ 14, 13, 16, 24, 40, 57, 69, 56},
		{ 14, 17, 22, 29, 51, 87, 80, 62},
		{ 18, 22, 37, 56, 68, 109, 103, 77},
		{ 24, 35, 55, 64, 81, 104, 113, 92},
		{ 49, 64, 78, 87, 103, 121, 120, 101},
		{ 72, 92, 95, 98, 112, 100, 103, 99}
	};
	
	//table de quantification pour Cr et Cb
	private static final int[][] qtCrCb = {
		{17, 18, 24, 47, 99, 99 ,99, 99},
		{18, 21 ,26, 66, 99, 99, 99, 99},
		{24, 26, 56, 99 ,99, 99, 99, 99},
		{47, 66, 99, 99, 99, 99, 99, 99},
		{99, 99, 99, 99, 99, 99, 99, 99},
		{99, 99, 99, 99, 99, 99, 99, 99},
		{99, 99, 99, 99, 99, 99, 99, 99},
		{99, 99, 99, 99, 99, 99, 99, 99}
	};

	//méthode de quantification
	public double [][][] quantifier(double[][][] imageDCT, int factQ){
	
		double a=0.0;
		int x = 0;
		//calculer la valeur de a
		if( factQ < 100) //si le facteur de qualité est 100, on ne quantifie pas les coefficients
			if(1<=factQ && factQ<=50)
				a = 50/(double)factQ;
			else{
				a = (200-2*factQ)/(double)100;
				System.out.println((200-2*factQ));
			}
				
		int rows = imageDCT[0][0].length;
		int cols = imageDCT[0].length;
		tabQfc = new double[3][rows][cols];
		
		System.out.println("Valeur a  "+a);
		
		//pour Y-Cr-Cb
		for(int a1=0;a1<3;a1++){
			//si ai == 0, utilise la table Y
			if(a1==0)
				tab=qtfQ;
			else //sinon , utilise la table CrCb
				tab=qtCrCb;
			
			//l'image au complet rows*cols séparer en blocs 8x8
			for(int m=0;m<rows;m+=N){
				for(int n=0;n<cols;n+=N){	
					//pour chaque pixel dans le bloc[m][n]
					for(int u=m;u<m+N;u++){ 
						for(int v=n;v<n+N;v++){													
							if(factQ == 100)
								tabQfc[a1][u][v]=imageDCT[a1][u][v];
							else 
								tabQfc[a1][u][v]=Math.round(imageDCT[a1][u][v]/(a*tab[u%8][v%8]));					
							
						}
					}
				}
			}
		}
		return tabQfc;
	}	
	
}
