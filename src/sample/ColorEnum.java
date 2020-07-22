package sample;
import javafx.scene.paint.Color;


public enum ColorEnum {

    AQUA(Color.AQUA),
    BLACK(Color.BLACK),
    BLUE(Color.BLUE),
    CORAL(Color.CORAL),
    GREEN(Color.GREEN),
    GREY(Color.GREY),
    RED(Color.RED),
    YELLOW(Color.YELLOW);

    private Color color ;

    ColorEnum(Color color) {
        this.color=color;
    }


    public Color getColor() {
        return color;
    }
}
