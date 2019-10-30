
package br.com.danubio.apps;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Settings {
    private static final int MAX_INDEX = 5;
    private static final String RESOURCES_IMAGES = "images/100x100/";
    private static final Settings uniqueSettings = new Settings();
    
    private static final int[] imageSize = {40, 52, 64, 76, 88, 100};
    private static final int[] zoomPanel = {5, 10, 15, 20, 25, 30};
    
    CircleDirectionLinked circleLinked;
    private int index;
    
    private Settings()
    {
        Direction northDirection = new Direction(0, -1);
        Direction eastDirection = new Direction(1, 0);
        Direction southDirection = new Direction(0, 1);
        Direction westDirection = new Direction(-1, 0);
        
        northDirection.setImage(createImage(RESOURCES_IMAGES + "turtle_enabled_north.png"), true);
        northDirection.setImage(createImage(RESOURCES_IMAGES + "turtle_disabled_north.png"), false);
        eastDirection.setImage(createImage(RESOURCES_IMAGES + "turtle_enabled_east.png"), true);
        eastDirection.setImage(createImage(RESOURCES_IMAGES + "turtle_disabled_east.png"), false);
        southDirection.setImage(createImage(RESOURCES_IMAGES + "turtle_enabled_south.png"), true);
        southDirection.setImage(createImage(RESOURCES_IMAGES + "turtle_disabled_south.png"), false);
        westDirection.setImage(createImage(RESOURCES_IMAGES + "turtle_enabled_west.png"), true);
        westDirection.setImage(createImage(RESOURCES_IMAGES + "turtle_disabled_west.png"), false);
        
        circleLinked = new CircleDirectionLinked(northDirection);
        circleLinked.addDirection(westDirection);
        circleLinked.addDirection(southDirection);
        circleLinked.addDirection(eastDirection);
        
        index = 3;
    }
    
    public boolean zoomIn()
    {
        if (index < MAX_INDEX)
        {
            index++;
            return true;
        }
        
        return false;
    }
    
    public boolean zoomOut()
    {
        if (index > 0) {
            index--;
            return true;
        }
        
        return false;
    }
    
    public void setZoom(int index)
    {
        if (index <= MAX_INDEX && index >= 0) {
            this.index = index;
        }
    }
    
    public int getZoom()
    {
        return index;
    }
    
    public Direction getDirection()
    {
        return circleLinked.getDirection();
    }

    public int getImageSize()
    {
        return imageSize[index];
    }
    public int getTranslate()
    {
        return imageSize[index] / 2;
    }
    
    public static Settings getSettings()
    {
        return uniqueSettings;
    }
    
    protected static Image createImage(String path)
    {
        java.net.URL imgURL = GraphicView.class.getClassLoader().getResource(path);
        try {
            return ImageIO.read(imgURL);
        } catch (IOException ex) {
            Logger.getLogger(GraphicView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}