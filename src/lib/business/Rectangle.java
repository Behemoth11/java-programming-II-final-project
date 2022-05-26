/**
 * Murach, J. ( 2017).  Murachs Java Programming, Training and
 * Reference,  5th Edition, Fresno, CA: Mike Murach & Associates, Inc.
 * Modifications by W. Bowers, 2018
 */

package lib.business;

import java.text.NumberFormat;

public class Rectangle {
    
    private double length;
    private double width;
    
    public Rectangle() {
        length = 0;
        width = 0;
    }

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }
    
    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getArea() {
        double area = width * length;
        return area;
    }
    
    public String getAreaNumberFormat() {
        NumberFormat number = NumberFormat.getNumberInstance();
        number.setMinimumFractionDigits(3);
        String numberFormatted = number.format(getArea());
        return numberFormatted;        
    }
    
    public double getPerimeter() {
        double perimeter = 2 * width + 2 * length;
        return perimeter;
    }
    
    public String getPerimeterNumberFormat() {
        NumberFormat number = NumberFormat.getNumberInstance();
        number.setMinimumFractionDigits(3);
        String numberFormatted = number.format(getPerimeter());
        return numberFormatted;        
    }        
}