package zest;

public class SumOfTwoIntegers {

    // Precondition: a, b are within the 32bit signed integer range
    public int getSum(int a, int b) {
        int initialA = a;
        int initialB = b;
        while (b != 0) {
            int carry = (a & b) << 1;  // Carry now contains common set bits of a and b
            a = a ^ b;  // Sum of bits of a and b where at least one of the bits is not set
            b = carry;  // Carry is shifted by one so that adding it to a gives the required sum
        }
        assert a == initialA + initialB : "Sum has not the same outcome as + operator";
        return a;
    }


    public long getLongSum(int a, int b) {
        long aLong = a;
        long bLong = b;
        while (bLong != 0) {
            long carry = (aLong & bLong) << 1;  // Carry now contains common set bits of a and b
            aLong = aLong ^ bLong;  // Sum of bits of a and b where at least one of the bits is not set
            bLong = carry;  // Carry is shifted by one so that adding it to a gives the required sum
        }
        return aLong;
    }
}
