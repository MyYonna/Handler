package QR;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * ��ά�����ɹ����ࡾ��Сͼ�꡿
 * <br> QRCodeUtils ��������汾
 * @author bxw
 * @time 2016-07-17 22:13:45
 * @version v1.1
 */
public class BarcodeFactory {
    
    /**
     * ͼƬ��ʽ����
     * @value Array
     */
    private static String[] IMAGE_TYPE = {"BMP", "bmp", "jpg", "JPG", "wbmp", "jpeg", "png", "PNG", "JPEG", "WBMP", "GIF", "gif","ICON","icon"};
    
    /**
     * ��ά����
     */
    public static final int WIDTH = 260;
    
    /**
     * ��ά��߶�
     */
    public static final int HEIGHT = 260;
        
    /**
     * ͼ����
     */
    private static final int IMAGE_WIDTH = 68;
    /**
     * ͼ��߶�
     */
    private static final int IMAGE_HEIGHT = 68;
    /**
     * ��ͼ��С�������Ρ�
     */
    private static final int IMAGE_HALF_WIDTH = IMAGE_WIDTH / 2;
    /**
     * ��ͼ�߿�
     */
    private static final int FRAME_WIDTH = 5;

    /**
     * ��ά��д����
     */
    private static MultiFormatWriter mutiWriter = new MultiFormatWriter();

    /**
     * ��ά������-�������
     * @param content ����
     * @param width ���
     * @param height �߶�
     * @param iconImagePath ͼ��ԭ·��
     * @param qrcodeImagePath ��ά����·��
     * @param hasFiller 
     *             ��������ʱ�Ƿ���Ҫ���ף�trueΪ����; falseΪ������
     * @return 
     *         �ɹ����������ͼƬ����·����ʧ�ܣ�����null
     */
    public static String encode(String content, int width, int height,
            String iconImagePath, String qrcodeImagePath,boolean hasFiller) {
        try {
            /**
             * ͼ���ʽУ��
             */
            File icon = new File(iconImagePath);
            if(!icon.exists()){
                System.err.println("ϵͳ�Ҳ���ͼ�������ļ� ��");
                return null;
            }
            String iconFileName = icon.getName();
            // �õ��ϴ��ļ�����չ��
            String fileExtName = iconFileName.substring(iconFileName.lastIndexOf(".") + 1);
            if(!checkIMGType(fileExtName)){
                System.err.println("ͼ���ʽ���� ��");
                return null;
            }
            
            if(width<260||height<260){
                width = BarcodeFactory.WIDTH;
                height = BarcodeFactory.HEIGHT;
            }
            ImageIO.write(genBarcode(content, width, height, iconImagePath,hasFiller),
                    "png", new File(qrcodeImagePath));
            System.err.println("��ά��������  "+qrcodeImagePath);
            return qrcodeImagePath;
        } catch (IOException e) {
            System.err.println("ͼƬ��ȡ�쳣 �� "+e.getMessage());
        } catch (WriterException e) {
            System.err.println("ͼƬ����쳣 ��"+e.getMessage());
        }
        return null;
    }

    /**
     * ͼƬ������
     * @param content
     * @param width
     * @param height
     * @param iconImagePath
     * @param hasFiller 
     *             ��������ʱ�Ƿ���Ҫ���ף�trueΪ����; falseΪ������;
     * @return
     * @throws WriterException
     * @throws IOException
     */
    private static BufferedImage genBarcode(String content, int width,
            int height, String iconImagePath,boolean hasFiller) throws WriterException,
            IOException {
        // ��ȡԴͼ��
        BufferedImage scaleImage = scale(iconImagePath, IMAGE_WIDTH,
                IMAGE_HEIGHT, hasFiller);
        int[][] srcPixels = new int[IMAGE_WIDTH][IMAGE_HEIGHT];
        for (int i = 0; i < scaleImage.getWidth(); i++) {
            for (int j = 0; j < scaleImage.getHeight(); j++) {
                srcPixels[i][j] = scaleImage.getRGB(i, j);
            }
        }

        Map<EncodeHintType, Object> hint = new HashMap<EncodeHintType, Object>();
        hint.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hint.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // ���ɶ�ά��
        BitMatrix matrix = mutiWriter.encode(content, BarcodeFormat.QR_CODE,
                width, height, hint);

        // ��ά����תΪһά��������
        int halfW = matrix.getWidth() / 2;
        int halfH = matrix.getHeight() / 2;
        int[] pixels = new int[width * height];

        for (int y = 0; y < matrix.getHeight(); y++) {
            for (int x = 0; x < matrix.getWidth(); x++) {
                // ��ȡͼƬ
                if (x > halfW - IMAGE_HALF_WIDTH
                        && x < halfW + IMAGE_HALF_WIDTH
                        && y > halfH - IMAGE_HALF_WIDTH
                        && y < halfH + IMAGE_HALF_WIDTH) {
                    pixels[y * width + x] = srcPixels[x - halfW
                            + IMAGE_HALF_WIDTH][y - halfH + IMAGE_HALF_WIDTH];
                } 
                // ��ͼƬ�����γɱ߿�
                else if ((x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH
                        && x < halfW - IMAGE_HALF_WIDTH + FRAME_WIDTH
                        && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
                        + IMAGE_HALF_WIDTH + FRAME_WIDTH)
                        || (x > halfW + IMAGE_HALF_WIDTH - FRAME_WIDTH
                                && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
                                && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
                                + IMAGE_HALF_WIDTH + FRAME_WIDTH)
                        || (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH
                                && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
                                && y > halfH - IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
                                - IMAGE_HALF_WIDTH + FRAME_WIDTH)
                        || (x > halfW - IMAGE_HALF_WIDTH - FRAME_WIDTH
                                && x < halfW + IMAGE_HALF_WIDTH + FRAME_WIDTH
                                && y > halfH + IMAGE_HALF_WIDTH - FRAME_WIDTH && y < halfH
                                + IMAGE_HALF_WIDTH + FRAME_WIDTH)) {
                    pixels[y * width + x] = 0xfffffff;
                } else {
                    // �˴������޸Ķ�ά�����ɫ�����Էֱ��ƶ���ά��ͱ�������ɫ��
                    pixels[y * width + x] = matrix.get(x, y) ? 0xff000000
                            : 0xfffffff;
                }
            }
        }

        BufferedImage image = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        image.getRaster().setDataElements(0, 0, width, height, pixels);
        
        return image;
    }

