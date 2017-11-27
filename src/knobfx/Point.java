/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knobfx;

/**
 *
 * @author bujaz
 */
public class Point {
    private double _x;
    private double _y;
    
    public Point(double x, double y) {
        this._x = x;
        this._y = y;
    }
    
    public void setX(double value){
        this._x = value;
    }
    public void setY(double value){
        this._y = value;
    }
    public double getX(){
        return this._x;
    }
    public double getY(){
        return this._y;
    }
}
