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
public class Silver extends Status{
    public Silver(int Points){
        super(Points);
    }
    public void addPoints(Customer c, int Points){
        super.points += Points;
        if( super.points >= 1000){
           c.setStatusObject(new Gold(super.points));
        }
    }
    public void removePoints(Customer c, int Points){
        if(super.points < Points){
            super.points = 0;
        }else{
            super.points -= Points;
        }
    }
    public String toString(){
        return "Silver";
    }
}
