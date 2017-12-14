
package jpeg;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.image.PixelGrabber;



class JpegInfo {
  String Comment;

  public Image imageobj;

  public int imageHeight;

  public int imageWidth;

  public int BlockWidth[];

  public int BlockHeight[];

  // the following are set as the default
  public int Precision = 8;

  public int NumberOfComponents = 3;

  public Object Components[];

  public int[] CompID = { 1, 2, 3 };

  public int[] HsampFactor = { 1, 1, 1 };

  public int[] VsampFactor = { 1, 1, 1 };

  public int[] QtableNumber = { 0, 1, 1 };

  public int[] DCtableNumber = { 0, 1, 1 };

  public int[] ACtableNumber = { 0, 1, 1 };

  public boolean[] lastColumnIsDummy = { false, false, false };

  public boolean[] lastRowIsDummy = { false, false, false };

  public int Ss = 0;

  public int Se = 63;

  public int Ah = 0;

  public int Al = 0;

  public int compWidth[], compHeight[];

  public int MaxHsampFactor;

  public int MaxVsampFactor;

  public JpegInfo(Image image) {
    Components = new Object[NumberOfComponents];
    compWidth = new int[NumberOfComponents];
    compHeight = new int[NumberOfComponents];
    BlockWidth = new int[NumberOfComponents];
    BlockHeight = new int[NumberOfComponents];
    imageobj = image;
    imageWidth = image.getWidth(null);
    imageHeight = image.getHeight(null);
    Comment = "JPEG Encoder Copyright 1998, James R. Weeks and BioElectroMech.  ";
    getYCCArray();
  }

  public void setComment(String comment) {
    Comment.concat(comment);
  }

  public String getComment() {
    return Comment;
  }

  

  private void getYCCArray() {
    int values[] = new int[imageWidth * imageHeight];
    int r, g, b, y, x;
  
    PixelGrabber grabber = new PixelGrabber(imageobj.getSource(), 0, 0, imageWidth, imageHeight,
        values, 0, imageWidth);
    MaxHsampFactor = 1;
    MaxVsampFactor = 1;
    for (y = 0; y < NumberOfComponents; y++) {
      MaxHsampFactor = Math.max(MaxHsampFactor, HsampFactor[y]);
      MaxVsampFactor = Math.max(MaxVsampFactor, VsampFactor[y]);
    }
    for (y = 0; y < NumberOfComponents; y++) {
      compWidth[y] = (((imageWidth % 8 != 0) ? ((int) Math.ceil(imageWidth / 8.0)) * 8 : imageWidth) / MaxHsampFactor)
          * HsampFactor[y];
      if (compWidth[y] != ((imageWidth / MaxHsampFactor) * HsampFactor[y])) {
        lastColumnIsDummy[y] = true;
      }
      
      BlockWidth[y] = (int) Math.ceil(compWidth[y] / 8.0);
      compHeight[y] = (((imageHeight % 8 != 0) ? ((int) Math.ceil(imageHeight / 8.0)) * 8
          : imageHeight) / MaxVsampFactor)
          * VsampFactor[y];
      if (compHeight[y] != ((imageHeight / MaxVsampFactor) * VsampFactor[y])) {
        lastRowIsDummy[y] = true;
      }
      BlockHeight[y] = (int) Math.ceil(compHeight[y] / 8.0);
    }
    try {
      if (grabber.grabPixels() != true) {
        try {
          throw new AWTException("Grabber returned false: " + grabber.status());
        } catch (Exception e) {
        }
      }
    } catch (InterruptedException e) {
    }
    float Y[][] = new float[compHeight[0]][compWidth[0]];
    float Cr1[][] = new float[compHeight[0]][compWidth[0]];
    float Cb1[][] = new float[compHeight[0]][compWidth[0]];
    // float Cb2[][] = new float[compHeight[1]][compWidth[1]];
    // float Cr2[][] = new float[compHeight[2]][compWidth[2]];
    int index = 0;
    for (y = 0; y < imageHeight; ++y) {
      for (x = 0; x < imageWidth; ++x) {
        r = ((values[index] >> 16) & 0xff);
        g = ((values[index] >> 8) & 0xff);
        b = (values[index] & 0xff);
        
        Y[y][x] = (float) ((0.299 * r + 0.587 * g + 0.114 * b));
        Cb1[y][x] = 128 + (float) ((-0.16874 * r - 0.33126 * g + 0.5 * b));
        Cr1[y][x] = 128 + (float) ((0.5 * r - 0.41869 * g - 0.08131 * b));
        index++;
      }
    }

 

    Components[0] = Y;
    Components[1] = Cb1;
    Components[2] = Cr1;
 }
} 

  