import java.util.ArrayList;

/**
 * Class to represent a polynomial, e.g. 3.5x^4 + 3x^2 - 4.
 * 
 * Polynomials can be added, subtracted, multiplied, and divided.
 * 
 * This class is a skeleton. You need to provide implementations
 * for the methods here defined. Feel free however, to add additional
 * methods as you see fit.
 *
 * @author Oluwatomiwa Olumuyiwa
 * @version 12.5.2021
 */
public class Poly{

    // TODO your instance fields here
    
    //The polynomial's coefficient
    double cfc;
    //The polynomial's exponent
    int expo;
    
    //A polynomial is composed of one or more monomial. Therefore the polynomial being an array list of monomials makes sense
    ArrayList<Monomial> polylist = new ArrayList<Monomial>();
    
    /**
     * Creates a new polynomial containing a single term with the coefficient
     * and exponent passed in as arguments. E.g. when called with coefficient
     * 3.5 and exponent 2, it should create a polynomial 3.5x^2.
     * 
     * You can create additional constructors if you'd like, but it's 
     * imperative that this one exists.
     * 
     * @param cfc the single term's coefficient.
     * @param expo the single term's exponent.
     * @return the polynomial created.
     */
    public Poly(double cfc, int expo){
        // TODO implement
        this.cfc = cfc;
        this.expo = expo;
        //Construct a new monomial to be passed into the arraylist
        Monomial m = new Monomial (cfc, expo);
        
        //Add the monomial to the polynomial list
        this.polylist.add(m);
    }
    
    /**
     * Empty constructor
     */

    public Poly(){
        //Put a zero monomial in the list
        Monomial m = new Monomial(0.0, 0);
        this.polylist.add(m);
    }
    
    /**
     * Adds the polynomial passed in as an argument, p, to the polynomial on which the 
     * method is called on (the "this" polynomial), and returns a new polynomial
     * with the result. I.e., it returns "this + p".
     * 
     * @param p the polynomial to add onto the polynomial on which the method is called on.
     * @return a polynomial representing the result of the addition.
     */
    public Poly add(Poly p){
        // TODO implement
        //A list that will hold the results of adding monomials
        ArrayList<Monomial> Odpolylist = new ArrayList<Monomial>();
        
        //list of monomials in the argument polynomial
        ArrayList<Monomial> pList = p.polylist;
        
        //list of monomials in the polynomial the method is called on
        ArrayList<Monomial> thisList = this.polylist;
        //Create a new polynomial that will hold our result 
        Poly result = new Poly();

        

        

        //This loop will pick items from pList and compare it to all elements in thisList
        for(int i = 0; i < pList.size(); i++){
            //Obtain the monomial in the i index of pList
            Monomial m2 = pList.get(i);

            //loop through thisList comparing monomial m2 to the monomials in thisList
            for (int j = 0; j < thisList.size(); j++){
                //Obtain the monomial in the j index of pList
                Monomial m1 = thisList.get(j);

                //If the monomials have matching exponents, they can be added
                if(m1.expo == m2.expo){
                    //Add the sum to Odpolylist then break from this loop since the matching exponents have already been found
                    Odpolylist.add(m1.add(m2));
                    break;
                }
                //If you've reached the end of thisList and none of the monomials have had the same exponent as m1 so far, add m1 as it is to the Odpolylist
                else if(j == thisList.size() - 1){
                    Odpolylist.add(m2);
                }
            }
        }

        //The loop above compares each monomial in pList to monomials in thisList, it is therefore possible that there are some monomials in thisList that did not have
        //the same exponent as monomials in pList hence they were not added to the Odpolylist. This loop identifies those monomials and adds them to Odpolylist
        for(int i = 0; i < thisList.size(); i++){
            //Obtain the monomial in index i of pList
            Monomial m1 = thisList.get(i);

            //This loop is for comparing the monomial above to monomials already in Odpolylist
            for(int j = 0; j < Odpolylist.size(); j++){
                //Obtain the monomial in index j of Odpolylist
                Monomial m2 = Odpolylist.get(j);

                //If the exponents of the two monomials match, it means the monomial m1 was dealt with in the previous loop hence we break from this loop
                if(m1.expo == m2.expo){
                    break;
                }
                //If we've reached the end of the Odpolylist and the m1 exponent hasn't been matched yet, then the monomial m1 was ignored in the previous loop
                //we add the monomial to Odpolylist
                else if(j == Odpolylist.size() - 1){
                    Odpolylist.add(m1);
                }
            }
        }

        result.polylist = Odpolylist;

        //order the result
        result.order();
        return result;
    }
    
