// Name: Daniel Paek
// Computing ID: jff7dm@virginia.edu
// HW Name: HW 3 - Basketball
// Resources used: N/A
public class Team {
    private Player[] team;

    //constructor only one parameter, array
    public Team(Player[] players){
        team = new Player[players.length];
    for (int i = 0; i < players.length; i++) {
        Player p = players[i];
        team[i] = new Player(p.getName(), p.getPosition(), p.getPPGame(),
                p.getFGPercentage(), p.getFTPercentage(), p.get3PtPercentage());
        }
    }

    //getter
    public Player[] getTeam(){
        Player[] copy = new Player[team.length];
        for (int i = 0; i < team.length; i++){
            Player p = team[i];
            copy[i] = new Player(p.getName(), p.getPosition(), p.getPPGame(),
                    p.getFGPercentage(), p.getFTPercentage(), p.get3PtPercentage());
        }
        return copy;
    }
    //setter
    public void setTeam(Player[] players){
        team = new Player[players.length];
        for (int i = 0; i < players.length; i++){
            Player p = players[i];
            team[i] = new Player(p.getName(), p.getPosition(), p.getPPGame(),
                    p.getFGPercentage(), p.getFTPercentage(), p.get3PtPercentage());
        }
    }

    public boolean equals(Object other){
        if (this == other) return true;
        Team o = (Team) other;
        if (team.length != o.team.length){
            return false;
        }
        for (int i = 0; i < team.length; i++){
            if (!team[i].equals(o.team[i])){
                return false;
            }
        }
        return true;
    }

    public double FGAverage(){
        double sum = 0.0;
        for (int i = 0; i < team.length; i++) {
            sum += team[i].getFGPercentage();
        }
        return sum / team.length;
    }

    //count players at given position
    public int positionCount(String pos){
        String target = pos.trim().toLowerCase();
        if (target.equals("c")){
            target = "center";
        }
        else if (target.equals("g")){
            target = "guard";
        }
        else if (target.equals("f")){
            target = "forward";
        }
        int count = 0;
        for (int i = 0; i < team.length; i++) {
            String p = team[i].getPosition().trim().toLowerCase();
            if (p.equals("c")){
                p = "center";
            }
            else if (p.equals("g")){
                p = "guard";
            }
            else if (p.equals("f")){
                p = "forward";
            }
            if (p.equals(target)){
                count++;
            }
        }
        return count;
    }

    public Player[] sort(){
        // deep copy of team (array + players)
        Player[] copy = new Player[team.length];
        for (int i = 0; i < team.length; i++){
            Player p = team[i];
            copy[i] = new Player(p.getName(), p.getPosition(), p.getPPGame(),
                    p.getFGPercentage(), p.getFTPercentage(), p.get3PtPercentage());
        }
        //selection sort copy
        for (int i = 0; i < copy.length - 1; i++) {
            int best = i;
            for (int j = i + 1; j < copy.length; j++) {
                if (copy[j].getPPGame() > copy[best].getPPGame()) {
                    best = j;
                }
                else if (copy[j].getPPGame() == copy[best].getPPGame()) {
                    if (copy[j].getName().compareTo(copy[best].getName()) < 0) {
                        best = j;
                    }
                }
            }
            Player temp = copy[i];
            copy[i] = copy[best];
            copy[best] = temp;
            //copy of the sorted team
        }
        return copy;
    }

    public boolean onTeam(String name){
        for (int i = 0; i < team.length; i++){
            if (team[i].getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public Player best3pt(){
        Player best = team[0];
        for (int i = 1; i < team.length; i++){
            if (team[i].get3PtPercentage() > best.get3PtPercentage()){
                best = team[i];
            }
        }
        return best;
    }
    public String toString(){
        String s = "";
        for (int i = 0; i < team.length; i++){
            s += team[i].toString();
            if (i < team.length - 1) s += "\n"; //one player per line
        }
        return s;
    }
}
