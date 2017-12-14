/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpeg;

import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class PixelArray extends JFrame{

	private static final long serialVersionUID = 1L;

	private String windowTitle=null; 
	private JScrollPane scrollTable=null; 
	private JTable table=null; 
	private JPanel panelTable=null;
	double[][] pixelArray=null;
        float[][] pixelArray1=null;
        int[][] pixelArray2=null;
	private String[][] pixelArrayStr=null; 
	private String[] titles=null;
	private int width; 
	private int height;
	private Object[][] pixelObject; 

	public PixelArray(){
		super();
	}

	
	public PixelArray(String windowTitle,String[][] pixelArrayStr,int width,int height){
		super();
		this.windowTitle=windowTitle;
		this.width=width;
		this.height=height;
		this.pixelArrayStr=pixelArrayStr;
		init();
		setVisible(true); 
	}

	
	public PixelArray(String windowTitle,double[][] pixelArray,int width,int height){
		super();
		this.windowTitle=windowTitle;
		this.width=width;
		this.height=height;
		this.pixelArray=pixelArray;
                if(pixelArray!=null) 
			pixelObject=convertArray2Object(pixelArray,null,width,height);
		init();
		setVisible(true);
	}
        
        public PixelArray(String windowTitle,float[][] pixelArray,int width,int height){
		super();
		this.windowTitle=windowTitle;
		this.width=width;
		this.height=height;
		this.pixelArray1=pixelArray;
                if(pixelArray1!=null) 
                    pixelObject=convertArray2Object(pixelArray1,null,width,height);
		init();
		setVisible(true);
	}
        
        
         public PixelArray(String windowTitle,int[][] pixelArray,int width,int height){
		super();
		this.windowTitle=windowTitle;
		this.width=width;
		this.height=height;
		this.pixelArray2=pixelArray;
                if(pixelArray2!=null) 
                    pixelObject=convertArray2Object(pixelArray2,null,width,height);
		init();
		setVisible(true);
	}
	
	private void init() {
		
//		if(pixelArrayStr!=null)
//			pixelObject=convertArray2Object(null,pixelArrayStr,width,height);
		titles=new String[width]; 
		for(int i=0;i<width;i++)
			titles[i]=""+i;

		table=new JTable(pixelObject,titles); 
		panelTable=new JPanel(); 
		panelTable.add(table); 
		scrollTable=new JScrollPane(panelTable); 
		scrollTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		scrollTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollTable); 
		this.setExtendedState(this.getExtendedState() | Frame.MAXIMIZED_BOTH); 
		setTitle(windowTitle); 
		setSize(new Dimension(300,300));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);	
	}

	private Object[][] convertArray2Object(double[][] array,String[][] arrayStr,int width,int height) {
		Object[][] object=new Object[width+100][height+100];
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				if(array!=null)
					object[i][j]=new Double(array[i][j]);
				else if(arrayStr!=null)
					object[i][j]=new String(arrayStr[i][j]);
			}
		}
		return object;
	}
        
        private Object[][] convertArray2Object(float[][] array,String[][] arrayStr,int width,int height) {
		Object[][] object=new Object[width+100][height+100]; 
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				if(array!=null)
					object[i][j]=new Float(array[i][j]);
				else if(arrayStr!=null)
					object[i][j]=new String(arrayStr[i][j]);
			}
		}
		return object;
	}
        
         private Object[][] convertArray2Object(int[][] array,String[][] arrayStr,int width,int height) {
		Object[][] object=new Object[width+100][height+100]; 
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				if(array!=null)
					object[i][j]=new Integer(array[i][j]);
				else if(arrayStr!=null)
					object[i][j]=new String(arrayStr[i][j]);
			}
		}
		return object;
	}
}

