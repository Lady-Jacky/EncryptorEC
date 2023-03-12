public class Encryptor {
    private String[][] letterBlock;
    private int numRows;
    private int numCols;

    public Encryptor(int r, int c) {
        letterBlock = new String[r][c];
        numRows = r;
        numCols = c;
    }

    public String[][] getLetterBlock() {
        return letterBlock;
    }

    public void fillBlock(String str) {
        int count = 0;
        for (int i = 0; i < letterBlock.length; i++) {
            for (int x = 0; x < letterBlock[0].length; x++) {
                if (count < str.length()) {
                    letterBlock[i][x] = str.substring(count, count + 1);
                    count++;
                } else {
                    letterBlock[i][x] = "A";
                }
            }
        }
    }

    public String encryptBlock() {
        String result = "";
        for (int col = 0; col < letterBlock[0].length; col++) {
            for (int row = 0; row < letterBlock.length; row++) {
                result += letterBlock[row][col];
            }
        }
        return result;
    }

    public String encryptMessage(String message) {
        String concatedMessage = "";
        int rep = Math.round(message.length() / (numRows * numCols)) + 1;
        if ((double) message.length() / (numRows * numCols) == Math.round(message.length() / (numRows * numCols))) {
            rep--;
        }
        int count = 0;
        while (count < rep) {
            fillBlock(message);
            concatedMessage += encryptBlock();
            count++;
            if (message.length() - numRows * numCols > 0) {
                message = message.substring(numRows * numCols);
            }
        }
        return concatedMessage;
    }

    public String decryptMessage(String encryptedMessage) {
        String orig = "";
        String concatedMessage = "";
        int rep = Math.round(encryptedMessage.length() / (numRows * numCols)) + 1;
        if ((double) encryptedMessage.length() / (numRows * numCols) == Math.round(encryptedMessage.length() / (numRows * numCols))) {
            rep--;
        }
        int count = 0;
        while (count < rep) {
            fillBlock2(encryptedMessage);
            concatedMessage += decryptBlock();
            count++;
            if (encryptedMessage.length() - numRows * numCols > 0) {
                encryptedMessage = encryptedMessage.substring(numRows * numCols);
            }
        }
        int idx = concatedMessage.length()-1;
        while (concatedMessage.substring(idx).equals("A")){
            concatedMessage = concatedMessage.substring(0, idx);
            idx = concatedMessage.length()-1;
        }
        return concatedMessage;
    }

    public String decryptBlock() {
        String result = "";
        for (int row = 0; row < letterBlock.length; row++) {
            for (int col = 0; col < letterBlock[row].length; col++) {
                result += letterBlock[row][col];
            }
        }
        result.replaceAll("A", "");
        return result;
    }

      public void fillBlock2(String str) {
        int count = 0;
        for (int x = 0; x < letterBlock[0].length; x++) {
            for (int i = 0; i < letterBlock.length; i++) {
                letterBlock[i][x] = str.substring(count, count + 1);
                count++;
            }
        }
    }

    public String superEncryptMessage(String message, int offset) {
        String[] words = new String[message.length()];
        for(int j = 0; j < message.length(); j++) {
            words[j] = "" + message.charAt(j);
        }
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String capAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for(int i = 0; i < message.length(); i++) {
            if(alphabet.contains(words[i])) {
                    words[i] = "" + alphabet.charAt(offset1(alphabet.indexOf(words[i]), offset));
            }
            if(capAlphabet.contains(words[i])) {
                words[i] = "" + capAlphabet.charAt(offset1(capAlphabet.indexOf(words[i]), offset));
            }
        }

        String encrypted = "";
        for(int l = 0; l < words.length; l++) {
            encrypted += words[l];
        }
        return encryptMessage(encrypted);
    }

    public String superDecryptMessage(String message, int offset) {
        String out= decryptMessage(message);
        String decrypted = "";

        String[] words = new String[out.length()];

        for(int j = 0; j < out.length(); j++) {
            words[j] = "" + out.charAt(j);
        }
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String capAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for(int i = 0; i < out.length(); i++) {
            if(alphabet.contains(words[i])) {
                words[i] = "" + alphabet.charAt(offset2(alphabet.indexOf(words[i]), offset));
            }
            if(capAlphabet.contains(words[i])) {
                words[i] = "" + capAlphabet.charAt(offset2(capAlphabet.indexOf(words[i]), offset));
            }
        }

        for(int l = 0; l < words.length; l++) {
            decrypted += words[l];
        }
        return decrypted;
    }

    public int offset1(int orig, int offsetNum) {
        int num = orig + offsetNum;
        if(num >= 26) {
            num = num - 26;
        }
        return num;
    }
    public int offset2(int orig, int offsetNum) {
        int num = orig - offsetNum;
        if(num < 0) {
            num += 26;
        }
        return num;
    }

}
