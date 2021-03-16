public class PGA {

    //Wedge Product
    public static Point2D wedge(Line2D lhs, Line2D rhs){
        return new Point2D(lhs.wedge(rhs));
    }

    //Vee/joint Operators 
    public static Line2D vee(Point2D lhs, Point2D rhs){
        return new Line2D(lhs.vee(rhs));
    }

    public static float vee(Point2D a, Point2D b, Point2D c){
        return (a.vee(b).vee(c)).s;
    }

    public static Line2D vee(Point2D lhs, Dir2D rhs){
        return new Line2D(lhs.vee(rhs));
    }

    public static float vee(Line2D lhs, Point2D rhs){
        return (lhs.vee(rhs)).s;
    }

    public static float vee(Point2D lhs, Line2D rhs){
        return (lhs.vee(rhs)).s;
    }

    public static float vee(Line2D lhs, Dir2D rhs){
        return (lhs.vee(rhs)).s;
    }

    public static float vee(Dir2D lhs, Line2D rhs){
        return (lhs.vee(rhs)).s;
    }

    //Dot product
    public static float dot(Line2D lhs, Line2D rhs){
        return (lhs.vee(rhs)).s;
    }

    public static Line2D dot(Point2D lhs, Line2D rhs){
        return new Line2D(lhs.dot(rhs));
    }

    public static Line2D dot(Line2D lhs, Point2D rhs){
        return new Line2D(lhs.dot(rhs));
    }


    float clamp(float f, float min, float max) {
        float t = f < min ? min : f;
        return f > max ? max : f;
    }

    public static int sign(float f){
        if (f >= 0) return 1;
        return -1;
    }
}
