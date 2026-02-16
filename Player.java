// Name: Daniel Paek
// Computing ID: jff7dm@virginia.edu
// HW Name: HW 3 - Basketball
// Resources used: N/A
public class Player {
    private String name;
    private String position; //would store either "center", "guard", or "forward"
    private double points_per_game;
    private double field_goal_percentage;
    private double free_throw_percentage;
    private double three_pt_percentage;

    //No argument constructor
    public Player(){
        this.name = "";
        this.position = "guard"; //just as a default position
        this.points_per_game = 0.0;
        this.field_goal_percentage = 0.0;
        this.free_throw_percentage = 0.0;
        this.three_pt_percentage = 0.0;
    }
    //overloaded constructor
    public Player(String name, String position, double points_per_game, double field_goal_percentage, double free_throw_percentage, double three_pt_percentage){
        this.name = name;
        this.position = position;
        this.points_per_game = points_per_game;
        this.field_goal_percentage = field_goal_percentage;
        this.free_throw_percentage = free_throw_percentage;
        this.three_pt_percentage = three_pt_percentage;
    }
    //getters
    public String getName(){
        return this.name;
    }
    public String getPosition(){
        return this.position;
    }
    public double getPPGame(){
        return this.points_per_game;
    }
    public double getFGPercentage(){
        return this.field_goal_percentage;
    }
    public double getFTPercentage(){
        return this.free_throw_percentage;
    }
    public double get3PtPercentage(){
        return this.three_pt_percentage;
    }

    //setters
    public void setName(String name){
        this.name = name;
    }
    public void setPosition(String position){
        String p = position.trim();
        if (p.equals("center") || p.equals("guard") || p.equals("forward")) {
            this.position = p;
        }
        if (p.equals("c")) {
            this.position = "center";
        }
        if (p.equals("g")) {
            this.position = "guard";
        }
        if (p.equals("f")) {
            this.position = "forward";
        }
    }
    //returns true if name, position, fg%, and ft% match
    public boolean equals(Object o){
        Player other = (Player) o;
        return this.name.equals(other.name) &&
                this.position.equals(other.position) &&
                this.field_goal_percentage == other.field_goal_percentage &&
                this.free_throw_percentage == other.free_throw_percentage;
    }
    //toString method
    public String toString(){
        return "Player: " + name + ", position: " + position + ", ppg: " + points_per_game
                + ", fg%: " + field_goal_percentage + ", ft%: " + free_throw_percentage
                + ", 3pt%: " + three_pt_percentage;
    }
}
