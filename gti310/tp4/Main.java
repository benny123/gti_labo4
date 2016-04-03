package gti310.tp4;

/**
 * The Main class is where the different functions are called to either encode
 * a PPM file to the Squeeze-Light format or to decode a Squeeze-Ligth image
 * into PPM format. It is the implementation of the simplified JPEG block 
 * diagrams.
 * 
 * @author Fran�ois Caron
 */
public class Main {

	/*
	 * The entire application assumes that the blocks are 8x8 squares.
	 */
	public static final int BLOCK_SIZE = 8;
	
	/*
	 * The number of dimensions in the color spaces.
	 */
	public static final int COLOR_SPACE_SIZE = 3;
	
	/*
	 * The RGB color space.
	 */
	public static final int R = 0;
	public static final int G = 1;
	public static final int B = 2;
	
	/*
	 * The YUV color space.
	 */
	public static final int Y = 0;
	public static final int U = 1;
	public static final int V = 2;
	
	/**
	 * The application's entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Squeeze Light Media Codec !");
		
		PPMReaderWriter ppmrw = new PPMReaderWriter();
		SZLReaderWriter szlrw = new SZLReaderWriter();
		RVBaYUV rvbAyuv = new RVBaYUV();
		DCT2D dct = new DCT2D();
		Quantification qtc = new Quantification();
		String extension = args[0].substring(args[0].lastIndexOf(".") + 1, args[0].length());
		
		int tab[][][], factQ;
		String filename = args[0];
		
		factQ = Integer.parseInt(args[1]);
		
		//v�rifie si c'est une image PPM ou SZL
		if(extension.equals("ppm")){
			
			System.out.println("PPM");
			//retourne le tableau RGB si le fichier est correcte
			tab=ppmrw.readPPMFile(filename);
			//convertir le tableau RVB en YCbCr
			rvbAyuv.conversion(tab);
			//DCT
			double [][][]dctTab = dct.appliqueDCT(rvbAyuv.conversion(tab));
			System.out.println("DCT TERMINER");
			
			double [][][]tabQtc = qtc.quantifier(dctTab,factQ);
			System.out.println(dctTab[0][16][16]);	
			System.out.println(tabQtc[0][32][32]);	
			
			/*	
			int compteur=0;
			for(int i=0,j=0;i<256;i+=8,j+=8){
				//System.out.println(dctTab[0][i][j]);	
				//compteur++;
			}
			*/
	
		}
		else
			szlrw.readSZLFile(filename);	
	}
}
