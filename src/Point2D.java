public class Point2D extends MultiVector {
    /**
     * Initialize Point2D
     * @param x x vector component
     * @param y y vector component
     */
    public Point2D(float x, float y){
        this.yw = x;
        this.wx = y;
        this.xy = 1;
    }

    /**
     * Initialize Point2D from MultiVector
     * @param mv MultiVector
     */
    public Point2D(MultiVector mv){
        mv.xy = mv.xy == 0 ? 1 : mv.xy;
        this.yw = mv.yw/mv.xy;
        this.wx = mv.wx/mv.xy;
        this.xy = mv.xy;
    }

    /**
     * Initialize Point2D from another
     * @param pt Point2D
     */
    public Point2D(Point2D pt){
        this(pt.x(),pt.y());
    }

    /**
     * Get scaled copy of this Point2D
     * @param s scale factor
     * @return Point2D, scaled by s
     */
    public Point2D scale(float s){
        return new Point2D(x()*s,y()*s);
    }

    /**
     * Get the addition of this and another Point2D
     * @param pt Point2D to add to this
     * @return Point2D, copy of this added with pt
     */
    public Point2D add(Point2D pt){
        return new Point2D(x()+pt.x(), y()+pt.y());
    }

    /**
     * Get the addition of this and Dir2
     * @param dir Dir2D to add to this
     * @return Point2D, copy of this added ith pt
     */
    public Point2D add(Dir2D dir){
        return new Point2D(x()+dir.x(), y()+dir.y());
    }

    /**
     * Get the subtraction of a Point2D from this
     * @param pt Point2D to subtract from this
     * @return Dir2D, the subtraction of pt from this
     */
    public Dir2D sub(Point2D pt){
        return new Dir2D(x()-pt.x(),y()-pt.y());
    }

    /**
     * Get the magnitude of this
     * @return float, magnitude of this
     */
    public float magnitude(){
        return (float) Math.sqrt(x()*x()+y()*y());
    }

    //Get individual components, labeling confusion with underlying MultiVector class
    public float x(){
        return this.yw;
    }
    public float y(){
        return this.wx;
    }
    public float w(){
        return this.xy;
    }
    public void x(float x){
        this.yw = x;
    }
    public void y(float y){
        this.wx = y;
    }
    public void w(float w){
        this.xy = w;
    }

}