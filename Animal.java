public abstract class Animal  // class is abstract
{
    private String name;
    public Animal(String nm)      // constructor method
    { name=nm; }
    public String getName()       // accessor
    { return (name); }
    public abstract void speak(); // abstract method - note no {}
    public String toString() { return "My name is " + name;}  // override inherited version
}
