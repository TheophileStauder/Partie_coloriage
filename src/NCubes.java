public class NCubes {

    private Graph nCubes;

    public NCubes(int N){
        nCubes = new Graph((int) Math.pow(2,N));
        char one = '1';
        char zero = '0';
        for(int i = 0; i < Math.pow(2,N)  ; i++ ){
            String binaryString = Integer.toBinaryString(i);
            for (int j = 0; j < binaryString.length(); j++){
                char c = binaryString.charAt(j);
                StringBuilder grayCode;
                if(c== one){
                    grayCode = new StringBuilder(binaryString);
                    grayCode.setCharAt(j, zero);

                }else{
                    grayCode = new StringBuilder(binaryString);
                    grayCode.setCharAt(j, one);

                }
                int grayCodeInt = Integer.parseInt(grayCode.toString(), 2);;
                if(nCubes.getSpecificEdge(i,grayCodeInt).equals(new Edge(-1,-1))){
                    nCubes.addEdge(new Edge(i,grayCodeInt));
                }
            }
        }
    }

    public Graph getnCubes() {
        return nCubes;
    }
}