    /**
     * Subtracts the polynomial passed in as an argument, p, from the polynomial on which the 
     * method is called on (the "this" polynomial), and returns a new polynomial
     * with the result. I.e., it returns "this - p".
     * 
     * @param p the polynomial to be subtracted from the polynomial on which the method is called on.
     * @return a polynomial representing the result of the subtraction.
     */
    public Poly subtract(Poly p){
        // TODO implement
        //A list that will hold the results of adding monomials
        ArrayList<Monomial> Odpolylist = new ArrayList<Monomial>();
        
        //list of monomials in the argument polynomial
        ArrayList<Monomial> pList = p.polylist;
        
        //list of monomials in the polynomial the method is called on
        ArrayList<Monomial> thisList = this.polylist;
        
        //Create a new polynomial that will hold our result 
        Poly result = new Poly();
        
        //This is basically a negation of a given polynomial
        Monomial minus1 = new Monomial(-1.0 , 0);
        
        //This loop will pick items from thisList and compare it to all elements in thisList
        for(int i = 0; i < thisList.size(); i++){
            //Obtain the monomial in the i index of pList
            Monomial mono1 = thisList.get(i);

            //loop through pList comparing monomial mono1 to the monomials in thisList
            for (int j = 0; j < pList.size(); j++){
                //Obtain the monomial in the j index of pList
                Monomial mono2 = pList.get(j);

                //If the monomials have matching exponents, subtraction can be evaluated
                if(mono1.expo == mono2.expo){
                    //Add the difference to Odpolylist then break from this loop since the matching exponents have already been found
                    Odpolylist.add(mono1.subtract(mono2));
                    break;
                }
                //If you've reached the end of thisList and none of the monomials have had the same exponent as mono1 so far, add mono1 as it is to the Odpolylist
                else if(j == thisList.size() - 1){
                    Odpolylist.add(mono1);
                }
            }
        }
        
          //Loop through pList looking for monomials that didn't have matching exponents and weren't dealt with in the previous loop
        for(int i = 0; i < pList.size(); i++){
            //Obtain the monomial in index i of the list
            Monomial mono1 = pList.get(i);

            //Loop through newlist
            for (int j = 0; j < Odpolylist.size(); j++){
                //Obtain the monomial in index j of the list
                Monomial mono2 = Odpolylist.get(j);

                //If exponents match, then the monomial was dealt with in the previous loop hence we ignore it
                if(mono1.expo == mono2.expo){
                    break;
                }
                //Otherwise, if we are at the end of the list and none of the exponents have matched, add the appropriate monomial negation
                else if(j == Odpolylist.size() - 1){
                    Monomial negmono1 = minus1.multiply(mono1);
                    Odpolylist.add(negmono1);
                }

            }
        }

        result.polylist = Odpolylist;

        //order the result
        result.order();

        return result; 
    }

    
    
 
    /**
     * Multiplies the polynomial passed in as an argument, p, by the polynomial on which the 
     * method is called on (the "this" polynomial), and returns a new polynomial
     * with the result. I.e., it returns "this * p".
     * 
     * @param p the polynomial to be multiplied by the polynomial on which the method is called on.
     * @return a polynomial representing the result of the multiplication.
     */
    public Poly multiply(Poly p){
        //Create a new polynomial that will hold our result 
        Poly result = new Poly();

        //A list that will hold the results of multiplying monomials
        ArrayList<Monomial> reslist = new ArrayList<Monomial>();

        //List of monomials in the polynomial called on
        ArrayList<Monomial> thisList = this.polylist;

        //list of monomials in the argument polynomial
        ArrayList<Monomial> pList = p.polylist;
        
       

        //Loop through thisList multiplying each monomial in it with every monomial in pList
        for (int i = 0; i < thisList.size(); i++){
            //Obtain the monomial in index i of thisList
            Monomial mono1 =  thisList.get(i);

            //Loop through pList multiplying each polynomial
            for(int j = 0; j < pList.size(); j++){
                //Obtain the monomial in index j of pList
                Monomial mono2 = pList.get(j);

                reslist.add(mono1.multiply(mono2));
            }
        }

        result.polylist = reslist;

        //order the result
        result.order();
        return result; 
    }


