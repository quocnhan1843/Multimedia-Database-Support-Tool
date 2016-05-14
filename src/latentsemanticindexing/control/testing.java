package latentsemanticindexing.control;

public class testing {

    static final double[] a
	= new double[] {1,2,3,4};
    static final double[] b
	= new double[] {4,3,2,1};

    public static void main(String[] args) throws Exception {
        System.out.println(CosinDistance.getDistance(a,b));
    }
}