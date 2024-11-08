/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package shestyorochka;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class Shestyorochka {
    
    private static MainForm form;
    private static Image fx;
    private static long lastFrameTime;
    private static List<Snowflake> snowflakes;
    
    public static void main(String[] args) throws IOException {
        form = new MainForm();
        
        // Загружаем изображение снежинки
        fx = ImageIO.read(Shestyorochka.class.getResourceAsStream("./content/fx.jpg"));
        
        // Инициализируем список снежинок
        snowflakes = new ArrayList<>();
        for (int i = 0; i < 50; i++) {  // создаем 50 снежинок
            snowflakes.add(new Snowflake());
        }
    
        

        form.setVisible(true);
    }
    
    public static void onRepaint(Graphics g) {
        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastFrameTime) * 0.000000001f;
        lastFrameTime = currentTime;
        
        // Обновляем положение каждой снежинки
        for (Snowflake snowflake : snowflakes) {
            snowflake.update(deltaTime);
            g.drawImage(fx, (int)snowflake.x, (int)snowflake.y, 10, 10, form);
        }
    }
    
    public static class SnowfallPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            onRepaint(g);
            repaint(); // обновление анимации
        }
    }
    
    // Класс для представления снежинки
    public static class Snowflake {
        float x;
        float y;
        float speed;
        
        public Snowflake() {
            Random random = new Random();
            // Начальное положение снежинки
            x = random.nextInt(400); // ширина экрана, например 400 пикселей
            y = random.nextInt(100) - 100; // чтобы появлялись сверху
            speed = 50 + random.nextFloat() * 150; // случайная скорость падения
        }
        
        public void update(float deltaTime) {
            y += speed * deltaTime;
            if (y > 400) { // если снежинка достигла нижней границы экрана
                y = -10; // сбрасываем наверх
                x = new Random().nextInt(400); // случайное положение по X
            }
        }
    }
  
}
