
package br.com.danubio.apps;

import java.awt.Graphics2D;

public interface Controller {
    public void setRight();
    public void setLeft();
    public void setMove(int space);
    public void setDraw(boolean draw);
    public void setPreferences();
    public void drawTurtleGraphic(Graphics2D g2d);
}