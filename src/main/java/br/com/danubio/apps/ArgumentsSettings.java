
package br.com.danubio.apps;

import java.awt.Color;

public class ArgumentsSettings {
    
    private Color lineColor;
    private float lineWidth;
    private int panelWidth;
    private int panelHeight;
    private Color panelColor;
    
    private Beach beach;

    public ArgumentsSettings(Beach beach)
    {
        this.beach = beach;
        
        loadArguments();
    }
    
    private void loadArguments()
    {
        setLineColor(beach.getLineColor());
        setLineWidth(beach.getLineWidth());
        
        setPanelWidth(beach.getPanelWidth());
        setPanelHeight(beach.getPanelHeight());
        setPanelColor(beach.getPanelColor());
    }
    
    public boolean isModified()
    {
        return (!lineColor.equals(beach.getLineColor()) || lineWidth != beach.getLineWidth() || panelWidth != beach.getPanelWidth() || panelHeight != beach.getPanelHeight() || !panelColor.equals(beach.getPanelColor()));
    }
    
    public void saveArguments()
    {
        beach.setLineColor(lineColor);
        beach.setLineWidth(lineWidth);
        beach.setPanelColor(panelColor);
        beach.setMatrix(panelWidth, panelHeight);
    }
    
    public void setLineColor(Color color)
    {
        this.lineColor = color;
    }
    
    public void setLineWidth(float width)
    {
        this.lineWidth = width;
    }
    
    public void setPanelWidth(int width)
    {
        this.panelWidth = width;
    }
    
    public void setPanelHeight(int height)
    {
        this.panelHeight = height;
    }
    
    public void setPanelColor(Color color)
    {
        this.panelColor = color;
    }
   
    public Color getLineColor()
    {
        return lineColor;
    }
    
    public float getLineWidth()
    {
        return lineWidth;
    }
    
    public int getPanelWidth()
    {
        return panelWidth;
    }
    
    public int getPanelHeight()
    {
        return panelHeight;
    }
    
    public Color getPanelColor()
    {
        return panelColor;
    }
    
}