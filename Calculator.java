// Name: Daniel Paek
// Computing ID: jff7dm@virginia.edu
// Homework Name: HW 2 - Calculator
// Resources used: None
public class Calculator {
    //stores the first operand
    private int mem1;
    //stores the second operand which will be updated after the perform() method
    private int mem2;
    //stores the operator ("+", "-", "*", "/", "^") or " " as default
    private String op;

    //no-argument constructor that initializes mem1 and mem2 to 0 and op to a space
    public Calculator() {
        mem1 = 0;
        mem2 = 0;
        op = " ";
    }

    //overloaded constructor that initializes mem1, mem2, and op
    //if newOp isn't a valid operator op will be set to a space
    public Calculator(int op1, int op2, String newOp) {
        mem1 = op1;
        mem2 = op2;
        if (newOp.equals("+") || newOp.equals("-") || newOp.equals("*")
                || newOp.equals("/") || newOp.equals("^")) {
            op = newOp;
        } else {
            op = " ";
        }
    }

    //accessor for mem1 that returns the value of mem1
    public int getMem1() {
        return mem1;
    }
    //accessor for mem2 that returns the value of mem2
    public int getMem2() {
        return mem2;
    }
    //accessor for op that returns the operator as string
    public String getOp() {
        return op;
    }

    /*mutator for mem1 that sets it to whatever value gets passed in
    the parameter being op1, the new value for mem1
     */
    public void setMem1(int op1) {
        mem1 = op1;
    }
    /*mutator for mem2 that sets it to whatever value gets passed in
    the parameter being op2, the new value for mem2
     */
    public void setMem2(int op2) {
        mem2 = op2;
    }
    /*mutator for op that first checks for valid operators +-/*^
    if the input is invalid, op does not change
    if the input is valid, mutator sets op it to whatever operator gets
    passed in, the parameter being newOp, the new operator
    */
    public void setOp(String newOp) {
        if (newOp.equals("+") || newOp.equals("-") || newOp.equals("*")
                || newOp.equals("/") || newOp.equals("^")) {
            op = newOp;
        }
    }

    //returns a string in the format of "mem1 op mem2"
    public String toString() {
        return mem1 + " " + op + " " + mem2;
    }

    /*performs the operation stored by using mem1 as the first operand
    and mem2 as the second operand, returning the result of the operation
    as an int that is also assigned to mem2.
    if the user tries to divide by zero, perform() returns 0 and mem2
    becomes 0
    used .equals() for String comparisons
     */
    public int perform() {
        int result;
        if (op.equals("+")) {
            result = mem1 + mem2;
        }
        else if (op.equals("-")) {
            result = mem1 - mem2;
        }
        else if (op.equals("*")) {
            result = mem1 * mem2;
        }
        else if (op.equals("/")) {
            //if division attempted with mem2 being 0
            if (mem2 == 0) {
                result = 0;
            }
            else {
                //integer division
                result = mem1 / mem2;
            }
        }
        else if (op.equals("^")) {
            //math.pow here would turn into double so cast to int
            result = (int) (Math.pow(mem1, mem2));
        }
        else {
            //if op is a space or an unknown value
            result = 0;
        }
        //assigning the result of the operation to mem2
        mem2 = result;
        return result;
    }

    /*Performs division using double arithmetic, returning the result
    as a double or 0.0 if it's invalid to divide
    only works if op is "/" and mem2 is not 0
    does not change the value of mem2
     */
    public double performDiv(){
        if (op.equals("/") && (mem2 != 0)) {
            return (double)mem1 / (double)mem2;
        }
        return 0.0;
    }

    //Swaps the values of mem1 and mem2
    public void swap() {
        int temp = mem1;
        mem1 = mem2;
        mem2 = temp;
    }
}
