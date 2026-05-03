public class Animal {
    private int age;
    public Animal(){
        age = 0;
    }
    public Animal(int age){
        this.age = age;
    }
    public int getAge(){
        return this.age;
    }
    public void setAge(int age){
        this.age = age;
    }
    public String toString(){
        return "age: " + age;
    }
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null){
            return false;
        }
        Animal other = (Animal)o;
        return age == other.age;
    }
}
