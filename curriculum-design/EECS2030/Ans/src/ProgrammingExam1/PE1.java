package ProgrammingExam1;
import java.awt.Color;
import java.util.ArrayList;

/* PLEASE DO NOT MODIFY A SINGLE STATEMENT IN THE TEXT BELOW.
READ THE FOLLOWING CAREFULLY AND FILL IN THE GAPS

I hereby declare that all the work that was required to
solve the following problem including designing the algorithms
and writing the code below, is solely my own and that I received
no help in creating this solution and I have not discussed my solution
with anybody. I affirm that I have read and understood
the Senate Policy on Academic honesty at
https://secretariat-policies.info.yorku.ca/policies/academic-honesty-senate-policy-on/
and I am well aware of the seriousness of the matter and the penalties that I will face as a
result of committing plagiarism in this assignment.

BY FILLING THE GAPS,YOU ARE SIGNING THE ABOVE STATEMENTS.

Full Name:
Student Number:
Course Section:
*/

public class  PE1 {
    public static void main(String[] args) {

        /* A canvas should be created for drawing.
         * All the shapes should be drawn on the canvas.
         * The bottom left coordinate of the canvas is (0,0)
         * The top right coordinates of the canvas is (1, 1)
         * The input parameter to Draw() constructor is the
         * title of the canvas.
         */
        Draw blankCanvas = new Draw("Programmign Exam 1");

        /* To draw a point, point function is called with proper parameters:
         * point(x_coordinate_of_point, y_coordinate_of_point)
         */
        blankCanvas.point(0.7, 0.7);

        /* To draw a circle, circle function is called with proper parameters:
         * circle(x_coordinate_of_center, y_coordinate_of_center, radius)
         */
        blankCanvas.circle(0.5, 0.5, 0.5);

        /* To draw a square, square function is called with proper parameters:
         * square(x_coordinate_of_center, y_coordinate_center, sides_half_length)
         */
        blankCanvas.square(0.5,  0.5, 0.4);

        /*
         * To change the color of the pen, setPenColor is used with three numbers that are in [0, 255] range.
         * All the colors can be made as a combination of red, green and blue.
         * The parameters show the richness of red, green and blue of a color.
         * For example setPenColor(new Color(0, 0, 0) sets the color of the pen
         * to black and setPenColor(new Color(255, 255, 255) sets the color to
         * white.
         */
        blankCanvas.setPenColor(new Color(150, 150, 150));

        /* To draw a line, line function is called
         * with proper parameters:
         * line(x_coordinate_of_center, y_coordinate_center, sides_half_length)
         */
        blankCanvas.line(0.0, 0.5, 1, 0.5);

    }


