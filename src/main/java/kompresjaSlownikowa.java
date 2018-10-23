import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;


public class kompresjaSlownikowa {

    public static void main(String[] args) {

        String tekst = "aabaabbccaccacaacbaacaaaabccaacabaacaabbacccaabbaaccaaabcbacabcbcaaaacbacabaccabaaabcccaaabccaabcac";
        String tmpEncodedText="";
        String tmpBin;
        String encodedText="";
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

        int numberOfBitsInChar = (int)Math.ceil(Math.log(dictionary.size())/Math.log(2));

        int reszta = (8 - (3 + tekst.length() * numberOfBitsInChar) % 8) % 8;

        System.out.println("Reszta: " + reszta);
        System.out.println("Liczba znaków w tekście do skompresowania: " + tekst.length());
        System.out.println("Liczba unikalnych znaków: " + dictionary.size());
        System.out.println("Liczba bitów na zakodowanie znaku: " + numberOfBitsInChar);


        tmpEncodedText = String.format("%3s",Integer.toBinaryString(reszta));
        tmpEncodedText = StringUtils.replace(tmpEncodedText, " ", "0");

        //zapisanie tekstu w postaci bitowej
        for (char letter: tekst.toCharArray()) {
            tmpBin = String.format("%"+numberOfBitsInChar+"s",Integer.toBinaryString(dictionary.get(letter)));
            tmpBin = StringUtils.replace(tmpBin," " , "0");
            tmpEncodedText = tmpEncodedText+tmpBin;
        }
        String tmpFinish = String.format("%"+reszta+"s", "1");
        tmpFinish = StringUtils.replace(tmpFinish, " ", "1");
        tmpEncodedText=tmpEncodedText + tmpFinish;

        String tmpCharBin;
        int parseInt;

        //kodowanie ośmiobitowych znaków do tekstu
        while(tmpEncodedText.length() >= 8){
            System.out.println("zaszyfrowany ciag: "+tmpEncodedText);
            tmpCharBin = tmpEncodedText.substring(0, 8);
            System.out.println("wyciągnięte bity: "+tmpCharBin);
            tmpEncodedText=tmpEncodedText.replaceFirst(tmpCharBin,"");
            parseInt = Integer.parseInt(tmpCharBin, 2);
            encodedText = encodedText + (char)parseInt;
            System.out.println("ASCII: "+parseInt);
        }

        System.out.println(encodedText);
        System.out.println("Dlugosc tekstu po kompresji: "+encodedText.length());
    }
}