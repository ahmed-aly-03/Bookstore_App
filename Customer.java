package bookstore;

//@author cjplett

public class Customer {
    private String username;
    private String password;
    private Status status;
    public Customer(String name, String password, int points){
        this.username = name;
        this.password = password;
        status = new Silver(points);
    }
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public String getPointsString(){
        return ""+ status.getPoints();
    }
    public int getPoints(){
        return this.status.getPoints();
    }
    public String getStatus(){
        return status.toString();
    }
    public void setStatusObject(Status s){
        this.status = s;
    }
    public void addPoints(int morePoints){
        status.addPoints(this, morePoints);
    }
    public void removePoints(int morePoints){
        status.removePoints(this, morePoints);
    }
}
