package logoqrcode;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;

import javax.imageio.ImageIO;

import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import qrcode.BufferedImageLuminanceSource;

/**
 * @ClassName DecodeImgZxing
 * @Description 通过google的zxing解析二维码 注：此代码，不能解析：L纠错级别带logo和H级别的解析
 * @Author Lucky
 * @Date 2018/5/10 13:26
 * @Version 1.0
 */
public class DecodeImgZxing {
    //二维码格式参数
    private static final EnumMap<DecodeHintType, Object> hints = new EnumMap<DecodeHintType, Object>(DecodeHintType.class);
    static{
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
    }
    /**
     * 解析二维码，使用google的zxing
     * @param imgPath 二维码路径
     * @return content 二维码内容
     * */
    public static String decodeImg(File imgFile){
        String content = null;
        if(!imgFile.isFile()){
            System.out.println("输入非文件");
            return null;
        }
        try {
            BufferedImage image = ImageIO.read(imgFile);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            MultiFormatReader reader = new MultiFormatReader();
            Result result = reader.decode(binaryBitmap, hints);
            content = result.getText();
//          System.out.println("二维码结果："+":"+result.toString()+"，"+result.getBarcodeFormat()+"，"+result.getText());
        } catch (NotFoundException e) {
            System.out.println("二维码解析NotFoundException");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("二维码解析IOException");
            e.printStackTrace();
        }
        return content;
    }
}