package dasho.jep434;

import java.lang.foreign.SymbolLookup;
import java.lang.foreign.Linker;
import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.Arena;
import java.lang.invoke.MethodHandle;
import static java.lang.foreign.ValueLayout.ADDRESS;
import static java.lang.foreign.ValueLayout.JAVA_LONG;

public  class jep434_FFM {
    public static long JEP434() throws Throwable{
        //Get a lookup object for commonly used libraries
        SymbolLookup standardLibrary = Linker.nativeLinker().defaultLookup();

        //Get a handle to the "strlen" function in the C standard library
        MethodHandle strlen = Linker.nativeLinker().downcallHandle(
                standardLibrary.find("strlen").orElseThrow(),
                FunctionDescriptor.of(JAVA_LONG, ADDRESS));

        //Convert Java String to C string and store it in off-heap memory
        try(Arena offHeap = Arena.openConfined()) {
            MemorySegment string = offHeap.allocateUtf8String("Codicological");

            //Invoke the foreign function
            long length = (long) strlen.invoke(string);
            return length;
        }
        // 5. Off-heap memory is deallocated at end of try-with-resources
    }
}