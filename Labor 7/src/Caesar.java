public class Caesar {
    public String caesarCode(String input, String offset) {
        String code = "";
        input = input.toUpperCase();
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int shift = letters.indexOf(offset);
        for(int i = 0; i < input.length(); i++) {
            char c = (char) (input.charAt(i) + shift);
            if (c > 90) {
                code += (char)(input.charAt(i) - (26 - shift));
            } else {
                code += (char)(input.charAt(i) + shift);
            }
        }
        return code;
    }
    public String caesarDecode(String input, String offset) {
        String code = "";
        input = input.toUpperCase();
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int shift = letters.indexOf(offset);
        for(int i = 0; i < input.length(); i++) {
            char c = (char) (input.charAt(i) - shift);
            if (c < 65) {
                code += (char)(input.charAt(i) + (26 - shift));
            } else {
                code += (char)(input.charAt(i) - shift);
            }
        }
        return code;
    }
}
