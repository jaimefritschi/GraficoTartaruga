
package br.com.danubio.apps;

import java.awt.Color;
import java.awt.Graphics2D;

public class GraphicController implements Controller {
    
    private GraphicView view;
    private Turtle turtle;
    private Beach beach;
    
    private ArgumentsSettings arguments;
    
    public GraphicController()
    {
        beach = new Beach();
        turtle = new Turtle(beach);
        
        arguments = new ArgumentsSettings(beach);
        view = new GraphicView(this, arguments);
        view.createGraphicView();
    }
    
    public void setRight() 
    {
        turtle.goRight();
        view.repaint();
    }
    
    public void setLeft()
    {
        turtle.goLeft();
        view.repaint();
    }

    public void setMove(int space) 
    {
        try {
            turtle.move(space);
        }
        catch (ArrayIndexOutOfBoundsException exception) {
            view.showMessage("Tente novamente", "Você adicionou espaço demais.\n");
        }
        view.repaint();
    }

    public void setDraw(boolean draw) 
    {
        view.setTurtleDraw(draw);
        turtle.setDraw(draw);
        view.repaint();
    }

    public void drawTurtleGraphic(Graphics2D g2d) 
    {
        beach.drawBeach(g2d);
        turtle.drawTurtle(g2d);
    }
    
    public void setPreferences()
    {
        if (arguments.isModified()) {
            arguments.saveArguments();
        }
    }
}