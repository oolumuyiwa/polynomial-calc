
/**
 * Write a description of class Monomial here.
 * A class representing monomials 
 * @author Oluwatomiwa Olumuyiwa
 *
 * @version 20th November 2021
 */
public class Monomial
{
    // instance variables - replace the example below with your own
    double cfc;
    int expo;

    /**
     * Constructor for objects of class Monomial
     */
    public Monomial(double cfc, int expo)
    {
        // initialise instance variables
        this.cfc = cfc;
        this.expo = expo;
    }
    
    /**
     * Empty constructor for the Mono class
     */
    public Monomial(){
    }

    /**
     * Adds the monomial passed in as an argument to the monomial that the method is
     * called on and returns a new monomial with the result
     *
     * @param  m the monomial being added onto
     * @return the monomial that is the result of the addition, or null if the addition isn't possible
     */
    public Monomial add(Monomial m){
        // if the exponents are different then monomial addition cannot be carried out
        if(this.expo != m.expo){
            return null;
        }

        // Otherwise add both coefficients and return them as a single value
        double newcfc = this.cfc + m.cfc;
       

        //Construct and return the new monomial
        Monomial result = null;
        result = new Monomial(newcfc, this.expo);
        return result;

    }
    
    /**
     * Subtracts the monomial passed in as an argument from the monomial
     * that the method is called on and returns the resulting monomial
     * 
     * @param m The monomial to be subtracted from the monomial called on
     * @return The monomial that is a result of the subtraction, or null if it can't be carried out
     * Note that the coefficient of a monomial can be negative
     */
    public Monomial subtract(Monomial m){
        // if the exponents are different then monomial subtraction cannot be carried out
        if(this.expo != m.expo){
            return null;
        }

        // Otherwise evaluate the difference between the coefficients and return it as a value
        double newcfc = this.cfc - m.cfc;
         //Construct and return the new monomial
        Monomial result = null;
        result = new Monomial(Math.round(newcfc*100.0)/100.0, this.expo);
        return result;
    }
    
     /**
     * Multiplies the monomial passed in as an argument, m, by the monomial on which the 
     * method is called on and returns a new monomial
     * with the result. 
     * 
     * @param m the monomial to be multiplied by the monomial on which the method is called on.
     * @return a monomial representing the result of the multiplication.
     */
    public Monomial multiply(Monomial m){
        //Calculate the new coefficient
        double newcfc = this.cfc * m.cfc;

        //Recall that multiplication in exponential terms is addition
        int newExpo = this.expo + m.expo;

        //Construct and return the new monomial
        Monomial result = null;
        result = new Monomial(Math.round(newcfc*100.0)/100.0, newExpo);
        return result;
    }
    
    /**
     * Divides the monomial the method is called on by the monomial passed in as an argument
     * and returns the result. 
     * 
     * @param m the monomial by which the monomial on which the method is called on will be divided.
     * @return a monomial representing the result of the division.
     */
    public Monomial divide (Monomial m){
        
        //Evaluate for division by zero
        if(m.cfc == 0.0){
            return null;
        }
        // Evaluate the new coefficient
        double newcfc = this.cfc / m.cfc;
        
        //Recall that division in exponential terms is subtraction
        int newExpo = this.expo - m.expo;
        
        //Construct and return the new monomial
        Monomial result = null;
        result = new Monomial(Math.round(newcfc*100.0)/100.0, newExpo);
        return result;
    }
    
    
    /**
     * Compares the object passed in as an argument to the monomial called on and
     * determines if they are equal
     * 
     * @param o the object to be compared to the monomial called on
     * @return true if the object is equal to the polynomial, false otherwise
     */
    public boolean equals(Object o){
       
        //first check if the object is a monomial
        if(!(o instanceof Monomial)){
            return false;
        }

        //Cast the object into a monomial
        Monomial m = (Monomial) o;
        
        //If both coefficients are zero, the exponent doesn't matter
        boolean zerocfcs = this.cfc == 0.0 && m.cfc == 0.0;
        if(zerocfcs){
            return true;
        }

        //Compare the exponents and coefficients
        if(this.expo != m.expo){
            return false;
        }
        else if(this.cfc != m.cfc){
            return false;
        }

        //If all these parameters have been passed then both monomials are equal
        return true;
    }
    
    /**
     * Creates a textual representation of the monomial called on
     * 
     * @return a string that is a textual representation of the monomial called on
     */
    public String toString(){
        //Create a string from which we will build on
        String resstring = "";
        
        //If the coefficient is zero, then the entire monomial is 0 regardless of the exponent
        if(this.cfc == 0.0){
            resstring = "0.0";
            return resstring;
        }
        
        //If the exponent is zero then we return just the coefficient
        if(this.expo == 0){
            resstring += this.cfc;
            return resstring;
        }
        
        //Cases where exponent is 1
        if(this.expo == 1){

            if(this.cfc == 1.0){
            resstring = "x";
            return resstring;
            }
            else if(this.cfc == -1.0){
            resstring = "-x";
            return resstring;
            }
            else{
                resstring = this.cfc + "x";
                return resstring;
            }
        }
        
        //create procedures for all other cases
         if (this.cfc == 1.0){
            resstring = "x^" + this.expo;
            return resstring;
        }
        else if(this.cfc == -1){
            resstring = "-x^" + this.expo;
            return resstring;
        }
        else{
            resstring += this.cfc + "x^" + this.expo;
            return resstring;
        }

    }
}
