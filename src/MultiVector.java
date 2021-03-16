public class MultiVector {
    public float s; //Scalar
    public float x,y,w; //Vector Components
    public float yw, wx, xy; //BiVector Components
    public float wxy; //Pseudoscalar

    /**
     * Initialize MultiVector
     * @param s scalar component
     * @param x x vector component
     * @param y y vector component
     * @param w w vector component
     * @param yw yw bivector component
     * @param wx wx bivector component
     * @param xy xy bivector component
     * @param wxy wxy pseudoscalar
     */
    public MultiVector(float s, float x, float y, float w, float yw, float wx, float xy, float wxy){
        this.s = s;
        this.x = x;
        this.y = y;
        this.w = w;
        this.yw = yw;
        this.wx = wx;
        this.xy = xy;
        this.wxy = wxy;
    }

    /**
     * Initlaize MultiVector with 0
     */
    public MultiVector(){
        this(0,0,0,0,0,0,0,0);
    }

    /**
     * Initalize MultiVector from another
     * @param mv MultiVector
     */
    public MultiVector(MultiVector mv){
        this.s = mv.s;
        this.x = mv.x;
        this.y = mv.y;
        this.w = mv.w;
        this.yw = mv.yw;
        this.wx = mv.wx;
        this.xy = mv.xy;
        this.wxy = mv.wxy;
    }

    /**
     * Initialize MultiVector from Point2D
     * @param pt Point2D
     */
    public MultiVector(Point2D pt){
        this(0,0,0,0,pt.x(),pt.y(),1,0);
    }

    /**
     * Initialize MultiVector from Dir2D
     * @param dir Dir2D
     */
    public MultiVector(Dir2D dir){
        this(0,0,0,0,dir.x(),dir.y(),0,0);
    }

    /**
     * Initialize MultiVector from Motor2D
     * @param motor Motor2D
     */
    public MultiVector(Motor2D motor){
        this(motor.s,0,0,0,motor.yw,motor.wx,motor.xy,0);
    }

    /**
     * Initialize MultiVector from Line2D
     * @param line Line2D
     */
    public MultiVector(Line2D line){
        this(0,line.x,line.y,line.w,0,0,0,0);
    }

    /**
     * Multiply this MultiVector with another
     * @param rhs MultiVector to multiply
     * @return MultiVector, result of this*rhs
     */
    public MultiVector times(MultiVector rhs){
        float _s = s*rhs.s + x*rhs.x + y*rhs.y - xy*rhs.xy;
        float _x = s*rhs.x + x*rhs.s - y*rhs.xy + xy*rhs.y;
        float _y = s*rhs.y + x*rhs.xy + y*rhs.s - xy*rhs.x;
        float _w = s*rhs.w - x*rhs.wx + y*rhs.yw + w*rhs.s - yw*rhs.y + wx*rhs.x - xy*rhs.wxy - wxy*rhs.xy;
        float _yw = s*rhs.yw + x*rhs.wxy + y*rhs.w - w*rhs.y + yw*rhs.s - wx*rhs.xy + xy*rhs.wx + wxy*rhs.x;
        float _wx = s*rhs.wx - x*rhs.w + y*rhs.wxy + w*rhs.x + yw*rhs.xy + wx*rhs.s - xy*rhs.yw + wxy*rhs.y;
        float _xy = s*rhs.xy + x*rhs.y - y*rhs.x + xy*rhs.s;
        float _wxy = s*rhs.wxy + x*rhs.yw + y*rhs.wx + w*rhs.xy + yw*rhs.x + wx*rhs.y + xy*rhs.w + wxy*rhs.s;
        return new MultiVector(_s, _x, _y, _w, _yw, _wx, _xy, _wxy);
    }

    /**
     * Add this MultiVector with another
     * @param rhs MultiVector to add
     * @return MultiVector, result of this+rhs
     */
    public MultiVector add(MultiVector rhs){
        float _s = s+rhs.s;
        float _x = x+rhs.x;
        float _y = y+rhs.y;
        float _w = w+rhs.w;
        float _yw = yw+rhs.yw;
        float _wx = wx+rhs.wx;
        float _xy = xy+rhs.xy;
        float _wxy = wxy+rhs.wxy;
        return new MultiVector(_s, _x, _y, _w, _yw, _wx, _xy, _wxy);
    }

    /**
     * Subtract a MultiVector from this
     * @param rhs MultiVector to subtract
     * @return MultiVector, result of this-rhs
     */
    public MultiVector sub(MultiVector rhs){
        float _s = s-rhs.s;
        float _x = x-rhs.x;
        float _y = y-rhs.y;
        float _w = w-rhs.w;
        float _yw = yw-rhs.yw;
        float _wx = wx-rhs.wx;
        float _xy = xy-rhs.xy;
        float _wxy = wxy-rhs.wxy;
        return new MultiVector(_s, _x, _y, _w, _yw, _wx, _xy, _wxy);
    }

    /**
     * Multiply this MultiVector by a float
     * @param f float
     * @return MultiVector, result of f*this
     */
    public MultiVector mul(float f){
        return new MultiVector(s*f, x*f, y*f, w*f, yw*f, wx*f, xy*f, wxy*f);
    }

    /**
     * Divie this MultiVector by a float
     * @param f float
     * @return MultiVector, result of this/f
     */
    public MultiVector div(float f){
        return new MultiVector(s/f, x/f, y/f, w/f, yw/f, wx/f, xy/f, wxy/f);
    }

    /**
     * Get the wedge of this MultiVector and another
     * @param rhs MultiVector to wedge with
     * @return MultiVector, result of this^rhs
     */
    public MultiVector wedge(MultiVector rhs){
        float _s = s*rhs.s;
        float _x = s*rhs.x + x*rhs.s;
        float _y = s*rhs.y + y*rhs.s;
        float _w = s*rhs.w + w*rhs.s;
        float _yw = s*rhs.yw + y*rhs.w - w*rhs.y + yw*rhs.s;
        float _wx = s*rhs.wx - x*rhs.w + w*rhs.x + wx*rhs.s;
        float _xy = s*rhs.xy + x*rhs.y - y*rhs.x + xy*rhs.s;
        float _wxy = s*rhs.wxy + x*rhs.yw + y*rhs.wx + w*rhs.xy + yw*rhs.x + wx*rhs.y + xy*rhs.w + wxy*rhs.s;
        return new MultiVector(_s, _x, _y, _w, _yw, _wx, _xy, _wxy);
    }

    /**
     * Get the dot product of this MultiVector and another
     * @param rhs MultiVector to dot with
     * @return MultiVector, result of this . rhs
     */
    public MultiVector dot(MultiVector rhs){ // dot(ei,ej) = 1 if i==j, 0 else
        float _s = s*rhs.s + x*rhs.x + y*rhs.y - xy*rhs.xy;
        float _x = s*rhs.x - y*rhs.xy + xy*rhs.y - x*rhs.s;
        float _y = s*rhs.y + x*rhs.xy - xy*rhs.x + y*rhs.s;
        float _w = s*rhs.w - x*rhs.wx + y*rhs.yw + w*rhs.s - yw*rhs.y + wx*rhs.x - xy*rhs.wxy - wxy*rhs.xy;
        float _yw = s*rhs.yw + x*rhs.wxy + yw*rhs.s + wxy*rhs.x;
        float _wx = s*rhs.wx - y*rhs.wxy + wx*rhs.s + wxy*rhs.y;
        float _xy = s*rhs.xy + xy*rhs.s;
        float _wxy = s*rhs.wxy + wxy*rhs.s;
        return new MultiVector(_s, _x, _y, _w, _yw, _wx, _xy, _wxy);
    }

    /**
     * Get the magnitude of this MultiVector
     * @return float, the magnitude of this
     */
    public float magnitude(){
        return (float) Math.sqrt(this.times(this.reverse()).s);
    }

    /**
     * Normalize this MultiVector
     * @return MultiVector, this MultiVector normalized
     */
    public MultiVector normalized(){
        return this.mul(1f/this.magnitude());
    }

    /**
     * Get the vee of this MultiVector and another
     * @param rhs MultiVector to vee with
     * @return MultiVector, result of thisVrhs
     */
    public MultiVector vee(MultiVector rhs){
        return this.dual().wedge(rhs.dual()).dual();
    }

    /**
     * Get the reverse of this MultiVector
     * @return MultiVector, this reversed
     */
    public MultiVector reverse(){
        return new MultiVector(s, x, y, w, -yw, -wx, -xy, -wxy);
    }

    /**
     * Get the dual of this multivector
     * @return MultiVector, this dualed
     */
    public MultiVector dual(){
        return new MultiVector(wxy, yw, wx, xy, x, y, w, s);
    }

    /**
     * Get the transform of this MultiVector and another
     * @param m MultiVector
     * @return MultiVector, transform of this and m
     */
    public MultiVector transform(MultiVector m){
        MultiVector self = this;
        return m.times(self).times(m.reverse());
    }

    /**
     * Wedge to MultiVectors
     * @param lhs left hand side MultiVector
     * @param rhs right hand side MultiVector
     * @return MultiVector, result of lhs^rhs
     */
    public static MultiVector wedge(MultiVector lhs, MultiVector rhs){
        return lhs.wedge(rhs);
    }

    /**
     * Dot two MultiVectors
     * @param lhs left hand side MultiVector
     * @param rhs right hand side MultiVector
     * @return MultiVector, result of lhs . rhs
     */
    public static MultiVector dot(MultiVector lhs, MultiVector rhs){
        return lhs.dot(rhs);
    }

    /**
     * Transform one MultiVector by another
     * @param lhs MultiVector to be transformed
     * @param m MultiVector that will apply transformation
     * @return MultiVector, result of transforming lhs by m
     */
    public static MultiVector transform(MultiVector lhs, MultiVector m){
        return m.times(lhs).times(m.reverse());
    }

}
