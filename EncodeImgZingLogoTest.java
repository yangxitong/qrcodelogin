package logoqrcode;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName EncodeImgZingLogoTest
 * @Description TODO 测试
 * @Author Lucky
 * @Date 2018/5/10 13:20
 * @Version 1.0
 */
public class EncodeImgZingLogoTest {

    public static void main(String[] args) throws IOException {
        String contents = "https://blog.csdn.net/shasiqq";
        String format = "jpg"; //***此处如果格式为"gif"，则logo图片为黑色，其他格式ok
        //生成二维码
        File img = new File("D:"+File.separator+"csdn.jpg");
        EncodeImgZxing.writeToFile(contents, format, img);
//      //添加logo图片
        File logoImg = new File("D:"+File.separator+"logo.jpg");
        File img1 = new File("D:"+File.separator+"csdnAndLogo.jpg");
        EncodeImgZingLogo.writeToFile(img, logoImg, format, img1);

        //解析二维码
        String content = DecodeImgZxing.decodeImg(img);
        System.out.println("1:"+content);
        //带logo
        String content1 = DecodeImgZxing.decodeImg(img1);
        System.out.println("2:"+content1);
    }
}