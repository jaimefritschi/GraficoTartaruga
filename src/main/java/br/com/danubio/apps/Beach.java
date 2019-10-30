package br.com.danubio.apps;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.ArrayList;


public class Beach {

    private List<BeachLine> beachLine;

    private Color lineColor;
    private BasicStroke lineStroke;
    private Matrix matrix;
    private Color panelColor;
    
    
    public Beach() {
        beachLine = new ArrayList<BeachLine>();
        
        setLineColor(Color.BLUE);
        setLineWidth(5.0f);
        setPanelColor(Color.RED);
        
        setMatrix(50, 50);
    }

    public void addLine(BeachLine line) 
    {
        if (line != null) {
            beachLine.add(line);
        }
    }

    public void setMatrix(int row, int column)
    {
        if (matrix == null) {
            matrix = new Matrix(row, column);
        } else {
            int width = matrix.getColumn();
            int height = matrix.getRow();

            if (width != row || height != column) {
                matrix = new Matrix(row, column);
            }
        }
    }
    
    public Point getPoint(int row, int column) throws ArrayIndexOutOfBoundsException
    {
        return matrix.getPoint(row, column);
    }
    
    public BeachLine createBeachLine() {
        return new BeachLine(this.lineColor, this.lineStroke);
    }
    
    public Color getLineColor()
    {
        return lineColor;
    }
    
    public void setLineColor(Color cor)
    {
        this.lineColor = cor;
    }
    
    public void setLineWidth(float width)
    {
        this.lineStroke = new BasicStroke(width);
    }
    
    public float getLineWidth()
    {
        return lineStroke.getLineWidth();
    }
    
    public Color getPanelColor()
    {
        return panelColor;
    }
    
    public void setPanelColor(Color panel)
    {
        this.panelColor = panel;
    }
    
    
    public int getPanelHeight()
    {
        return (matrix.getRow() * Settings.getSettings().getZoom()) - Settings.getSettings().getZoom();
    }
    
    public int getPanelWidth()
    {
        return (matrix.getColumn() * Settings.getSettings().getZoom()) - Settings.getSettings().getZoom();
    }
    
    public void drawBeach(Graphics2D g2d) 
    {

        g2d.translate(Settings.getSettings().getTranslate(), Settings.getSettings().getTranslate());
        g2d.setColor(getPanelColor());
        g2d.fillRect(0, 0, getPanelWidth(), getPanelHeight());
        
        for (BeachLine line : beachLine) {
            
            g2d.setPaint(line.getColor());
            g2d.setStroke(line.getHeight());
            
            g2d.drawLine(line.getP1().x * Settings.getSettings().getZoom(), 
              line.getP1().y * Settings.getSettings().getZoom(), 
              line.getP2().x * Settings.getSettings().getZoom(), 
              line.getP2().y * Settings.getSettings().getZoom());
        }
    }
}

class Matrix {
    private int row;
    private int column;
    
    private Point[][] points;
    Matrix(int row, int column)
    {
        this.row = row;
        this.column = column;
        points = new Point[row][column];
        
        for(int i=0; i < row; i++) {
            for(int j=0; j < column; j++) {
                points[i][j] = new Point(i, j);
            }
        }
    }
    
    public Point getPoint(int row, int column) throws ArrayIndexOutOfBoundsException
    {
        return points[row][column];
    }
    
    public int getRow()
    {
        return this.row;
    }
    
    public int getColumn()
    {
        return this.column;
    }
    
}

