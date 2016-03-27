import edu.princeton.cs.algs4.Picture;

import java.awt.*;
import java.util.Arrays;

/**
 * Created by abc84 on 2016/3/26.
 */
public class SeamCarver {
    private double[][] arry;
    private Picture pic;

    public SeamCarver(Picture picture) {// create a seam carver object based on the given picture
        if (picture == null) throw new NullPointerException();
        this.pic = new Picture(picture);
        buildArray();
    }
    private void buildArray() {
        arry = new double[height()][width()];
        for (int i=0;i<height();i++) {
            for (int j=0;j<width();j++){
                arry[i][j] = energy(j, i);
            }
        }
    }

    public Picture picture() {// current picture
        return pic;
    }

    public int width() {// width of current picture
        return pic.width();
    }

    public int height() {// height of current picture
        return pic.height();
    }

    public double energy(int x, int y) {// energy of pixel at column x and row y
        if (x <0 || x >= width() || y >= height() || y < 0) throw new IndexOutOfBoundsException();
        if (x == 0 || y == 0 || x == width() - 1 || y == height() -1) {
            return 1000;
        }
        int[] rgbx1 = getRGB(x-1, y);
        int[] rgbx2 = getRGB(x+1, y);
        int[] rgby1 = getRGB(x, y-1);
        int[] rgby2 = getRGB(x, y+1);
        int deltaX = caculate(rgbx1,rgbx2);
        int deltaY = caculate(rgby1,rgby2);
        return Math.sqrt(deltaX+deltaY);
    }


    private int caculate(int[] a, int[] b) {
        int result = 0;
        for (int i=0;i<a.length;i++) {
            result += Math.abs(a[i] - b[i])*Math.abs(a[i] - b[i]);
        }
        return result;
    }
    private int[] getRGB(int x, int y) {
        Color color = pic.get(x, y);
        int[] rgb = new int[3];
        int red = color.getRed();
        rgb[0] = red;
        int green = color.getGreen();
        rgb[1] = green;
        int blue = color.getBlue();
        rgb[2] = blue;
        return rgb;
    }

    public int[] findVerticalSeam() {// sequence of indices for vertical seam
        int height = height();
        int[] result = null ;
        int[][] seams = new int[width()][height];
        buildArray();
        double MinWeight = Double.POSITIVE_INFINITY;
        for (int i=1;i<width()-1;i++) {
            seams[i-1] = getSeamByColum(i, arry);
        }
        for (int[] seam : seams) {
            double weight = sumCol(arry,seam);
            if (weight < MinWeight) {
                MinWeight = weight;
                result = seam;
            }
        }
        return result;
    }
    public int[] findHorizontalSeam() {// sequence of indices for horizontal seam
        int width = width();
        int[] result = null ;
        int[][] seams = new int[height()][width];
        buildArray();//必须有这一行
        double MinWeight = Double.POSITIVE_INFINITY;
        for (int i=1;i<height()-1;i++) {
            seams[i-1] = getSeamByRow(i, arry);
        }
        for (int[] seam : seams) {
            double weight = sumRow(arry,seam);
            if (weight < MinWeight) {
                MinWeight = weight;
                result = seam;
            }
        }
        return result;
    }

    private double sumCol(double[][] arry,int[] a) {
        double sum = 0;
        for (int i=0;i<height();i++){
            if (i < a.length)
                sum += arry[i][a[i]];
        }
        return sum;
    }
    private int[] getSeamByColum(int col, double[][] arry) {
        int[] result = new int[height()];
        int x = col;
        result[0] = col;
        for (int i=1;i<height();i++) {
            if (arry[i][x-1]<=arry[i][x]&&arry[i][x-1]<=arry[i][x+1]) {
                x = x - 1;
                result[i] = x;
            }
            else if (arry[i][x]<=arry[i][x-1]&&arry[i][x]<=arry[i][x+1]){
                result[i] = x;
            }
            else if (arry[i][x+1]<=arry[i][x]&&arry[i][x+1]<=arry[i][x-1]){
                x = x + 1;
                result[i] = x;
            }
        }
        //System.out.println("\n arry:"+Arrays.toString(result));
        return result;
    }

