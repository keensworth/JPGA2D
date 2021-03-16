public class Geom {

    /**
     * Displace a point in some direction
     * @param p initial point
     * @param d direction to displace
     * @return a Point2D, displaced in the direction d
     */
    public static Point2D move(Point2D p, Dir2D d){
        return new Point2D(p.x()+d.x(),p.y()+d.y());
    }
    

    /**
     * Compute the displacement between two points
     * @param p1 first point
     * @param p2 second point
     * @return Dir2D, the vector displacement between p1 and p2
     */
    public static Dir2D displacement(Point2D p1, Point2D p2){
        return new Dir2D(p2.x()-p1.x(),p2.y()-p1.y());
    }
    

    /**
     * Compute the distance between two points
     * @param p1 first point
     * @param p2 second point
     * @return float, the scalar distance between p1 and p2
     */
    public static float dist(Point2D p1, Point2D p2){
        return PGA.vee(p1, p2).magnitude();
    }
    
    
    /**
     * Compute perpindicular distance from a point to a line
     * @param l a line
     * @param p a point
     * @return float, the scalar perpendicular distance from point p to line l
     */
    public static float dist(Line2D l, Point2D p){
        return PGA.vee(l.normalized(), p);
    }
    
    public static float dist(Point2D p, Line2D l){
        return PGA.vee(p, l.normalized());
    }
    

    /**
     * Compute intersection point between two lines
     * @param l1 first line
     * @param l2 second line
     * @return Point2D, the intersection point between l1 and l2
     */
    public static Point2D intersect(Line2D l1, Line2D l2){
        return PGA.wedge(l1, l2);
    }


    /**
     * Compute the line that goes through two points
     * @param p1 first point
     * @param p2 second point
     * @return Line2D, the line that goes through p1 and p2
     */
    public static Line2D join(Point2D p1, Point2D p2){
        return PGA.vee(p1, p2);
    }

    
    /**
     * Compute the projection of a point onto a line
     * @param p a point
     * @param l a line
     * @return Point2D, the result of projecting p onto l
     */
    public static Point2D project(Point2D p, Line2D l){
        return new Point2D(PGA.dot(l, p).times(l));
    }
    
    public static Line2D project(Line2D l, Point2D p){
        return new Line2D(PGA.dot(l, p).times(p));
    }
    

    /**
     * Compute the angle between two lines
     * @param l1 first line
     * @param l2 second line
     * @return float, scalar angle between l1 and l2
     */
    public static float angle(Line2D l1, Line2D l2){
        return (float)Math.asin((PGA.wedge(l2, l1)).normalized().magnitude());
    }

    public static float angleC(Line2D l1, Line2D l2){
        return (float)Math.asin((PGA.dot(l2, l1)));
    }


    /**
     * Compute the cross product between two lines
     * @param l1 first line
     * @param l2 second lone
     * @return float, the result of the cross product between two lines 
     */
    public static float cross(Line2D l1, Line2D l2){
        return (l1.x*l2.y - l1.y*l2.x);
    }
    

    /**
     * //Check if the line segment p1->p2 intersects the line segment a->b
     * @param p1 point 1 of first segment
     * @param p2 point 2 of first segment
     * @param a point 1 of second segment
     * @param b point 2 of second segment
     * @return boolean, whether or not the segments formed by the above points intersect each other
     */
    public static boolean segmentSegmentIntersect(Point2D p1, Point2D p2, Point2D a, Point2D b){
        Line2D line1 = PGA.vee(p1, p2);
        Line2D line2 = PGA.vee(a, b);

        boolean line1OppositeSides = Math.signum(PGA.vee(line1,a)) != Math.signum(PGA.vee(line1,b));
        boolean line2OppositeSides = Math.signum(PGA.vee(line2,p1)) != Math.signum(PGA.vee(line2,p2));

        return line1OppositeSides && line2OppositeSides;
    }
    

    /**
     * Check if a point is inside of a triangle formed by three points
     * @param p the point of interest
     * @param t1 triangle point 1
     * @param t2 triangle point 2
     * @param t3 triangle point 3
     * @return boolean, whether or not p lies in the triangle formed by t1->t2->t3->t1 (arbitrary orientation)
     */
    public static boolean pointInTriangle(Point2D p, Point2D t1, Point2D t2, Point2D t3){
        Line2D l1 = PGA.vee(t1,t2);
        Line2D l2 = PGA.vee(t2,t3);
        Line2D l3 = PGA.vee(t3,t1);

        Line2D lr1 = PGA.vee(t1,t3);
        Line2D lr2 = PGA.vee(t3,t2);
        Line2D lr3 = PGA.vee(t2,t1);
        float s1 = Math.signum(PGA.vee(l1,p));
        float s2 = Math.signum(PGA.vee(l2,p));
        float s3 = Math.signum(PGA.vee(l3,p));
        float s4 = Math.signum(PGA.vee(lr1,p));
        float s5 = Math.signum(PGA.vee(lr2,p));
        float s6 = Math.signum(PGA.vee(lr3,p));

        boolean consistent = (s1==s2 && s2==s3) || (s4==s5 && s5==s6);

        return consistent;
    }

    
    /**
     * Check if a point is inside of a quad formed by four points
     * @param p the point of interest
     * @param q1 quad point 1
     * @param q2 quad point 2
     * @param q3 quad point 3
     * @param q4 quad point 4
     * @return boolean, whether or not p lies in the quad formed by q1->q2->q3->q4->q1 (arbitrary orientation)
     */
    public static boolean pointInQuad(Point2D p, Point2D q1, Point2D q2, Point2D q3, Point2D q4){
        Line2D l1 = PGA.vee(q1,q2);
        Line2D l2 = PGA.vee(q2,q3);
        Line2D l3 = PGA.vee(q3,q4);
        Line2D l4 = PGA.vee(q4,q1);

        Line2D lr1 = PGA.vee(q1,q4);
        Line2D lr2 = PGA.vee(q4,q3);
        Line2D lr3 = PGA.vee(q3,q2);
        Line2D lr4 = PGA.vee(q2,q1);

        float s1 = Math.signum(PGA.vee(l1,p));
        float s2 = Math.signum(PGA.vee(l2,p));
        float s3 = Math.signum(PGA.vee(l3,p));
        float s4 = Math.signum(PGA.vee(l4,p));
        float s5 = Math.signum(PGA.vee(lr1,p));
        float s6 = Math.signum(PGA.vee(lr2,p));
        float s7 = Math.signum(PGA.vee(lr3,p));
        float s8 = Math.signum(PGA.vee(lr4,p));

        return (s1==s2 && s2==s3 && s3==s4) || (s5==s6 && s6==s7 && s7==s8);
    }

    /**
     * Compute the area of a triangle formed by three points
     * @param t1 triangle point 1
     * @param t2 triangle point 2
     * @param t3 triangle point 3
     * @return
     */
    public static float areaTriangle(Point2D t1, Point2D t2, Point2D t3){
        return 0.5f*(PGA.vee(PGA.vee(t2, t3), t1));
    }

    /**
     * Compute the distance from a point to the edge of a triangle
     * @param p point of interest
     * @param t1 triangle point 1
     * @param t2 triangle point 2
     * @param t3 triangle point 3
     * @return float, distance from p to an edge of the triangle formed by t1->t2->t3->t1
     */
    public static float pointTriangleEdgeDist(Point2D p, Point2D t1, Point2D t2, Point2D t3){
        Line2D l1 = PGA.vee(t1,t2);
        Line2D l2 = PGA.vee(t2,t3);
        Line2D l3 = PGA.vee(t3,t1);

        float dist1 = Math.abs(dist(l1,p));
        float dist2 = Math.abs(dist(l2,p));
        float dist3 = Math.abs(dist(l3,p));

        float min = dist1;
        if (dist2 < min){
            min = dist2;
        } else if (dist3 < min){
            min = dist3;
        }

        return min;
    }

    /**
     * Compute the distance from a point to the corner of a triangle
     * @param p point of interest
     * @param t1 triangle point 1
     * @param t2 triangle point 2
     * @param t3 triangle point 3
     * @return float, distance from p to a corner of the triangle formed by t1->t2->t3->t1
     */
    public static float pointTriangleCornerDist(Point2D p, Point2D t1, Point2D t2, Point2D t3){
        float dist1 = dist(p,t1);
        float dist2 = dist(p,t2);
        float dist3 = dist(p,t3);

        float min = dist1;
        if (dist2 < min){
            min = dist2;
        } else if (dist3 < min){
            min = dist3;
        }

        return min;
    }

    /**
     * Check if a quad is convex
     * @param p1 quad point 1
     * @param p2 quad point 2
     * @param p3 quad point 3
     * @param p4 quad point 4
     * @return
     */
    public static boolean isQuadConvex(Point2D p1, Point2D p2, Point2D p3, Point2D p4){
        Line2D l1 = PGA.vee(p1,p2);
        Line2D l2 = PGA.vee(p2,p3);
        Line2D l3 = PGA.vee(p3,p4);
        Line2D l4 = PGA.vee(p4,p1);

        float angle1 = PGA.dot(l1,l2);
        float angle2 = PGA.dot(l2,l3);
        float angle3 = PGA.dot(l3,l4);
        float angle4 = PGA.dot(l4,l1);

        float s1 = Math.signum(angle1);
        float s2 = Math.signum(angle2);
        float s3 = Math.signum(angle3);
        float s4 = Math.signum(angle4);

        return (s1*s2*s3*s4 > 0);
    }

    /**
     * Reflect a point around a line
     * @param p point to be reflected
     * @param l axis line
     * @return Point2D, the resulting point of reflecting p across l
     */
    public static Point2D reflect(Point2D p, Line2D l){
        return (Point2D) l.times(p).times(l).mul(-1);
    }

    /**
     * Reflect a line around another line
     * @param d line to be reflected
     * @param l axis line
     * @return Line2D, the resulting line formed by reflecting d across l
     */
    public static Line2D reflect(Line2D d, Line2D l){
        return (Line2D) l.times(d).times(l).mul(-1);
    }
}