    /**
     * Gives a given polynomial an order equivalent to how it would be represented 
     * naturally (on paper) i.e the terms are qwritten in decreasing exponent order
     */
    public Poly order(){
        //Create a list that will house the ordered polynomial
        ArrayList<Monomial> OdpolyList = new ArrayList<Monomial>();
        
        
        
        
        
        //If the polynomial is empty return null
        if(this.polylist.size() < 1){
        return new Poly(0.0, 0);}
        
        //"Initialise" the ordered polynomial by adding the first item in the input list to it and then removing 
        // that monomial from the input list
        OdpolyList.add(this.polylist.get(0));
        this.polylist.remove(0);
        
        //Loop through the input list and reorder
         for(int i= 0; i < this.polylist.size(); i++){
            
            Monomial mono1 = this.polylist.get(i);
        
            //Loop through Odpolylist looking for the appropriate position for m1
            for (int j = 0; j < OdpolyList.size(); j++){
                //Obtain the monomial in the index j of Odpolylist
                Monomial mono2 = OdpolyList.get(j);
                //if the two exponents match, then add them and replace with the sum
                 if(mono1.expo == mono2.expo){
                    OdpolyList.remove(j);
                    OdpolyList.add(j,mono1.add(mono2));
                    break;
                }
                //If m1 exponent is more than m2 exponent, add it to that index
                else if(mono1.expo > mono2.expo){
                    OdpolyList.add(j,mono1);
                    break;
                }
                // if you've reached the end of the list and none of the above conditions have been met, add m1 to the end of the list
                else if(j == OdpolyList.size() - 1){
                    OdpolyList.add(mono1);
                    break;
                }

            }

        }
        //Update the list whose polynomial is called on to be the ordered polynomial list
        this.polylist = OdpolyList;
        
        //If a monomial has a coefficient of zero, it has no effect on the size of the polynomial. Thus it should be omitted.\
        for(int i = 0; i < this.polylist.size(); i++){
            if (this.polylist.get(i).cfc == 0){
                this.polylist.remove(i);
            }
        }
        return this;
    }
    /**
     * Divides the polynomial on which the method is called on (the "this" polynomial), by
     * the polynomial passed in as an argument, p, and returns a new polynomial
     * with the resulting quotient. I.e., it returns "this / p".
     * 
     * The division should be performed according to the polynomial long division algorithm
     * ( https://en.wikipedia.org/wiki/Polynomial_long_division ).
     * 
     * Polynomial division may end with a non-zero remainder, which means the polynomials are
     * indivisible. In this case the method should return null. A division by zero should also
     * yield a null return value.
     * 
     * @param p the polynomial to be multiplied by the polynomial on which the method is called on.
     * @return a polynomial representing the quotient of the division, or null if they're indivisible.
     */    
    public Poly divide(Poly p){
        //Reorder both polynomials
        this.order();
        p.order();

        //Create a new polynomial that will hold the result
        Poly result = new Poly();

        //A list that will hold the monomials of the final results
        ArrayList<Monomial> rlist = new ArrayList<Monomial>();

        //Obtain the lists in the divisor and dividend
        ArrayList<Monomial> thisList = this.polylist;
        ArrayList<Monomial> pList = p.polylist;

        //if polynomial p is zero, it is indivisible
        if(pList.size() < 1){
            return null;
        }
        //If the polynomial called on is zero, the answer is zero
        else if(thisList.size() < 1){
            return new Poly(0.0,0);
        }
        
        //As we keep subtracting from the original polynomial, this polynomial will help keep track of results of the subtractions so that
        //we don't have to change the original polynomial
        Poly p1 = new Poly();
        
            p1.polylist = thisList;
            Monomial mono1 = thisList.get(0);
            Monomial mono2 = pList.get(0);
            //dividing by a constant
            if(mono2.expo == 0){
                for (int i = 0; i < thisList.size(); i++){
                    Monomial m3 = new Monomial(thisList.get(i).cfc/mono2.cfc, thisList.get(i).expo);
                    rlist.add(m3);
                }
                result.polylist = rlist;
                result.order();
                return result;
            }
            //dividing a constant shout return the inverse polynomial multiplied by that constant
            else if (mono1.expo == 0){
                for (int i = 0; i < pList.size(); i++){
                    Monomial m3 = new Monomial( Math.round((mono1.cfc/pList.get(i).cfc) * 100) / 100 , (-1 * (pList.get(i).expo)));
                    rlist.add(m3);
                }
                result.polylist = rlist;
                result.order();
                return result;
            }
            else{
             while(pList.get(0).expo >= thisList.get(0).expo){

            //the monomial list in p1 is initially equal to the list in the polynomial called on. As the loop keeps getting executed,thisList will get updated
            //and therefore the p1 list will also get updated
            p1.polylist = thisList;

            //Get the monomial with the largest exponent in thisList
            Monomial m1 = thisList.get(0);

            //Get the monomial with the largest exponent in pList
            Monomial m2 = pList.get(0);

            //If the exponent of m2 is larger than the exponent of m1, then they are indivisible
            if(m2.expo > m1.expo){
                return null;
            }

            //Get the result of dividing the two then add it to the result's list
            Monomial m3 = m1.divide(m2);
            rlist.add(m3);

            //To facilitate multiplication of the m3 monomial by p, make a polynomial with the same values
            Poly p2 = new Poly(m3.cfc,m3.expo);

            //Multiply the polynomial above with the argument polynomial to get the value to subtract from the original polynomial
            Poly p3 = p2.multiply(p);

            //Subtract p3 from p1
            Poly p4 = p1.subtract(p3);

            //Update thisList so that it contains the value of the subtraction result
            thisList = p4.polylist;           

            //When only zero monomials are remaining, then we have the result hence break from the loop
            if(thisList.size() == 0 || thisList.get(0).cfc == 0.0){
                break;
            }

        }
     //Make the monomial list of the result to be listy
        result.polylist = rlist;

        //Reorder the polynomial then return
        result.order();

        return result; 
        
}
        
}     
    
