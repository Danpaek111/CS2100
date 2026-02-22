public class Snake extends Animal
{
    public Snake(String nm)  {
        super(nm);    // builds ala parent
    }
    public void speak()  { // this method specific to Snake
        System.out.println("Ssssssssss");
    }
}