    /**
     * �Ѵ����ԭʼͼ�񰴸߶ȺͿ�Ƚ������ţ����ɷ���Ҫ���ͼ��
     * 
     * @param iconImagePath
     *            Сͼ��Դ�ļ���ַ
     * @param height
     *            Ŀ��߶�
     * @param width
     *            Ŀ����
     * @param hasFiller
     *            ��������ʱ�Ƿ���Ҫ���ף�trueΪ����; falseΪ������;
     * @throws IOException
     */
    private static BufferedImage scale(String iconImagePath, int height,
            int width, boolean hasFiller) throws IOException {
        double ratio = 0.0; // ���ű���
        File file = new File(iconImagePath);
        BufferedImage srcImage = ImageIO.read(file);
        Image destImage = srcImage.getScaledInstance(width, height,
                BufferedImage.SCALE_SMOOTH);
        // �������
        if ((srcImage.getHeight() > height) || (srcImage.getWidth() > width)) {
            if (srcImage.getHeight() > srcImage.getWidth()) {
                ratio = (new Integer(height)).doubleValue()
                        / srcImage.getHeight();
            } else {
                ratio = (new Integer(width)).doubleValue()
                        / srcImage.getWidth();
            }
            AffineTransformOp op = new AffineTransformOp(
                    AffineTransform.getScaleInstance(ratio, ratio), null);
            destImage = op.filter(srcImage, null);
        }
        if (hasFiller) {// ����
            BufferedImage image = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D graphic = image.createGraphics();
            graphic.setColor(Color.white);
            graphic.fillRect(0, 0, width, height);
            if (width == destImage.getWidth(null))
                graphic.drawImage(destImage, 0,
                        (height - destImage.getHeight(null)) / 2,
                        destImage.getWidth(null), destImage.getHeight(null),
                        Color.white, null);
            else
                graphic.drawImage(destImage,
                        (width - destImage.getWidth(null)) / 2, 0,
                        destImage.getWidth(null), destImage.getHeight(null),
                        Color.white, null);
            graphic.dispose();
            destImage = image;
            System.err.println("INFO ͼ�겹������� ");
        }
        return (BufferedImage) destImage;
    }

    /**
     * ͼƬ��ʽУ��
     * @param fileExtName
     * @return
     */
    private static boolean checkIMGType(String fileExtName){
        boolean flag = false;
        for (String type : IMAGE_TYPE) {
            //-- ͼƬ��ʽ��ȷ
            if(type.toLowerCase().equals(fileExtName.toLowerCase())){
                flag = true;
                break;
            }
        }
        //------------ͼƬ��ʽУ�����
        return flag;
    }
    
    /**
     * �������������
     * @param args
     */
    public static void main(String[] args) {
        String codeIconPath = "C:/Users/zhangpeng/Desktop/icon.png";
        String codePath = "C:/Users/zhangpeng/Desktop/"+new Date().getTime()+".png";
        
        /**
         * ���Է������
         */
        BarcodeFactory.encode("��Ŀ����Ͷ���",300, 300, codeIconPath, codePath,false);
    }
}
