package src;

import java.awt.Color;
import java.awt.Graphics;

public class Branch extends Object{
    public Branch(int[] xPoints, int[] yPoints, Color color, ID id){
        super(xPoints, yPoints, color, id);
    }

    public void tick(){

    }


    public void render(Graphics g) {
        g.setColor(super.getColor());
        g.drawLine(super.getXPoints()[0], super.getYPoints()[0], super.getXPoints()[1], super.getYPoints()[1]);
    }
}