package ba.unsa.etf.rpr;

import java.util.Stack;

/**
 * This class evaluates input string using Dijkstra's two stack algorithm
 * @author Emir Salkić
 */

public class ExpressionEvaluator {

    /**
     * Function that does the evaluation
     * @param izraz the expression that needs to be evaluated
     * @return the evaluated number
     */
    public Double evaluate(String izraz){

        // provjera da li su na pocetku i kraju zagrade
        Character prvi = izraz.charAt(0);
        Character zadnji = izraz.charAt(izraz.length()-1);
        if( !prvi.equals('(') || !zadnji.equals(')') ){
            throw new RuntimeException();
        }

        // provjera da li je izraz validan
        int brDesnihZagrada = 0;
        int brLijevihZagrada = 0;
        int brOperatora = 0;
        boolean prosliRazmak = true;
        boolean proslaCifra = false;
        for (int i = 0; i < izraz.length(); i++){
            Character trenutni = izraz.charAt(i);

            if(trenutni.equals('(')) {
                if(!prosliRazmak) throw new RuntimeException();
                brLijevihZagrada++;
                prosliRazmak = false;
            }
            else if (trenutni.equals(')')){
                if(!prosliRazmak) throw new RuntimeException();
                brDesnihZagrada++;
                prosliRazmak = false;
            }
            else if (trenutni.equals('+') || trenutni.equals('-')
                     || trenutni.equals('*') || trenutni.equals('/')){
                if(!prosliRazmak) throw new RuntimeException();
                brOperatora++;
                prosliRazmak = false;
            }
            else if(trenutni.equals('s')){
                //gleda da li je substring sqrt za korjenovanje
                if(izraz.substring(i,i+4).equals("sqrt")){
                    if(!prosliRazmak) throw new RuntimeException();
                    brOperatora++;
                    prosliRazmak = false;
                    i+=3;
                }
                else throw new RuntimeException();
            }
            else if(Character.isDigit(trenutni) || trenutni.equals('.')){
                //equals(.) za decimalne brojeve
                if(!prosliRazmak && !proslaCifra) throw new RuntimeException();
                prosliRazmak = false;
                proslaCifra = true;
            }
            else if(trenutni.equals(' ')){
                if(prosliRazmak) throw new RuntimeException();
                else prosliRazmak = true;
            }
            else{
                throw new RuntimeException();
            }
        }
        if(brLijevihZagrada != brDesnihZagrada || brDesnihZagrada != brOperatora){
            //ako neke zagrade nisu zatvorene ili ako nema dovoljno operatora baca izuzetak
            throw new RuntimeException();
        }

        // izvrsavanje algoritma na validnom stringu
        Stack<String> operatori = new Stack<>();
        Stack<Double> vrijednosti = new Stack<>();
        for(String znak: izraz.split(" ")){
            if      (znak.equals("("));
            else if (znak.equals("+")) operatori.push(znak);
            else if (znak.equals("-")) operatori.push(znak);
            else if (znak.equals("*")) operatori.push(znak);
            else if (znak.equals("/")) operatori.push(znak);
            else if (znak.equals("sqrt")) operatori.push(znak);
            else if (znak.equals(")")){
                String o = operatori.pop();
                Double v = vrijednosti.pop();
                if      (o.equals("+")) v = vrijednosti.pop() + v;
                else if (o.equals("-")) v = vrijednosti.pop() - v;
                else if (o.equals("*")) v = vrijednosti.pop() * v;
                else if (o.equals("/")){
                    //baca izuzetak za dijeljenje sa nulom
                    if(v == 0) throw new RuntimeException();
                    v = vrijednosti.pop() / v;
                }
                else if (o.equals("sqrt")) {
                    v = Math.sqrt(v);
                }
                vrijednosti.push(v);
            }
            else vrijednosti.push(Double.parseDouble(znak));
        }
        return vrijednosti.pop();
    }
}
