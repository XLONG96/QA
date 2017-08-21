package com.QA.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Administrator on 2017/7/19.
 */
@Controller
public class ValidateController {

    @RequestMapping("/validate")
    public void validate(HttpServletResponse response, HttpServletRequest request) throws IOException {
        int width = 120;  //图片宽度
        int height = 40;  //图片高度
        int codeY = 35;  //文字和图片上方的间距
        int codeX = 10;  //文字和图片左方的间距
        int codeB = 22;  //文字之间的间隔
        int fontsize = 36;  //文字的大小
        char code[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        int codecount = 4; //验证码的个数

        //定义图像buffer，充当画板
        BufferedImage buffimg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //Graphics2D充当画笔的角色
        Graphics2D g2d = buffimg.createGraphics();
        //创建一个随机数
        Random random = new Random();
        //创建图片底色并填充到图片
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, width, height);
        //创建字体，字体的大小根据图片高度来定
        Font font = new Font("Fixedsys",Font.PLAIN,fontsize);
        //设置字体和颜色
        g2d.setFont(font);
        g2d.setColor(Color.BLACK);

        g2d.setColor(Color.BLACK);  //设置线条颜色
        //随机生成10条线条
        for(int i=0; i<10; i++){
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(60);  //线条离X轴的距离
            int y1 = random.nextInt(60);  //线条离Y轴的距离
            g2d.drawLine(x, y, x1, y1);
        }

        //设置旋转角度
        int rotate = random.nextInt(8)-8;
        g2d.rotate(rotate * Math.PI / 180);
        //sbcode用于保存随机产生的验证码
        StringBuffer sbcode = new StringBuffer();
        //循环在BufferImage中写入验证码
        for(int i=0; i<codecount; i++){
            //生成一个随机字符
            String strRand = String.valueOf(code[random.nextInt(35)]);
            //生成随机RGB颜色，值从0~254，当三色都为255，此时为白色所以此值忽略
            int red = random.nextInt(255);
            int green = random.nextInt(255);
            int blue = random.nextInt(255);
            g2d.setColor( new Color(red,green,blue ));

            //先将上一次设置的旋转角度清除然后再设置新的旋转角度
            g2d.rotate(-rotate * Math.PI / 180);
            rotate = random.nextInt(8)-8;
            g2d.rotate(rotate * Math.PI / 180);
            //将数字绘制到图片中,括号里面的数字代表文字间距，外面的数字代表X轴
            g2d.drawString(strRand, (i*codeB)+codeX, codeY);
            sbcode.append(strRand);
        }
        //将验证码的字符串保存在session中
        HttpSession session = request.getSession();
        session.setAttribute("validateCode", sbcode.toString());
        //禁止图像缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        //将图片写入输出流中
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(buffimg, "jpeg", sos);
        sos.close();
    }
}
