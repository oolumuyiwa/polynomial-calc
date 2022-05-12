import java.util.*;
/**
 * PolyCalc details the process of execution of the polynomial calculator.
 *
 * @author Oluwatomiwa Olumuyiwa
 * @version 12.5.2021
 */
public class PolyCalc
{

    /**
     * Requests the user for the operator of the polynomial calculation
     * 
     * @return the operator chosen or null if it is quit
     */
    public static String pickOperator(){
        //Scanner for the method
        Scanner scan = new Scanner(System.in);

        //Request operator, change to uppercase so that it is not case sensitive
        System.out.println("Please choose an operation (+,-,*,/) or 'quit' to exit:");
        String operator = scan.next().toUpperCase();

        //Check if the user has quit
        if(operator.compareTo("QUIT") == 0){
            return null;
        }

        //For as long as the operator is not valid, keep printing an error and requesting a new operator
        while( !(operator.equals("/")) && !(operator.equals("*")) && !(operator.equals("-")) && !(operator.equals("+"))){
            System.out.println("Invalid operator. Only the ones listed are supported");
            System.out.println("Please choose an operation (+,-,*,/) or 'quit' to exit:");

            //Update the operator variable
            operator = scan.next().toUpperCase();

            //Check if the user has quit
            if(operator.compareTo("QUIT") == 0){
                return null;
            }
        }

        return operator;
    }
    
   /**
     * executes the operation passed in as an argument on the polynomials passed in as arguments
     * 
     * @param op the operation to be executed
     * @param p1 the polynomial that will be operated on. This acts as the "this" polynomial. i.e if it's division, it will be p1/p2
     * @param p2 the second polynomial to be operated on
     * @return the resultant polynomial of the operation
     */
    static Poly handleOperation(String operate, Poly p1, Poly p2){
        //this polynomial will hold our result
        Poly result = new Poly();

        //Use a switch loop for each valid string case

        switch(operate){
            case "-":
            result = p1.subtract(p2);
            break;

            case "+":
            result = p1.add(p2);
            break;

            case "/":
            result = p1.divide(p2);
            break;

            case "*":
            result = p1.multiply(p2);
            break; 

        }

        //return the result
        return result;

    }
    
    

    /**
     * Requests the user for a polynomial in the appropriate format and constructs the polynomial
     * 
     * @param entry the input message for the particular polynomial i.e helps distinguish polynomial a from polynomial b
     * @return the polynomial with the coefficients and exponents specified. Null if the user quits
     */
    public static Poly enterPolynomial(String message){
        //Polynomial to hold our final result
        Poly result = new Poly();
        //This integer will help us determine when to break from the loop
        int x = 0;
        while(true){
            System.out.println(message);

            //Reset the result at the start of each loop
            result = new Poly();

            //Scanner for the user's input
            Scanner scan = new Scanner(System.in);
            String sinput = scan.nextLine().toUpperCase(); 

            //If the user quits then return null
            if(sinput.equals("QUIT")){
                return null;
            }
            //Scanner to walk through the user's input
            Scanner sinputScanner = new Scanner(sinput); 

            //Loop through the lineScanner for as long as it has elements
            while(sinputScanner.hasNext()){
                //Catch the possible exceptions from user inputs
                try{

                    double cfc =  sinputScanner.nextDouble();
                    int expo = sinputScanner.nextInt();
                    Poly p = new Poly(cfc,expo);
                    result = result.add(p);
                    x = 0;
                }
                //Case where the cfc isn't a double or the expo is not an integer. 
                catch(InputMismatchException e){
                    System.out.println("Invalid polynomial input! Please refer to the instructions given.");
                    x = -2;
                    break;
                }
                //Case where the user hasn't given enough elements.
                catch(NoSuchElementException e){
                    System.out.println("Input enough values for evaluation mi amigo.");
                    x = -2;
                    break;
                }
                
            }

            //If i is equal to zero, it means we broke from the inner loop with a valid polynomial hence we can break from the outer loop
            if(x == 0){
                break;
            }
        }

        //Return the polynomial
        return result;
    }

    public static void main(String[] args){
        //Print the welcome message and a few instructions concerning the calculator
        System.out.println("Hola amigo!");
        System.out.println("Welcome to Tomiwa's polynomial calculator!");
        System.out.println("It's pretty cool, and there are just a couple rules to follow my friend:");
        System.out.println("Polynomials are explicitly input by space-seperated values of coefficient and exponent");
        System.out.println("E.g.," + "'2.5x^2 - 1'" + "should be input as" + "'2.5 2 -1 0'" + ".");
        System.out.println("Enter 'quit' whenever you feel like exiting the program lol");

        //This will just be used to keep track of the number of computations
        int i = 1;
        while(true){
            //Space out the lines
            System.out.println();

            System.out.println("--- Computation #" + i + " ---");
            i++;

            //Obtain the first polynomial
            Poly a = enterPolynomial("Input the first polynomial according to instructions given above ('quit' to exit), Polynomial a:");

            //If the user quits, end the computation
            if(a == null){
                break;
            }

            //Print out the textual representation of the polynomial
            System.out.println("Polynomial a is read as:");
            System.out.println("a = " + a.toString());

            //Space out the lines
            System.out.println();

            //Request for operator
            String operator = pickOperator();

            //If the user quits, end the computation
            if(operator == null){
                break;
            }

            //Space out the lines 
            System.out.println();

            //Obtain the second polynomial
            Poly b = enterPolynomial("Input polynomial according to instructions given above ('quit' to exit), Polynomial b:");

            //If the user quits, end the computation
            if(b == null){
                break;
            }

            //Print out the textual representation of the polynomial
            System.out.println("Polynomial b is read as:");
            System.out.println("b = " + b.toString());

            //Space out the lines
            System.out.println();
            
            Poly result = handleOperation(operator,a,b);

            //Print out the details of the entire operation
            if(result == null){
                System.out.println(a + " " + operator + " " + b + " = indivisible");
            }
            else{
                System.out.println(a + " " + operator + " " + b + " = " + result);
            }
            
        }

        System.out.println("Good luck my friend, or as we say in Spanish: buena suerte!");

        }
        
       
       
}