    /**
     * This method draws a number of circles that share the same center, as long as the radius is positive.
     * @param x is the x-coordinate of the circles
     * @param y is the y-coordinate of the circles.
     * @param radius is the radius of a circle.
     * 		The function is called with the radius that is cut to two decimal points.
     * 		For example 0.39876543210 must be cut to 0.39
     * @param diff is the difference between the radius of a circle and its immediate inner circle.
     * @param page is the canvas on which the circles are drawn.
     * @param radiusList is an accumulated list of the radius of the circles that were drawn.
     * @return a list of all the circles' radius that were drawn.
     */
    public static String nestedCircle (double x, double y, double radius, double diff,  Draw page, String radiusList) {
        if(radius<0) {
            return radiusList;
        }
        else{
            int r = (int)(radius*100);
            radius = ((double)r/100);
            page.circle(x,y,radius);
            if(radiusList==""){
                radiusList+="["+radius;
            }else{
                radiusList+=", "+radius;
            }
            if(radius<diff){
                radiusList+="]";
            }
            return nestedCircle(x,y,radius-diff,diff,page,radiusList);

        }

    }
    /**
     * This method recursively draws 4 squares, whose center falls on a corner of
     * previously drawn square. The side of the square is half as much as the side of the
     * square that is drawn in previous round.
     * @param x is the x-coordinate of the square
     * @param y is the y-coordinate of the square
     * @param halfLength is half the size of the square's side
     * @param order is the number of the rounds by which a set of squares is drawn
     * @param page is the canvas on which the squares are drawn.
     * @return a list of the center of smallest squares that are drawn..
     * 		The coordinates should be cut to one decimal point. For example:
     * 		 0.39876543210 is cut to 0.3
     */
    public static String squares (double x, double y, double halfLength, int order, Draw page) {

        page.square(x,y,halfLength);
        //临界条件
        if(order==1){
            int tmp= (int)(x*10);
            x = ((double)tmp/10);
            tmp= (int)(y*10);
            y = ((double)tmp/10);
            return "["+x+", "+y+"]";
        }else{
            return squares(x+halfLength,y+halfLength,halfLength/2,order-1,page)
                    +squares(x+halfLength,y-halfLength,halfLength/2,order-1,page)
                    +squares(x-halfLength,y+halfLength,halfLength/2,order-1,page)
                    +squares(x-halfLength,y-halfLength,halfLength/2,order-1,page);
        }
    }
    /**
     * This method specifies which coordinates should be drilled. It also draw the
     * horizontal line of each triangle. No duplicate point should be added to the output.
     * @param p1 is one of the vertex of the triangle
     * @param p2 is the second vertex of the triangle
     * @param p3 is the third vertex of the triangle
     * @param order is the number of times a nested triangle should be drawn.
     * 			<code> order >= 0 </code>, however if it is zero, nothing should be drawn
     * @param page is the canvas on which this method draws.
     * @param array is the list of the points that should be drilled. To add to this list point.toString() must be added.
     * @return an array that contains all the points that should be drilled. this method should not have any duplicate points in it.
     */
    public static ArrayList<String> drillPoints(Point p1, Point p2, Point p3, int order, Draw page, ArrayList<String> array) {
        if(order==0) return array;

        page.point(p1.x,p1.y);
        if(!array.contains(p1.toString())) array.add(p1.toString());
        page.point(p2.x,p2.y);
        if(!array.contains(p2.toString())) array.add(p2.toString());
        page.point(p3.x,p3.y);
        if(!array.contains(p3.toString())) array.add(p3.toString());
        page.line(p2.x,p2.y,p3.x,p3.y);

        if(order==1) return array;

        Point p12 = new Point((p1.x+p2.x)/2,(p1.y+p2.y)/2);
        if(!array.contains(p12.toString())) array.add(p12.toString());
        Point p23 = new Point((p2.x+p3.x)/2,(p2.y+p3.y)/2);
        if(!array.contains(p23.toString())) array.add(p23.toString());
        Point p13 = new Point((p1.x+p3.x)/2,(p1.y+p3.y)/2);
        if(!array.contains(p13.toString())) array.add(p13.toString());

        array.addAll(drillPoints(p1,p12,p12,order-1,page,array));
        array.addAll(drillPoints(p2,p12,p23,order-1,page,array));
        array.addAll(drillPoints(p3,p13,p23,order-1,page,array));

        return array;
    }

}
/**
 * This class creates a point.
 *
 */
class Point {
    double x;
    double y;
    /**
     * This is the constructor that builds a point
     * @param x is the x-coordinate of the point
     * @param y is the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
        // your code goes here. Task 0
    }
    /**
     * This method returns the mid point of a line,
     * whose two ends are given.
     * @param p1 is one end of the line
     * @param p2 is the other end of the line
     * @return the mid point of the line. Both the
     * coordinates are cut to two decimal points.
     * e.g. 0.37654 is cut to 0.37
     */
    public static Point midpoint(Point p1, Point p2) {
        int x = (int)((p1.x+p2.x)/2*100);
        int y = (int)((p1.y+p2.y)/2*100);
        // your code goes here. Task 0
        return new Point((double)(x)/100, (double)(y)/100);
    }
    @Override
    /**
     * This method returns the coordinate of this object as a string.
     */
    public String toString() {
        return "["+this.x + ", "+ this.y +"]";
    }

}
