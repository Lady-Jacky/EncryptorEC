import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Encryptor encryptor1 = new Encryptor(2, 1);
        System.out.println(encryptor1.superEncryptMessage("This is confidential! Be on the lookout!", 2));
        System.out.println(encryptor1.superDecryptMessage("Vjku ku eqphkfgpvkcn! Dg qp vjg nqqmqwv!", 2));


    }
}