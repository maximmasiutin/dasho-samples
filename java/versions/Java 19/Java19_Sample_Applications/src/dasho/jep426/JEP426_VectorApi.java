package dasho.jep426;

public class JEP426_VectorApi {
    public void methodCall() {
    Method_Implementation sample=new Method_Implementation();
            sample.laneWise();
            sample.jep426_MemorySegment();
            sample.compressExpand();
    int val= sample.compress(1001,0101 );
        System.out.println("Compress= "+val);
    int val1= sample.expand(1001,0101 );
        System.out.println("Expand= " +val1);

    }
}
