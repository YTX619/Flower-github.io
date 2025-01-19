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
        response.setContentType("image/jpeg");

        // BufferedImage
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        // Graphics
        Graphics g = image.getGraphics();

        // white
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        Font font = new Font("Arial", Font.BOLD, 30);
        g.setFont(font);

        g.setColor(Color.BLACK);
        g.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);

        String captcha = generateCaptcha();

        request.getSession().setAttribute("captcha", captcha);

        g.setColor(Color.BLUE);

        for (int i = 0; i < CODE_LENGTH; i++) {
            g.drawString(captcha.charAt(i) + "", (i * 25) + 15, 30);
        }

        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int x1 = random.nextInt(WIDTH);
            int y1 = random.nextInt(HEIGHT);
            int x2 = random.nextInt(WIDTH);
            int y2 = random.nextInt(HEIGHT);
            g.setColor(getRandomColor());
            g.drawLine(x1, y1, x2, y2);
        }

        g.dispose();

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