    /**
     * Compares the polynomial on which the method is called (the "this" polynomial), 
     * to the object passed in as argument, o. o is to be considered equal to the "this"
     * polynomial if they both represent equivalent polynomials.
     * 
     * E.g., "3.0x^4 + 0.0x^2 + 5.0" and "3.0x^4 + 5.0" should be considered equivalent.
     * "3.0x^4 + 5.0" and "3.0x^4 + 3.0" should not.
     * 
     * @param o the object to be compared against the polynomial the method is called on.
     * @return true if o is a polynomial equivalent to the one the method was called on,
     * and false otherwise.
     */
    public boolean equals(Object o){
        //first check if the object is a polynomial
        if(!(o instanceof Poly)){
            return false;
        }
        //Cast the object into a Poly
        Poly x = (Poly) o;
        //Obtain the list of monomials in the polynomial called on
        ArrayList<Monomial> thisList = this.polylist;

        //Obtain the list of monomials in the polynomial argument
        ArrayList<Monomial> xList = x.polylist;
        
        //Check if there's an equal monomial for every monomial in xList
        for(int i = 0; i < xList.size(); i++){
            Monomial m1 = xList.get(i);

            for(int j = 0; j < thisList.size(); j++){
                Monomial m2 = thisList.get(j);

                //If the exponents of the monomials match, then check if the two monomials are equal
                if(m1.expo == m2.expo){
                    if(m1.equals(m2) == false){
                        //Return false if the monomials are not equals
                        return false;
                    }
                    //Otherwise break from this loop and obtain another m1 monomial
                    else{
                        break;
                    }                    
                }
                
                else if(j == thisList.size()  - 1){
                    //If the coefficient of m1 is not zero, then the polynomials cannot be matching
                    if(m1.cfc != 0.0){
                        return false;
                    }
                    else{
                        break;
                    }
                }
            }

        }
        //Do the reverse loop to check across all monomials in thisList
                for(int i = 0; i < thisList.size(); i++){
            Monomial m1 = thisList.get(i);

            for(int j = 0; j < xList.size(); j++){
                Monomial m2 = xList.get(j);

                //If the exponents of the monomials match, then check if the two monomials are equal
                if(m1.expo == m2.expo){
                    if(m1.equals(m2) == false){
                        //Return false if the monomials are not equals
                        return false;
                    }
                    //Otherwise break from this loop and obtain another m1 monomial
                    else{
                        break;
                    }                    
                }
                
                else if(j == xList.size()  - 1){
                    //If the coefficient of m1 is not zero, then the polynomials cannot be matching
                    if(m1.cfc != 0.0){
                        return false;
                    }
                    //Othewise break from the loop and obtain another m1
                    else{
                        break;
                    }
                }
            }

        }
        //If both loops have been evaluated and a false value hasnt been returned then both polynomials
        //are equal
        return true;
    }

