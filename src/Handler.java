
import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Color;

public class Handler {
    LinkedList<GameObject> object = new LinkedList<GameObject>();

    public void tick() {
        int i = 0;
        boolean error = false;
        GameObject tempObject = null;
        while (i < object.size()) {
            do{
                try{
                    tempObject = object.get(i);
                    error = false;
                }catch(NullPointerException e){
                    error = true;
                }
            }while(error);
                    tempObject.tick();
            i++;
        }
    }
    public void render(Graphics g) {
        int i = 0;
        GameObject tempObject = null;
        boolean error = false;
        while (i < object.size()) {
                do{
                    try{
                        tempObject = object.get(i);
                        error = false;
                    }catch(NullPointerException e){
                        error = true;
                    }
                }while(error);
                    tempObject.render(g);
            i++;
        }
    }
    public void addObject(GameObject object) {
        this.object.add(object);
    }

    public void setColor(int index, Color color) {
        this.object.get(index).setColor(color);
    }

    public void setX(int index, int x) {
        this.object.get(index).setX(x);
    }

    public void setY(int index, int y) {
        this.object.get(index).setY(y);
    }

    public void setWidth(int index, int x) {
        this.object.get(index).setWidth(x);
    }

    public void setHeight(int index, int y) {
        this.object.get(index).setHeight(y);
    }

    public void addSpecificObject(GameObject object, int objectLocation) {
        this.object.add(objectLocation, object);
    }
    public void replaceObject(int objectLocation, GameObject object){
        this.object.remove(objectLocation);
        this.object.add(objectLocation, object);
    }
    public void reset(){
        for(int i = object.size();i != 0;i--){
            object.remove();
        }
    }
    public void moveObject(int objectLocation, int x, int y) {
        this.object.get(objectLocation).setX(x);
        this.object.get(objectLocation).setY(y);
    }
}