package jsoup_Test;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class nonEnglih {

	public static void main(String[] args) {
        String s = "Il più crudele dei giorni";
        Charset c = Charset.forName("UTF-16BE");

        ByteBuffer b = c.encode(s);
        for (int i = 0; b.hasRemaining(); i++) {
            int charValue = (b.get()) & 0xff;
            System.out.print((char) charValue);
	}

}
}
