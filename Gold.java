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
public class Gold extends Status{
    public Gold(int Points){
        super(Points);
    }
    public void addPoints(Customer c, int Points){
        super.points += Points;
    }
     public void removePoints(Customer c, int Points){
        super.points -= Points;
        if( super.points < 1000){
           c.setStatusObject(new Silver(super.points));
        }
    }
    public String toString(){
        return "Gold";
    }
}
