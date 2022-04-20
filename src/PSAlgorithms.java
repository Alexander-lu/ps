import acm.graphics.*;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class PSAlgorithms implements PSAlgorithmsInterface {

    public GImage rotateCounterclockwise(GImage source) {
        /************************************************
         * 旋转前，旧图片的信息
         ************************************************/
        int[][] oldPixelArray = source.getPixelArray();     // 旧图片数组
        int oldHeight = oldPixelArray.length;               // 旧图片高度
        int oldWidth = oldPixelArray[0].length;             // 旧图片宽度

        /************************************************
         * 旋转前，旧图片的信息
         ************************************************/
        int newHeight = oldWidth;                               // 新图片高度等于旧图片宽度
        int newWidth = oldHeight;                               // 新图片宽度等于旧图片高度
        int[][] newPixelArray = new int[newHeight][newWidth];   // 为新图片新建一个数组，行数是newHeight，列数是newWidth

        /************************************************
         * 新旧数组的像素对应关系
         ************************************************/
        for (int yNew = 0; yNew < newHeight; yNew++) {
            for (int xNew = 0; xNew < newWidth; xNew++) {
                int yOld = xNew;
                int xOld = oldWidth - yNew - 1;
                newPixelArray[yNew][xNew] = oldPixelArray[yOld][xOld];
            }
        }

        return new GImage(newPixelArray);
    }

    public GImage rotateClockwise(GImage source) {
        /************************************************
         * 旋转前，旧图片的信息
         ************************************************/
        int[][] oldPixelArray = source.getPixelArray();     // 旧图片数组
        int oldHeight = oldPixelArray.length;               // 旧图片高度
        int oldWidth = oldPixelArray[0].length;             // 旧图片宽度

        /************************************************
         * 旋转前，旧图片的信息
         ************************************************/
        int newHeight = oldWidth;                               // 新图片高度等于旧图片宽度
        int newWidth = oldHeight;                               // 新图片宽度等于旧图片高度
        int[][] newPixelArray = new int[newWidth][newHeight];   // 为新图片新建一个数组，行数是newHeight，列数是newWidth

        /************************************************
         * 新旧数组的像素对应关系
         ************************************************/
        for (int yNew = 0; yNew < newHeight; yNew++) {
            for (int xNew = 0; xNew < newWidth; xNew++) {
//                int yOld = xNew;
//                int xOld = oldWidth -yNew ;
                int xOld = yNew;
                int yOld = oldHeight -xNew -1;
//                3=3 -0
//                2=5- 2 -1
//                0=5- ? -2
                newPixelArray[yNew][xNew] = oldPixelArray[yOld][xOld];
//                newPixelArray[1][1] = oldPixelArray[1][3];
            }
        }
        return new GImage(newPixelArray);
    }

    public GImage flipHorizontal(GImage source) {
        /************************************************
         * 旋转前，旧图片的信息
         ************************************************/
        int[][] oldPixelArray = source.getPixelArray();     // 旧图片数组
        int oldHeight = oldPixelArray.length;               // 旧图片高度
        int oldWidth = oldPixelArray[0].length;             // 旧图片宽度

        /************************************************
         * 旋转前，旧图片的信息
         ************************************************/
        int newHeight = oldHeight;                               // 新图片高度等于旧图片高度
        int newWidth = oldWidth;                               // 新图片宽度等于旧图片宽度
        int[][] newPixelArray = new int[newHeight][newWidth];   // 为新图片新建一个数组，行数是oldHeight，列数是oldWidth

        /************************************************
         * 新旧数组的像素对应关系
         ************************************************/
        for (int xNew = 0; xNew < newHeight; xNew++) {
            for (int yNew = 0; yNew < newWidth; yNew++) {
//                int yOld = xNew;
//                int xOld = oldWidth -yNew ;
                int xOld = xNew;
                int yOld = newWidth-yNew -1;
                newPixelArray[xNew][yNew] = oldPixelArray[xOld][yOld];
//                newPixelArray[1][1] = oldPixelArray[1][3];
            }
        }
        return new GImage(newPixelArray);
    }

    public GImage negative(GImage source) {
        int[][] pixelArray = source.getPixelArray();
        for (int row = 0; row < pixelArray[0].length; row++) {
            for (int col = 0; col < pixelArray.length; col++) {
                int pixel = pixelArray[col][row];
                int r = GImage.getRed(pixel);
                int g = GImage.getGreen(pixel);
                int b = GImage.getBlue(pixel);
                int newPixel = GImage.createRGBPixel(255 - r, 255 - g, 255 - b);
                pixelArray[col][row] = newPixel;
            }
        }

        return new GImage(pixelArray);
    }
    public GImage junhenghua(GImage source) {
//         旋转前，旧图片的信息
        int[][] oldPixelArray = source.getPixelArray();
        int oldHeight = oldPixelArray.length;
        // 旧图片高度
        int oldWidth = oldPixelArray[0].length;
        // 旧图片宽度
        int[] luminosityTotal = new int[oldWidth*oldHeight];
        int[][] newPixelArray = new int[oldHeight][oldWidth];
        int n = 0;

        for (int row = 0; row < oldWidth; row++) {
            for (int col = 0; col < oldHeight; col++) {
                int pixel = oldPixelArray[row][col];
                int r = GImage.getRed(pixel);
                int g = GImage.getGreen(pixel);
                int b = GImage.getBlue(pixel);
                int luminosity = computeLuminosity(GImage.getRed(pixel), GImage.getGreen(pixel), GImage.getBlue(pixel));
                luminosityTotal[n] = luminosity;
                n++;
            }
        }
        for (int row = 0; row < oldWidth; row++) {
            for (int col = 0; col < oldHeight; col++) {
                int pixel = oldPixelArray[col][row];
                int luminosity = computeLuminosity(GImage.getRed(pixel), GImage.getGreen(pixel), GImage.getBlue(pixel));
                int m =0;
                for (int object : luminosityTotal) {
                    if (luminosity>=object) {
                        m++;
                    }
                }
                int newluminosity = (int) Math.floor(255*m/oldWidth*oldHeight);
                newPixelArray[col][row] = GImage.createRGBPixel(newluminosity, newluminosity, newluminosity);
            }
        }
        return new GImage(newPixelArray);
    }
    public GImage greenScreen(GImage source) {
//         旋转前，旧图片的信息
        int[][] oldPixelArray = source.getPixelArray();

        int oldHeight = oldPixelArray.length;
        // 旧图片高度
        int oldWidth = oldPixelArray[0].length;
        // 旧图片宽度
        int[][] newPixelArray = new int[oldHeight][oldWidth];
        for (int row = 0; row < oldWidth; row++) {
            for (int col = 0; col < oldHeight; col++) {
                if (GImage.getGreen(oldPixelArray[col][row])>=2*Math.max(GImage.getRed(oldPixelArray[col][row]),GImage.getBlue(oldPixelArray[col][row]))){
                    int newPixel = GImage.createRGBPixel(0, 0, 0,0);
                    newPixelArray[col][row] = newPixel;
                } else  {newPixelArray[col][row] = oldPixelArray[col][row];}
            }
        }
        return new GImage(newPixelArray);
    }
    public GImage convolution(GImage source) {
        /************************************************
         * 旋转前，旧图片的信息
         ************************************************/
        int[][] oldPixelArray = source.getPixelArray();
        int oldHeight = oldPixelArray.length;
        // 旧图片高度
        int oldWidth = oldPixelArray[0].length;
        // 旧图片宽度
        int[][] newPixelArray = new int[oldHeight][oldWidth];
        // 为新图片新建一个数组，行数是newHeight，列数是newWidth
        for (int col = 0; col < oldHeight; col++) {
            for (int row = 0; row < oldWidth; row++) {
                newPixelArray[col][row] = juanji(oldPixelArray,row,col,oldWidth,oldHeight);
            }
        }
        return new GImage(newPixelArray);
    }
    public int juanji(int[][]oldPixelArray,int i,int o,int oldWidth,int oldHeight){
        int rTotal = 0;
        int gTotal = 0;
        int bTotal = 0;
        int ji = 0;
        int xMax = Math.min(i+CONVOLUTION_RADIUS,oldWidth-1);
        int xMin = Math.max(i-CONVOLUTION_RADIUS,0);
        int yMax = Math.min(o+CONVOLUTION_RADIUS,oldHeight-1);
        int yMin = Math.max(o-CONVOLUTION_RADIUS,0);
        for (int y=yMin; y <= yMax; y++) {
            for (int x=xMin; x <= xMax; x++) {
                rTotal += GImage.getRed(oldPixelArray[y][x]);
                gTotal += GImage.getGreen(oldPixelArray[y][x]);
                bTotal += GImage.getBlue(oldPixelArray[y][x]);
                ji++;
            }
        }
        return GImage.createRGBPixel(rTotal/ji,gTotal/ji ,bTotal/ji);
    }

    /**
     * 裁剪图片，裁剪后仅保留选区内容，其他全部删掉
     * @param source        要被裁剪的原始图片
     * @param cropX         选区左上角的x坐标
     * @param cropY         选区左上角的y坐标
     * @param cropWidth     选区的宽度
     * @param cropHeight    选区的高度
     * @return              裁剪后的图片
     */
    public GImage crop(GImage source, int cropX, int cropY, int cropWidth, int cropHeight) {
        /************************************************
         * 旋转前，旧图片的信息
         ************************************************/
        int[][] oldPixelArray = source.getPixelArray();     // 旧图片数组
//        int oldHeight = oldPixelArray.length;               // 旧图片高度
//        int oldWidth = oldPixelArray[0].length;             // 旧图片宽度

        /************************************************
         * 旋转前，旧图片的信息
         ************************************************/
        int newHeight = cropHeight;                               // 新图片高度等于旧图片高度
        int newWidth =  cropWidth;                               // 新图片宽度等于旧图片宽度
        int[][] newPixelArray = new int[cropHeight][cropWidth];   // 为新图片新建一个数组，行数是newHeight，列数是newWidth

        /************************************************
         * 新旧数组的像素对应关系
         ************************************************/
        for (int xNew = 0; xNew < cropHeight; xNew++) {
            for (int yNew = 0; yNew < cropWidth; yNew++) {
//                int yOld = xNew;
//                int xOld = oldWidth -yNew ;
                int xOld = xNew+cropY;
                int yOld = yNew+cropX;
                newPixelArray[xNew][yNew] = oldPixelArray[xOld][yOld];
//                newPixelArray[1][1] = oldPixelArray[1][3];
            }
        }
        return new GImage(newPixelArray);
    }
}
