package test.bit;

public class BitRepresenting {
    public static void main(String[] args) {
        String representation = represent(541435521);
        System.out.println(representation);
        System.out.println(toNumber(representation));
    }

    private static String represent(int value) {
        byte[] bytes = toBytes(value);
        StringBuffer representation = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            representation.append(represent(bytes[i]));
            if (i != 3) {
                representation.append(' ');
            }
        }
        return representation.toString();
    }

    private static String represent(byte value) {
        StringBuilder representation = new StringBuilder();
        for (int i = 7; i >= 0; i--) {
            representation.append(((value >> i) & 1) == 1 ? '1' : '0');
        }
        return representation.toString();
    }

    private static byte[] toBytes(int value) {
        byte[] parsed = new byte[4];

        parsed[0] = (byte) (value >> 24);
        parsed[1] = (byte) (value >> 16);
        parsed[2] = (byte) (value >> 8);
        parsed[3] = (byte) (value);

        return parsed;
    }

    private static int toNumber(String inSeq) {
        String seq = inSeq.replaceAll("\\W", "");
        char[] chars = seq.toCharArray();
        int n = 0;

        if (chars.length != 32)
            return 0;

        for (int i = 0; i < 32; i++) {
            int currentBit = 1 << i;
            char currentChar = chars[32 - 1 - i];

            if (currentChar == '1')
                n |= currentBit;
            else if (currentChar != '0')
                return 0;
        }

        return n;
    }
}
