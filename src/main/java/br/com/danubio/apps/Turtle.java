/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.danubio.apps;

import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author jaime
 */

/**
 * 
 *              NORTH
 *                         
 *     WEST ------|------- EAST
 *                         
 *              SOUTH    
 * 
 *   A tartaruga precisa ter uma posição 
 *  e retornar uma linha após se mover.
 */

public class Turtle {
    
    private boolean draw;
    private Direction currentDirection;
    private Point p1;
    
    private Beach beach;
    
    public Turtle(Beach beach)
    {
        draw = false;
        this.beach = beach;
        this.p1 = beach.getPoint(49, 49);
        
        currentDirection = Settings.getSettings().getDirection();
    }
    
    public void goRight()
    {
        currentDirection = currentDirection.getDirectionRight();
    }
    
    public void goLeft()
    {
        currentDirection = currentDirection.getDirectionLeft();
    }
    
    public void move(int space) throws ArrayIndexOutOfBoundsException
    {
        int nextX = this.p1.x + (currentDirection.getMoveX() * space);
        int nextY = this.p1.y + (currentDirection.getMoveY() * space);

        Point p2 = beach.getPoint(nextX, nextY);
        BeachLine line = null;
        if (draw) {
            line = beach.createBeachLine();
            line.setP1(p1);
            line.setP2(p2);
            beach.addLine(line);
        }

        p1 = p2;
    }
    
    public void setDraw(boolean e)
    {
        draw = e;
    }
    public boolean getDraw()
    {
        return draw;
    }
    
    public void drawTurtle(Graphics2D g2d)
    {
        g2d.translate(-(Settings.getSettings().getTranslate()), -(Settings.getSettings().getTranslate()));
        g2d.drawImage(currentDirection.getImage(draw), 
          p1.x * Settings.getSettings().getZoom(), 
          p1.y * Settings.getSettings().getZoom(), null);
    }
    
}
