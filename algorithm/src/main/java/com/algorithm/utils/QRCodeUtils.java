package com.algorithm.utils;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCodeUtils {
    // 二维码的字符编码
    private static final String CHARSET = "utf-8";
    // 二维码的文件类型
    private static final String FORMAT_NAME = "JPG";
    // 二维码尺寸
    private static final int QRCODE_SIZE = 300;
    // 内嵌图片宽度
    private static final int WIDTH = 60;
    // 内嵌图片高度
    private static final int HEIGHT = 60;

    /**
     * 创建固定大小的二维码图片
     * @param content       二维码包含的URL
     * @param imgPath       内嵌图片的URL地址，如果不需要内嵌图片，可以传null
     * @param needCompress  内嵌图片是否需要压缩大小
     * @return
     * @throws Exception
     */
    private static BufferedImage createImage(String content, String imgPath, boolean needCompress) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,
                QRCODE_SIZE, QRCODE_SIZE, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (imgPath == null || "".equals(imgPath)) {
            return image;
        }
        // 插入图片
        QRCodeUtils.insertImage(image, imgPath, needCompress);
        return image;
    }

    /**
     * 在二维码中插入图片
     * @param source        原二维码
     * @param imgPath       要内嵌的图片的URL
     * @param needCompress  是否需要压缩。此处的压缩是固定大小的
     * @throws Exception
     */
    private static void insertImage(BufferedImage source, String imgPath, boolean needCompress) throws Exception {
        File file = new File(imgPath);
        if (!file.exists()) {
            System.err.println("" + imgPath + "   该文件不存在！");
            return;
        }
        Image src = ImageIO.read(new File(imgPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress) { // 压缩LOGO
            if (width > WIDTH) {
                width = WIDTH;
            }
            if (height > HEIGHT) {
                height = HEIGHT;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    /**
     *  可对外发布的生成二维码的方法
     * @param content       二维码的URL
     * @param imgPath       内嵌图片的URL，不需要内嵌图片可传null
     * @param destPath      生成二维码图片的保存地址
     * @param needCompress  内嵌图片是否需要压缩
     * @throws Exception
     */
    public static void encode(String content, String imgPath, String destPath, boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtils.createImage(content, imgPath, needCompress);
        mkdirs(destPath);
        ImageIO.write(image, FORMAT_NAME, new File(destPath));
    }

    public static BufferedImage encode(String content, String imgPath, boolean needCompress) throws Exception {
        return QRCodeUtils.createImage(content, imgPath, needCompress);
    }

    private static void mkdirs(String destPath) {
        File file = new File(destPath);
        // 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }

    /**
     * 对外可发布的生成二维码的方法
     * @param content   二维码的URL
     * @param imgPath       内嵌图片的URL，不需要内嵌图片可传null
     * @param destPath      生成二维码图片的保存地址
     * @throws Exception
     */
    public static void encode(String content, String imgPath, String destPath) throws Exception {
        QRCodeUtils.encode(content, imgPath, destPath, false);
    }

    /**
     *  可对外发布的生成二维码的方法
     * @param content       二维码的URL
     * @param destPath      生成二维码图片的保存地址
     * @throws Exception
     */
    public static void encode(String content, String destPath) throws Exception {
        QRCodeUtils.encode(content, null, destPath, false);
    }

    /**
     *  可对外发布的生成二维码的方法
     * @param content       二维码的URL
     * @param imgPath       内嵌图片的URL，不需要内嵌图片可传null
     * @param output        生成二维码图片的输出流
     * @param needCompress  内嵌图片是否需要压缩
     * @throws Exception
     */
    public static void encode(String content, String imgPath, OutputStream output, boolean needCompress)
            throws Exception {
        BufferedImage image = QRCodeUtils.createImage(content, imgPath, needCompress);
        ImageIO.write(image, FORMAT_NAME, output);
    }

    /**
     *  可对外发布的生成二维码的方法
     * @param content       二维码的URL
     * @param output        生成二维码图片的输出流
     * @throws Exception
     */
    public static void encode(String content, OutputStream output) throws Exception {
        QRCodeUtils.encode(content, null, output, false);
    }

    /**
     * 解析文件中的二维码所包含的URL
     * @param file  需要解析的文件
     * @return      解析出来的URL
     * @throws Exception
     */
    public static String decode(File file) throws Exception {
        BufferedImage image;
        image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable hints = new Hashtable();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, hints);
        String resultStr = result.getText();
        return resultStr;
    }

    /**
     * 解析URL地址下的文件的二维码包含的URL
     * @param path      要解析的文件路径
     * @return          解析出来的URL
     * @throws Exception
     */
    public static String decode(String path) throws Exception {
        return QRCodeUtils.decode(new File(path));
    }

    /**
     * 可自定义图片大小生成二维码
     * @param content       二维码的URL
     * @param imgPath       内嵌图片的URL
     * @param needCompress  内嵌图片是否需要压缩
     * @param size          生成的二维码图片的大小
     * @return
     * @throws Exception
     */
    private static BufferedImage createImage(String content, String imgPath, boolean needCompress, int size) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, size, size, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (imgPath == null || "".equals(imgPath)) {
            return image;
        }
        // 插入图片
        QRCodeUtils.insertImage(image, imgPath, true, size);
        return image;
    }

    private static void insertImage(BufferedImage source, String imgPath, boolean needCompress, int size) throws Exception {
        File file = new File(imgPath);
        if (!file.exists()) {
            System.err.println("" + imgPath + "   该文件不存在！");
            return;
        }
        Image src = ImageIO.read(new File(imgPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress) { // 压缩LOGO
            if (width > (size/5)) {
                width = size/5;
            }
            if (height > (size/5)) {
                height = size/5;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (size - width) / 2;
        int y = (size - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    /**
     * 生成可自定义大小的二维码
     * @param content       二维码的URL
     * @param imgPath       二维码内嵌图片URL，不需要内嵌图片可以传null
     * @param destPath      二维码生成以后保存地址
     * @param needCompress  是否需要压缩
     * @param size          生成的二维码图片的大小，二维码为正方形
     * @throws Exception
     */
    public static void encode(String content, String imgPath, String destPath, boolean needCompress, int size) throws Exception {
        BufferedImage image = QRCodeUtils.createImage(content, imgPath, needCompress, size);
        mkdirs(destPath);
        // String file = new Random().nextInt(99999999)+".jpg";
        // ImageIO.write(image, FORMAT_NAME, new File(destPath+"/"+file));
        ImageIO.write(image, FORMAT_NAME, new File(destPath));
    }
}
