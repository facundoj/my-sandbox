package test.bit;

public class Bitwise {
    public static void main(String[] args) {
        int a = -536870911; // 1110...01
        // 1110...01 --> -536870911
        // 11110...00 --> -268435456
        // 01110...00 --> 1879048192
        System.out.println((a << 1) >>> 1); // 0110...01 --> 1610612737
        System.out.println(17 << 1);
    }
}
