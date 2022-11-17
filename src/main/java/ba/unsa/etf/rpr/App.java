package ba.unsa.etf.rpr;

/**
 * Main class
 * @author Emir Salkić
 */
public class App 
{
    public static void main( String[] args )
    {
        ExpressionEvaluator evaluator = new ExpressionEvaluator();
        try {
            System.out.println("Rezultat unesenog izraza je: " + evaluator.evaluate(args[0]));
        }
        catch (RuntimeException e){
            System.out.println("Uneseni izraz nije validan!");
        }
    }
}
