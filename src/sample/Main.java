package sample;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class Main extends Application {
    int shapes;


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Dogs application");

        Screen screen = Screen.getPrimary();
        Rectangle2D screenSize = screen.getVisualBounds();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        shapes = 6;

        int coordinateYText = 500;

        Group group = new Group();


        int line = 1;
        int previousWidth = 0;


        Color color = null;
        String colorText = "";


        Line line1 = new Line(0, 100, width, 100);
        group.getChildren().add(line1);

        for (int i = 0; i < shapes; i++) {
            Text text = new Text();
            text.setFont(Font.font(15));
            text.setY(coordinateYText);
            coordinateYText += 30;

            int shape = 1 + (int) (Math.random() * 4);
            Object[] colorParam = getColor();

            colorText = (String) colorParam[0];
            color = (Color) colorParam[1];

            if (shape == 1) {
                int radius = 20 + (int) (Math.random() * 50);
                line += previousWidth + radius;
                previousWidth = radius + 20;
                Circle circle = new Circle(line,100, radius);


                if (line + radius < width) {
                    circle.setFill(color);
                    text.setText("Фігура:Круг. Колір:" + colorText +
                            ". Радіус:" + radius +
                            ". Периметр:"+Math.round(2*Math.PI*radius)
                            +". Площа:" + Math.round((Math.PI * Math.pow(radius, 2))));
                    group.getChildren().addAll(text, circle);
                }

            }

            if (shape == 2) {
                int rectWith = (20 + (int) (Math.random() * 200));
                line += previousWidth;
                previousWidth = rectWith + 20;
                Rectangle rectangle1 = new Rectangle(line,
                        100 - (rectWith / 2), rectWith, rectWith);

                if (line + rectWith < width) {
                    rectangle1.setFill(color);
                    text.setText("Фігура:Квадрат. Колір:" + colorText +
                            ". Довжина сторони:" + rectWith +
                            ". Переметр:" + Math.round(rectWith*4) +
                            ". Площа:" + Math.round(rectWith * rectWith));
                    group.getChildren().addAll(text, rectangle1);
                }

            }

            if (shape == 3) {
                double firstPointX = (Math.random() * 150);
                double secondPointX = ((Math.random() * 50));
                double thirdPointX = (60 + (Math.random() * 300));

                double firstPointY = (Math.random() * 100);
                double secondPointY = (120 + (Math.random() * 300));
                double thirdPointY = (120 + (Math.random() * 300));

                double widthTriangle = 0;


                if (firstPointX > secondPointX) {
                    widthTriangle = thirdPointX - secondPointX;
                    firstPointX -= secondPointX;
                    thirdPointX -= secondPointX;
                    secondPointX = 0;
                }

                if (firstPointX < secondPointX) {
                    widthTriangle = thirdPointX - secondPointX;
                    secondPointX -= firstPointX;
                    thirdPointX -= firstPointX;
                    firstPointX = 0;
                }

                if (firstPointX > thirdPointX) {
                    widthTriangle = firstPointX - secondPointX;
                    secondPointX -= firstPointX;
                    thirdPointX -= firstPointX;
                    firstPointX = 0;
                }

                line += previousWidth;
                previousWidth = (int) widthTriangle + 20;

                if (line + previousWidth < width) {
                    double a=findLength(firstPointX,firstPointY,secondPointX,secondPointY);
                    double b=findLength(secondPointX,secondPointY,thirdPointX,thirdPointY);
                    double c=findLength(firstPointX,firstPointY,thirdPointX,thirdPointY);
                    double perimetr = c+b+a;

                    Polygon polygon = new Polygon();
                    polygon.getPoints().addAll(line + firstPointX, firstPointY,
                            line + secondPointX, secondPointY,
                            line + thirdPointX, thirdPointY);

                    polygon.setFill(color);

                    text.setText("Фігура:Трикутник. Колір:" + colorText +
                            ". Периметр:" +Math.round(perimetr)+
                            ". Площа:"+ Math.round(Math.sqrt(perimetr/2*(perimetr/2-a)*(perimetr/2-b)*(perimetr/2-c)))
                    );
                    group.getChildren().addAll(text, polygon);
                }


            }


            if (shape == 4) {
                double firstPointX = 50 + (Math.random() * 150);
                double secondPointX = firstPointX + (Math.random() * 300);
                double fourthPointX = (Math.random() * 50);
                double thirdPointX = (secondPointX + (Math.random() * 350));

                double firstPointY = (Math.random() * 100);
                double secondPointY = firstPointY;
                double thirdPointY = 120 + (Math.random() * 300);
                double fourthPointY = thirdPointY;


                firstPointX -= fourthPointX;
                secondPointX -= fourthPointX;
                thirdPointX -= fourthPointX;
                fourthPointX = 0;

                line += previousWidth;
                previousWidth = (int) (thirdPointX - fourthPointX) + 40;


                if (line + previousWidth < width) {
                    double a=findLength(firstPointX,firstPointY,secondPointX,secondPointY);
                    double b=findLength(secondPointX,secondPointY,thirdPointX,thirdPointY);
                    double c=findLength(thirdPointX,thirdPointY,fourthPointX,fourthPointY);
                    double d=findLength(firstPointX,firstPointY,fourthPointX,fourthPointY);
                    double perimetr = c+b+a+d;

                    Polygon polygon = new Polygon();
                    polygon.getPoints().addAll(
                            line + firstPointX, firstPointY,
                            line + secondPointX, secondPointY,
                            line + thirdPointX, thirdPointY,
                            line + fourthPointX, fourthPointY
                    );

                    polygon.setFill(color);
                    text.setText("Фігура:Трапеція. Колір:" + colorText +
                            ". Периметр:" +Math.round(perimetr)+
                            ". Площа:"+ Math.round(((a+c)/Math.abs(a-c))
                            *Math.sqrt((perimetr/2-a)*(perimetr/2-c)*(perimetr/2-a-b)*(perimetr/2-a-d)))
                    );
                    group.getChildren().addAll(text, polygon);
                }
            }

            if (line > width) break;
        }


        Scene scene = new Scene(group, width, height);
        primaryStage.setScene(scene);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


    public Object[] getColor() {
        Object[] colorParam = new Object[2];

        int breakeTriger = 0;
        int colorBreake = (int) (Math.random() * ColorEnum.values().length);


        for (ColorEnum c : ColorEnum.values()) {
            if (breakeTriger == colorBreake) {
                colorParam[0] = c.name();
                colorParam[1] = c.getColor();

                break;
            } else {
                breakeTriger++;
            }
        }

        return colorParam;
    }


    public double findLength(double x1,double y1,double x2,double y2){
        return Math.sqrt(Math.pow((x1-x2),2)+Math.pow((y1-y2),2));
    }


}




