package task2;

public class Rectangle {
    private double x1;
    private double x2;
    private double y1;
    private double y2;

    public Rectangle(){
        x1 = 0;
        x2 = 1;
        y1 = 0;
        y2 = 0;
    }

    public Rectangle(double a, double b, double c, double d){
        x1 = a;
        y1 = b;
        x2 = c;
        y2 = d;
    }

    public double square(){
        return Math.abs((x1 - x2) * (y1 - y2));
    }

    public boolean is_square(){
        return Math.abs(x1 - x2) == Math.abs(y1 - y2);
    }

    @Override
    public String toString() {
        return "(" + x1 + "; " + y1 + "), (" + x2 + "; " + y2 + ")";
    }
}
