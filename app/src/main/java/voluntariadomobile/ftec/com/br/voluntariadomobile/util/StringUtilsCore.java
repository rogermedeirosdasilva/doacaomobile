package voluntariadomobile.ftec.com.br.voluntariadomobile.util;

import java.security.MessageDigest;
import java.text.Normalizer;

/**
 * Criado pr Roger em 08/05/2015.
 */
public class StringUtilsCore {
    public static String RemoveNonAlpha(String input) {
        return input.replaceAll("[^a-zA-Z]+","");
    }

    public static String RemoveNonAlphaDigit(String input) {
        return input.replaceAll("[^a-zA-Z0-9]+","");
    }

    public static String RemoveNonDigit(String input) {
        return input.replaceAll("[^0-9]+","");
    }

    public static String StringNormalizer(String input) {
        return Normalizer
                .normalize(input, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }

    public static String geraSHA1(String Value) throws Exception {
        MessageDigest m = MessageDigest.getInstance("SHA-1");

        return byteArrayToHexString(m.digest(Value.getBytes()));
    }

    private static String byteArrayToHexString(byte[] b) {
        String result = "";
        for (int i=0; i < b.length; i++) {
            result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
        }
        return result;
    }
}
