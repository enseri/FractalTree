package src;
import java.awt.Color;
public class Colors{
    Color color = null;
    public Colors(int n) {
        switch(n) {
            case 0:
                color = Color.black;
                break;
            case 1:
                color = Color.blue;
                break;
            case 2:
                color = Color.cyan;
                break;
            case 3:
                color = Color.darkGray;
                break;
            case 4:
                color = Color.gray;
                break;
            case 5:
                color = Color.green;
                break;
            case 6:
                color = Color.lightGray;
                break;
            case 7:
                color = Color.magenta;
                break;
            case 8:
                color = Color.orange;
                break;
            case 9:
                color = Color.pink;
                break;
            case 10:
                color = Color.red;
                break;
            case 11:
                color = Color.white;
                break;
            case 12:
                color = Color.yellow;
                break;
        }
    }
    public Color getColor() {
        return color;
    }
}