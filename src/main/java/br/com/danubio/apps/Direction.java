
package br.com.danubio.apps;

import java.awt.Image;

public class Direction {
    // Para mudar de direção
    private Direction leftDirection;
    private Direction rightDirection;
    // Para mover a tartaruga
    private final int moveX;
    private final int moveY;
    // Para desenhar a tartaruga
    private Image enabledImage;
    private Image disabledImage;
    
    
    
    public Direction(int moveX, int moveY)
    {
        this.moveX = moveX;
        this.moveY = moveY;
    }
    
    public int getMoveX()
    {
        return moveX;
    }
    
    public int getMoveY()
    {
        return moveY;
    }
    
    public void setImage(Image img, boolean enabled)
    {
        if (enabled) {
            enabledImage = img;
        } else {
            disabledImage = img;
        }
    }
    
    public Image getImage(boolean enabled)
    {
        if (enabled) {
            return getImage(enabledImage);
        } else {
            return getImage(disabledImage);
        }
    }
    
    private Image getImage(Image image)
    {
        return image.getScaledInstance(Settings.getSettings().getImageSize(), Settings.getSettings().getImageSize(), 100);
    }
    
    public void setDirectionRight(Direction right)
    {
        rightDirection = right;
    }
    
    public Direction getDirectionRight()
    {
        return rightDirection;
    }
    
    public void setDirectionLeft(Direction left)
    {
        leftDirection = left;
    }
    
    public Direction getDirectionLeft()
    {
        return leftDirection;
    }
}