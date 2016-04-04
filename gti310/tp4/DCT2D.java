package gti310.tp4;

public class DCT2D {
	
	private static final int N = 8; // 8x8 block
	private double[][][] imageDCT;
	private double sum, au ,av;

	// source : https://www.cyut.edu.tw/~yltang/program/Dct.java
	//fonction pour faire le traitement DCT
	public double[][][] appliqueDCT(double tab[][][]){
		
		int cols = tab[0].length;
		int rows = tab[0][0].length;
		//initialiser les matrices
		imageDCT= new double[3][rows][cols];

		//pour Y-Cr-Cb
		for(int a=0;a<3;a++){
			
			//pour chaque 8x8 bloc[m,n]
			for(int m=0;m<rows;m+=N){
				for(int n=0;n<cols;n+=N){
					
					//pour chaque pixel dans le bloc[m][n]
					for(int u=m;u<m+N;u++){ 
						//trouver le coefficient
						if(u==m)au = 1/(Math.sqrt(2));
						else au = 1;
					
						for(int v=n;v<n+N;v++){
							//trouver le coefficient
							if(v==n) av = 1/( Math.sqrt(2));
							else av = 1;  
							
							sum=0;
				        	//additionne toutes les pixels du bloc
				            for(int i=m; i<m+N;i++){
				              for(int j=n;j<n+N;j++)				 
				                sum+=( 
				                		Math.cos(((2*i+1)*(u%8)*Math.PI)/16)* 
				                		Math.cos(((2*j+1)*(v%8)*Math.PI)/16)*
				                		tab[a][i][j] 
				                	);		
				            }
				            sum*=((au*av)/4);
				            imageDCT[a][u][v] = sum; // met la sommation dans le pixel du bloc 8x8
				      }
				   }
				}
			}
		}
		
		return imageDCT;
   }
}
