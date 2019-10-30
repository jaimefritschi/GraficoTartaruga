
package br.com.danubio.apps;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Point;
import java.awt.Stroke;

public class BeachLine {

    private Point p1;
    private Point p2;

    private Color color;
    private Stroke height;

    BeachLine(Color cor, Stroke height) {
        this.color = cor;
        this.height = height;
    }

    public void setP1(Point p1) {
        this.p1 = p1;
    }

    public void setP2(Point p2) {
        this.p2 = p2;
    }

    public Point getP1() {
        return this.p1;
    }

    public Point getP2() {
        return this.p2;
    }

    public Color getColor() {
        return this.color;
    }

    public Stroke getHeight() {
        return this.height;
    }
}