    private double[][] buildTransposedArray() {
        double[][] copy = new double[height()][width()];
        for (int i=0;i<height();i++) {
            copy[i] = Arrays.copyOf(arry[i],width());
        }
        double[][] arry = new double[width()][height()];
        for (int i=0;i<width();i++) {
            for (int j=0;j<height();j++) {
                arry[i][j] = copy[j][i];
            }
        }
        return arry;
    }
    private static double[][] buildTransposedArray(double[][] a) {
        double[][] copy = new  double[a.length][a[0].length];
        for (int i=0;i<a.length;i++) {
            copy[i] = Arrays.copyOf(a[i],a[i].length);
        }
        double[][] result = new double[a[0].length][a.length];
        for (int i=0;i<a[0].length;i++) {
            for (int j=0;j<a.length;j++) {
                result[i][j] = copy[j][i];
            }
        }
        return result;
    }
//    public int[] findHorizontalSeam() {
//        picture = SCUtility.doubleToPicture(arry);
//        int[] result = findVerticalSeam();
//        return result;
//    }


    private double sumRow(double[][] arry, int[] a) {
        double sum = 0;
        for (int i=0;i<width();i++) {
            if (i < a.length) {
                sum += arry[a[i]][i];
            }
        }
        return sum;
    }

    private int[] getSeamByRow(int row, double[][] arry) {
        int[] result = new int[width()];
        int y = row;
        result[0] = row;
        for (int i=1;i<width();i++) {
            //System.out.println("y :"+y);
            if (arry[y-1][i]<=arry[y][i]&&arry[y-1][i]<=arry[y+1][i]) {
                y = y - 1;
                result[i] = y;
            }
            else if (arry[y][i]<=arry[y-1][i]&&arry[y][i]<=arry[y+1][i]) {
                result[i] = y;
            }
            else if (arry[y+1][i]<=arry[y][i]&&arry[y+1][i]<=arry[y-1][i]) {
                y = y + 1;
                result[i] = y;
            }
        }
        //System.out.println("\n arry:"+Arrays.toString(result));
        return result;
    }

    public void removeHorizontalSeam(int[] a) throws IllegalArgumentException {
        // remove horizontal seam from picture、
        for (int i=0;i<a.length-1;i++){
            if (Math.abs(a[i]-a[i+1])>1)throw new IllegalArgumentException();
        }
        if (a.length != pic.width())
            throw new IllegalArgumentException();
        Picture cPic = new Picture(pic.width(), pic.height() - 1);
        for (int i = 0; i < pic.width(); i++) {
            for (int j = 0; j < pic.height(); j++) {
                if (j == a[i])
                    continue;
                int pt = j;
                if (pt > a[i])
                    pt--;
                cPic.set(i, pt, this.pic.get(i, j));
            }
        }
        this.pic = cPic;
    }

    public void removeVerticalSeam(int[] a) throws IllegalArgumentException {
        // remove vertical seam from picture
        if (a.length != pic.height())
            throw new IllegalArgumentException();
        for (int i=0;i<a.length-1;i++){
            if (Math.abs(a[i]-a[i+1])>1)throw new IllegalArgumentException();
        }
        Picture cPic = new Picture(pic.width() - 1, pic.height());
        for (int j = 0; j < pic.height(); j++) {
            for (int i = 0; i < pic.width(); i++) {
                if (i == a[j])
                    continue;
                int pt = i;
                if (pt > a[j])
                    pt--;
                cPic.set(pt, j, pic.get(i, j));
            }
        }
        this.pic = cPic;
    }

    public static void main(String[] args) {
        double[][] a = new double[][]{
                {1.1,2.2},
                {3.3,4.4},
                {5.5,6.6}
        };
        for (double[] b : buildTransposedArray(a)) {
            System.out.println(Arrays.toString(b));
        }
    }

}