public class Line2D extends MultiVector {
    /**
     * Initialize Line2D
     * @param x x vector component
     * @param y y vector component
     * @param w homogeneous coordinate
     */
    public Line2D(float x, float y, float w){
        super();
        this.x = x;
        this.y = y;
        this.w = w;
    }

    /**
     * Initialize Line2D with homogeneous coordinate=1
     * @param x x parameter
     * @param y y parameter
     */
    public Line2D(float x, float y){
            this(x,y,1);
    }

    /**
     * Initialize Line2D from MultiVector
     * @param mv MultiVector
     */
    public Line2D(MultiVector mv){
        super();
        s = mv.s;
        x = mv.x;
        y = mv.y;
        w = mv.w;
    }

    /**
     * Initalize Line2D from another Line2D
     * @param line line to copy
     */
    public Line2D(Line2D line){
        this(line.x,line.y,line.w);
    }

    /**
     * Get the magnitude of the line
     * @return float, magnitude of the line
     */
    public float magnitude(){
        return (float) Math.sqrt(x*x + y*y);
    }

    /**
     * Get the magnitude squared of the line
     * @return float, magnitude squared of the line
     */
    public float magnitudeSqr(){
        return x*x + y*y;
    }

    /**
     * Normalize the line
     * @return Line2D, normalized
     */
    public Line2D normalize(){
        float mag = magnitude();
        x /= mag;
        y /= mag;
        w /= mag;
        return this;
    }

    /**
     * Get a copy of this Line2D, normalized
     * @return Line2D, normalized
     */
    public Line2D normalized(){
        float mag = magnitude();
        return new Line2D(x / mag, y / mag);
    }
}
