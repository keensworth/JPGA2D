public class Dir2D extends MultiVector {
    /**
     * Initialize Dir2D
     * @param x x vector component
     * @param y y vector component
     */
    public Dir2D(float x, float y){
        super();
        this.yw = x;
        this.wx = y;
    }

    /**
     * Initialize Dir2D from MultiVector
     * @param mv MultiVector
     */
    public Dir2D(MultiVector mv){
        super();
        yw = mv.yw;
        wx = mv.wx;
    }

    /**
     * Initialize Dir2D from another Dir2D
     * @param dir Dir2D
     */
    public Dir2D(Dir2D dir){
        this(dir.x(),dir.y());
    }

    /**
     * Get the magnitude of this
     * @return float, magnitude of this
     */
    public float magnitude(){
        return (float) Math.sqrt(x()*x() + y()*y());
    }

    /**
     * Get the magnitude squared of this
     * @return float, magnitude squared of this
     */
    public float magnitudeSqr(){
        return x()*x() + y()*y();
    }

    /**
     * Get normalized copy of this
     * @return Dir2D, normalized copy of this
     */
    public Dir2D normalized(){
        float mag = magnitude();
        return new Dir2D(x() / mag, y() / mag);
    }

    /**
     * Normalize this
     * @return Dir2D, this normalized
     */
    public Dir2D normalize(){
        float mag = magnitude();
        this.yw = x() / mag;
        this.wx = y() / mag;
        return this;
    }

    /**
     * Get perpendicular Dir2D
     * @return Dir2D, perpendicular to this
     */
    public Dir2D perp(){
        return new Dir2D(y(), -x());
    }

    /**
     * Add a Point2D to this
     * @param pt Point2D to add
     * @return Point2D, result of this+pt
     */
    public Point2D add(Point2D pt){
        return new Point2D(x()+pt.x(), y()+pt.y());
    }

    /**
     * Add a Dir2D to this
     * @param dir Dir2D to add
     * @return Dir2D, result of this+dir
     */
    public Dir2D add(Dir2D dir){
        return new Dir2D(x()+dir.x(), y()+dir.y());
    }

    /**
     * Subtract a Dir2D from this
     * @param dir Dir2D to subtract
     * @return Dir2d, result of this-dir
     */
    public Dir2D sub(Dir2D dir){
        return new Dir2D(x()-dir.x(), y()-dir.y());
    }

    //Get individual components, labeling confusion with underlying MultiVector class
    public float x(){
        return this.yw;
    }
    public float y(){
        return this.wx;
    }
}