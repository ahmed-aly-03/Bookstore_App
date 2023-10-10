/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstore;

/**
 *
 * @author Philip
 */
public abstract class Status {
    protected int points;
    public Status(int points){
        this.points = points;
    }
    public abstract void addPoints(Customer c, int Points);
    public abstract void removePoints(Customer c, int Points);
    public int getPoints(){
        return points;
    }
}
