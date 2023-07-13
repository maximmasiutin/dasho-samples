package dasho.jep424;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.MemorySession;
import java.lang.foreign.SymbolLookup;
import java.lang.foreign.ValueLayout;
import java.lang.invoke.MethodHandle;

public class JEP424_FFM {
    public static long JEP424(String input) throws Throwable {
        MemorySession session = MemorySession.openConfined();
        //Allocating off heap memory
        MemorySegment string = session.allocateUtf8String(input);

        //Linking and calling native functions
        //Obtain an instance of the native linker
        Linker linker = Linker.nativeLinker();

        //Locating address of the native function
        SymbolLookup standardLibrary = linker.defaultLookup();
        MemorySegment strlen_adder = standardLibrary.lookup("strlen").get();

        //description of the native function
        FunctionDescriptor strlen_sig = FunctionDescriptor.of(
                ValueLayout.JAVA_LONG, ValueLayout.ADDRESS);

        //downcall handle for the native function
        MethodHandle strlen = linker.downcallHandle(strlen_adder, strlen_sig);

        //Calling native function directly from Java
        return (long) strlen.invoke(string);
    }
}