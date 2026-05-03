public class Dog extends Animal{
    private String breed;
    public Dog(){
        super();
        this.breed = "";
    }
    public Dog(int age, String breed){
        super(age);
        this.breed = breed;
    }

    public String toString(){
        return super.toString() + ", breed: " + breed;
    }

    public boolean equals(Object o){
        if(!(o instanceof Dog)){
            return false;
        }
        Dog other = (Dog)o;
        return super.equals(other) && this.breed.equalsIgnoreCase(other.breed);
    }
}
