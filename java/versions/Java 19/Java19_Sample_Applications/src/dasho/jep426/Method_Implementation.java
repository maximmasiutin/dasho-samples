package dasho.jep426;

import jdk.incubator.vector.ByteVector;
import jdk.incubator.vector.IntVector;
import jdk.incubator.vector.VectorMask;
import jdk.incubator.vector.VectorSpecies;

import java.lang.foreign.MemorySegment;
import java.nio.ByteOrder;

import static jdk.incubator.vector.VectorOperators.BIT_COUNT;

import static jdk.incubator.vector.VectorOperators.LEADING_ZEROS_COUNT;
import static jdk.incubator.vector.VectorOperators.TRAILING_ZEROS_COUNT;
import static jdk.incubator.vector.VectorOperators.REVERSE;
import static jdk.incubator.vector.VectorOperators.REVERSE_BYTES;

public class Method_Implementation {

    //Lanewise Bitwise Operation
    public static final VectorSpecies<Integer> SPECIES = IntVector.SPECIES_PREFERRED;

    public static void laneWise() {
        int[] values = {7, 2, 3, 4, 5, 1, 2, 3, 4, 5};

        var upperBound = SPECIES.loopBound(values.length);
        var readValues = 0;
        for (; readValues < upperBound; readValues += SPECIES.length()) {
            var valuenumber1 = IntVector.fromArray(SPECIES, values, readValues);

            System.out.println("Bit Count= " + valuenumber1.lanewise(BIT_COUNT));
            System.out.println("Leading Zero Count= " + valuenumber1.lanewise(LEADING_ZEROS_COUNT));
            System.out.println("Trailing Zero Count= " + valuenumber1.lanewise(TRAILING_ZEROS_COUNT));
            System.out.println("Reverse= " + valuenumber1.lanewise(REVERSE));
            System.out.println("Reverse Bytes= " + valuenumber1.lanewise(REVERSE_BYTES));


        }
    }

    //Compress bit
    public int compress(int value, int mask) {
        int right, shift, mask_bit; // Result, shift, mask bit.
        right = 0;
        shift = 0;
        do {
            mask_bit = mask & 1;
            right = right | ((value & mask_bit) << shift);
            shift = shift + mask_bit;
            value = value >> 1;
            mask = mask >> 1;
        } while (mask != 0);
        return right;

    }

    //Expand Bit
    public int expand(int value, int mask) {
        int mask0riginal, right_even_zero, right_odd_zero, move, targetValue;
        int array[] = new int[5];

        mask0riginal = mask; // Save original mask.
        right_even_zero = ~mask << 1; // We will count 0's to right.
        for (int count = 0; count < 5; count++) {
            right_odd_zero = right_even_zero ^ (right_even_zero << 1); // Parallel suffix.
            right_odd_zero = right_odd_zero ^ (right_odd_zero << 2);
            right_odd_zero = right_odd_zero ^ (right_odd_zero << 4);
            right_odd_zero = right_odd_zero ^ (right_odd_zero << 8);
            right_odd_zero = right_odd_zero ^ (right_odd_zero << 16);
            move = right_odd_zero & mask; // Bits to move.
            array[count] = move;
            mask = (mask ^ move) | (move >> (1 << count)); // Compress mask.
            right_even_zero = right_even_zero & ~right_odd_zero;
        }
        for (int count = 4; count >= 0; count--) {
            move = array[count];
            targetValue = value << (1 << count);
            value = (value & ~move) | (targetValue & move);
        }
        return value & mask0riginal;
    }

    //Memory Segment
    public static final VectorSpecies<Byte> species = ByteVector.SPECIES_PREFERRED;

    public void jep426_MemorySegment() {


        byte value[] = new byte[64];
        value[0] = 1;
        value[1] = 2;
        value[2] = 3;
        value[3] = 4;
        value[4] = 5;
        final var memorySegments = MemorySegment.ofArray(value);
        var mask = species.maskAll(true);
        ByteVector byteVector = ByteVector.fromMemorySegment(species, memorySegments, 0, ByteOrder.nativeOrder(), mask);
        byteVector.intoMemorySegment(memorySegments, 0, ByteOrder.nativeOrder());
        System.out.println("Memory Segment = " + byteVector);


    }

    //Compress and expand operation

    public void compressExpand() {

        int[] value = {1, 2, 3, 4};

        IntVector sourceVector = IntVector.fromArray(IntVector.SPECIES_128, value, 0);
        boolean booleans[] = {true, false, true, false};
        VectorMask<Integer> mask = VectorMask.fromArray(IntVector.SPECIES_128, booleans, 0);
        IntVector destinationVector = sourceVector.compress(mask);
        System.out.println("Compressed data: " + destinationVector.toString());
        IntVector expandVector = destinationVector.expand(mask);
        System.out.println("Expanded data: " + expandVector.toString());
        VectorMask<Integer> mask1 = VectorMask.fromArray(IntVector.SPECIES_128, booleans, 0);
        VectorMask<Integer> destinationVector1 = mask1.compress();
        System.out.println("Mask compressed data: " + destinationVector1.toString());
    }


}

