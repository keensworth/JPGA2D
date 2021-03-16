public class Motor2D extends MultiVector {
    /**
     * Initialize Motor2D
     * @param s scalar component
     * @param yw yw bivector component
     * @param wx wx bivector component
     * @param xy xy bivector component
     */
    public Motor2D(float s, float yw, float wx, float xy){
        super();
        this.s = s;
        this.yw = yw;
        this. wx = wx;
        this.xy = xy;
    }

    /**
     * Initalize Motor2D with scalar onlt
     * @param s scalar component
     */
    public Motor2D(float s){
        this(s,0,0,0);
    }

    /**
     * Initialize Motor2D with MultiVector
     * @param mv MultiVector
     */
    public Motor2D(MultiVector mv){
        super();
        s = mv.s;
        yw = mv.yw;
        wx = mv.wx;
        xy = mv.xy;
    }

    public static Motor2D Translator2D(float dist, Dir2D perp_direction){
        return new Motor2D(1,dist/2.0f*perp_direction.x(),dist/2.0f*perp_direction.y(),0);
    }

    public static Motor2D Translator2D(Dir2D displacement) {
        float dist = displacement.magnitude();
        displacement = displacement.normalized();
        if (dist > 0){
            return Translator2D(dist,displacement.perp());
        }
        return new Motor2D(1); //Identity transformation
    }

    public static Motor2D Rotator2D(float angle, Point2D point) {
        float c = (float) Math.cos(angle/2.0f);
        float s = (float) Math.sin(angle/2.0f);
        return new Motor2D(c,s*point.x(),s*point.y(), s);
    }
}
