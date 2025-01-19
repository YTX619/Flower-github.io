package servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/CaptchaServlet")
public class CaptchaServlet extends HttpServlet {
    private static final int WIDTH = 120;
    private static final int HEIGHT = 40;
    private static final int CODE_LENGTH = 4;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置响应头，指定内容类型为图片
        response.setContentType("image/jpeg");

        // 创建BufferedImage对象，指定图像类型为RGB
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        // 获取Graphics对象，用于绘制图像
        Graphics g = image.getGraphics();

        // 设置背景颜色为白色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // 设置字体
        Font font = new Font("Arial", Font.BOLD, 30);
        g.setFont(font);

        // 设置边框颜色为黑色
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);

        // 生成随机验证码
        String captcha = generateCaptcha();

        // 将验证码存储到会话中，以便后续验证
        request.getSession().setAttribute("captcha", captcha);

        // 设置文本颜色为蓝色
        g.setColor(Color.BLUE);

        // 在图像上绘制验证码
        for (int i = 0; i < CODE_LENGTH; i++) {
            g.drawString(captcha.charAt(i) + "", (i * 25) + 15, 30);
        }

        // 干扰线
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int x1 = random.nextInt(WIDTH);
            int y1 = random.nextInt(HEIGHT);
            int x2 = random.nextInt(WIDTH);
            int y2 = random.nextInt(HEIGHT);
            g.setColor(getRandomColor());
            g.drawLine(x1, y1, x2, y2);
        }

        // 释放资源
        g.dispose();

        // 将图像输出到响应流中
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

    private String generateCaptcha() {
        String captcha = "";
        Random random = new Random();
        for (int i = 0; i < CODE_LENGTH; i++) {
            captcha += random.nextInt(10);
        }
        return captcha;
    }

    private Color getRandomColor() {
        Random random = new Random();
        int r = random.nextInt(256);
        int g = random.nextInt(256);
        int b = random.nextInt(256);
        return new Color(r, g, b);
    }
}