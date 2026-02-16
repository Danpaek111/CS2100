public void main(){
    Calculator c = new Calculator();
    Calculator c2 = new Calculator(1, 2, "+");
    System.out.println(c);
    System.out.println(c2);

    c.setMem1(5);
    c.setMem2(7);
    c.setOp("+");
    System.out.println(c);
    System.out.println(c.perform());

    c.swap();
    c.setOp("-");
    System.out.println(c);
    System.out.println(c.perform());

    c.setOp("*");
    System.out.println(c.perform());

    c.swap();
    c.setOp("/");
    System.out.println(c);
    System.out.println(c.perform());

    System.out.println(c);
    System.out.println("mem2 is " + c.getMem2());

    System.out.println(c.performDiv());

    c.setMem2(0);
    System.out.println(c.perform());
    System.out.println(c);
}