    /**
     * Returns a textual representation of the polynomial the method is called on.
     * The textual representation should be a sum of monomials, with each monomial 
     * being defined by a double coefficient, the letters "x^", and an integer exponent.
     * Exceptions to this rule: coefficients of 1.0 should be omitted, as should "^1",
     * and "x^0".
     * 
     * Terms should be listed in decreasing-exponent order. Terms with zero-coefficient
     * should be omitted. Each exponent can only appear once. 
     * 
     * Rules for separating terms, applicable to all terms other that the largest exponent one:
     *   - Terms with positive coefficients should be preceeded by " + ".
     *   - Terms with negative coefficients should be preceeded by " - ".
     * 
     * Rules for the highest exponent term (i.e., the first):
     *   - If the coefficient is negative it should be preceeded by "-". E.g. "-3.0x^5".
     *   - If the coefficient is positive it should not preceeded by anything. E.g. "3.0x^5".
     * 
     * The zero/empty polynomial should be represented as "0.0".
     * 
     * Examples of valid representations: 
     *   - "2.0x^2 + 3.0"
     *   - "3.5x + 3.0"
     *   - "4.0"
     *   - "-2.0x"
     *   - "4.0x - 3.0"
     *   - "0.0"
     *   
     * Examples of invalid representations: 
     *   - "+2.0x^2+3.0x^0"
     *   - "3.5x -3.0"
     *   - "- 4.0 + x"
     *   - "-4.0 + x^7"
     *   - ""
     * 
     * @return a textual representation of the polynomial the method was called on.
     */
    public String toString(){
        //String that we will build upon
        String resstring = "";
        //Order the polynomial
        this.order();
        //print the first monomial out 
       
         if(this.polylist.size() == 1){
            return this.polylist.get(0).toString();
        } 
        //If the polynomial list is empty, return zero
        else if(this.polylist.size() < 1){
            return "0.0";
        }
        
        //Add the first element in the list to resstring 
        //resstring =  resstring + this.polylist.get(0).toString();
        else if (this.polylist.size() > 1){ 
             resstring += this.polylist.get(0).toString();
        for(int i = 1; i < this.polylist.size(); i++){
            if (this.polylist.get(i).cfc > 0.0){
                resstring += " + " + this.polylist.get(i).toString();
            }
            else if(this.polylist.get(i).cfc == 0.0){
               resstring += "";
            }
            else{
                if (this.polylist.get(i).cfc == -1.0){
                    if(this.polylist.get(i).expo == 0){
                    resstring = resstring + " - 1.0 ";
                }
                else if (this.polylist.get(i).expo == 1){
                    resstring = resstring + " - x ";
                }
                else if (this.polylist.get(i).expo == -1){
                    resstring += " x ";
                    //Negation cancels out negation
                }
                else{
                  resstring += " - x^" + this.polylist.get(i).expo + " ";  
                }
                }
                else if(this.polylist.get(i).expo == 0){
                    resstring += " - " + (this.polylist.get(i).cfc * -1) + " ";
                }
                else if(this.polylist.get(i).expo == 1){
                    resstring += " - " + (this.polylist.get(i).cfc * -1) + "x "; 
                }
                else{
                    resstring += " - " + (this.polylist.get(i).cfc * -1) + "x^" + this.polylist.get(i).expo + "";
                }
            }
        }
    }
    return resstring;
}
    

}

