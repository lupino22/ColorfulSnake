/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakejava;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author lupino
 */
public class DrawPanel extends JPanel implements Runnable {

    private static Snake snake = new Snake(30, 30, 5);
    ;
    private static Food food;
    private static Color color = null;
    public boolean isPaused = false;
    private int SCORE = 0;

    private boolean temp = false;
    public static boolean gameOver = false;

    public DrawPanel() {
        setBackground(Color.WHITE);
    }

    public void speedUp() {
        snake.speedUp();
    }

    public void speedDown() {
        snake.speedDown();
    }

    public int getSpeed() {
        return snake.getSpeed();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D drawImage = (Graphics2D) g;
        if (!gameOver) {
            if (!isPaused) {
                if (color != null) {
                    drawImage.setColor(Color.BLACK);
                    drawImage.drawString("SCORE: " + this.SCORE, 15, 10);
                    drawImage.drawString("FOOD DESEASE: " + food.getRotLvl(), 15, 25);
                    for (int i = 0; i < snake.getBodyX().size(); i++) {
                        drawImage.setColor(snake.getColor(i));
                        drawImage.fillRect(((int) snake.getBodyX().get(i)) * 10, ((int) snake.getBodyY().get(i)) * 10, 10, 10);
                        drawImage.setColor(Color.BLACK);
                        drawImage.drawRect(((int) snake.getBodyX().get(i)) * 10, ((int) snake.getBodyY().get(i)) * 10, 10, 10);
                    }
                    drawImage.setColor(food.getColor());
                    drawImage.fillRect(((int) food.getCoordinatX()) * 10, ((int) food.getCoordinatY()) * 10, 10, 10);
                    drawImage.setColor(Color.BLACK);
                    drawImage.drawRect(((int) food.getCoordinatX()) * 10, ((int) food.getCoordinatY()) * 10, 10, 10);
                    drawImage.dispose();
                    if (snake.isEatingFood(food)) {
                        SCORE += food.getRotLvl();
                        food.resetRot();
                    }
                    snake.move();
                    food.desease();
                }
                if (temp) {
                    if (snake.isConflict()) {
                        gameOver = true;
                    }
                    if ((Controller.wall_x_max / 10) == ((int) snake.getBodyX().get(0))
                            || (Controller.wall_y_max / 10) == ((int) snake.getBodyY().get(0))
                            || -1 == ((int) snake.getBodyY().get(0))
                            || -1 == ((int) snake.getBodyX().get(0))) {
                        gameOver = true;
                    }
                }
            } else {
                drawImage.setColor(Color.BLACK);
                drawImage.drawString("SCORE: " + this.SCORE, 15, 10);
                drawImage.drawString("FOOD DESEASE: " + food.getRotLvl(), 15, 25);
                drawImage.setColor(Color.BLACK);
                drawImage.drawString("PAUSED", Controller.wall_x_max / 2 - 10, Controller.wall_y_max / 2 - 5);
                drawImage.dispose();
            }
        } else {
            drawImage.setColor(Color.BLACK);
            drawImage.drawString("SCORE: " + this.SCORE, 15, 10);
            drawImage.drawString("FOOD DESEASE: " + food.getRotLvl(), 15, 25);
            drawImage.setColor(Color.BLACK);
            drawImage.drawString("GAME OVER", Controller.wall_x_max / 2 - 10, Controller.wall_y_max / 2 - 5);
            timer.stop();
            drawImage.dispose();
        }
    }
    Timer timer;

    public void updateGraphics() {
        gameOver = false;
        isPaused = false;
        this.food = new Food(snake);
        this.color = snake.getColor(0);
        temp = true;
        timer = new Timer(1000 / snake.getSpeed(), new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                repaint();
            }
        });
        timer.start();
    }

    public Snake getSnake() {
        return this.snake;
    }

    public Food getFood() {
        return this.food;
    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
