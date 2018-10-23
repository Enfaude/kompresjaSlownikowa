import java.util.HashMap;

public class kompresjaSlownikowa {

    public static void main(String[] args) {

        String tekst = "ACBABBBACCDB";
        System.out.println("Tekst do skompresowania:" + tekst);
        char[] tempcode = tekst.toCharArray();
        int dictionaryCode = 0;
        HashMap<Character, Integer> dictionary = new HashMap<>();

        for (char znak : tempcode)
        {
            if (!dictionary.containsKey(znak))
            {

                dictionary.put(znak, dictionaryCode);
                dictionaryCode++;
            }
        }

        for (HashMap.Entry<Character, Integer> pair : dictionary.entrySet())
        {
            System.out.println(pair.getKey() +" "+ pair.getValue());
        }

        int numberOfBitsInChar = (int)Math.ceil(Math.log(dictionary.size()));
        //string value = Convert.ToString(dictionaryCode, 2).PadLeft(numberOfBitsInChar, '0');

        int reszta = (8 - (3 + tekst.length() * numberOfBitsInChar) % 8) % 8;

        System.out.println("Reszta: " + reszta);
        System.out.println("Liczba znaków w tekście do skompresowania: " + tekst.length());
        System.out.println("Liczba unikalnych znaków: " + dictionary.size());
        System.out.println("Liczba bitów na zakodowanie znaku: " + numberOfBitsInChar);

    }
}
