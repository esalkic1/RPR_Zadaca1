package ba.unsa.etf.rpr;


import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Unit tests for class ExpressionEvaluator
 * @author Emir Salkić
 */
public class ExpressionEvaulatorTest
{

    ExpressionEvaluator e = new ExpressionEvaluator();

    /**
     * Tests if spaces are ignored
     */
    @Test
    void exceptionTest() {
        assertThrows(RuntimeException.class, () -> e.evaluate("((3*4)+2)"));
    }

    /**
     * Checks if operand is forgotten
     */
    @Test
    void exceptionTest2() {
        assertThrows(RuntimeException.class, () -> e.evaluate("( ( 3 + * 4 ) + 2 )"));
    }

    /**
     * Checks if there are brackets missing
     */
    @Test
    void exceptionTest3() {
        assertThrows(RuntimeException.class, () -> e.evaluate("3 * 4 + 2"));
    }

    /**
     * Checks if there is an operator missing
     */
    @Test
    void exceptionTest4() {
        assertThrows(RuntimeException.class, () -> e.evaluate("( 3 ) + ( 4 ) ( 2 ) )"));
    }

    /**
     * Checks if there are too many spaces
     */
    @Test
    void exceptionTest5() {
        assertThrows(RuntimeException.class, () -> e.evaluate("( 3 *    ( 4 / 2 ) )"));
    }

    /**
     * Tests division by zero
     */
    @Test
    void exceptionTest6() {
        assertThrows(RuntimeException.class, () -> e.evaluate("( 3 * ( 4 / 0 ) )"));
    }

    /**
     * Tests if a valid input is being correctly evaluated
     */
    @Test
    void provjeraEvaluacije(){
        assertEquals(101D, e.evaluate("( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )"));
    }

    /**
     * Test for sqrt function
     */
    @Test
    void provjeraEvaluacije2(){
        assertEquals(4D,e.evaluate("( 2 * sqrt ( 4 ) )"));
    }

    /**
     * Test for division and subtraction
     */
    @Test
    void provjeraEvaluacije3(){
        assertEquals(6D,e.evaluate("( ( 256 / 16 ) - 10 )"));
    }

    /**
     * Test for decimal numbers
     */
    @Test
    void provjeraEvaluacije4(){
        assertEquals(21.25,e.evaluate("( ( 12.5 - 4 ) * 2.5 )"));
    }

    /**
     * Test for simple expression
     */
    @Test
    void provjeraEvaluacije5(){
        assertEquals(5D,e.evaluate("( 2 + 3 )"));
    }
}
