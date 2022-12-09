import java.util.*;

class Solution{
    public static int findCountByVertex(int ver,Map<Integer,List<Integer>> hm,int[] arr,int count, Map<Integer,Integer> vertexCost){
        if(!hm.containsKey(ver))
            return count;
        List<Integer> arrList=hm.get(ver);
        for(int x:arrList){
            if(arr[x-1]<arr[ver-1]){
                if(vertexCost.containsKey(x))
                    count+=vertexCost.get(x);
                else
                    count+=findCountByVertex(x,hm,arr,1,vertexCost);
            }
        }
        vertexCost.put(ver,count);
        return count;
    }
    public static int solve(int N,int[] arr, Map<Integer,List<Integer>> hm){
        int min=arr[0],secondMin=Integer.MAX_VALUE;
        boolean allSame=true;
        for(int i=0;i<N;i++){
            if(arr[i]<min){
                secondMin=arr[i];
                min=arr[i];
                allSame=false;
            }else if(arr[i]<secondMin && arr[i]!=min){
                secondMin=arr[i];
                allSame=false;
            }
            else if(arr[i]!=min)
                allSame=false;
        }
        if(allSame)
            return 1;
        int count=2;
        Map<Integer,Integer> vertexCost=new HashMap<>();
        for(int i=0;i<N;i++){
            int power=arr[i],ver=i+1;
            if(power==min || power==secondMin)
                continue;
            int c=findCountByVertex(ver,hm,arr,1,vertexCost);
            if(c>count)
                count=c;
        }
        return count;
    }
    public static void main(String[] agrs){
        Scanner sc=new Scanner(System.in);
        int testCases=sc.nextInt();
        for(int testCase=1;testCase<=testCases;testCase++){
            int N=sc.nextInt();
            int[] arr=new int[N];
            for(int i=0;i<N;i++)
                arr[i]=sc.nextInt();
            Map<Integer,List<Integer>> hm=new HashMap<>();
            for(int i=0;i<N-1;i++){
                int x=sc.nextInt();
                int y=sc.nextInt();
                List<Integer> connections=null;
                if(hm.containsKey(x)){
                    connections=hm.get(x);
                }else{
                    connections=new ArrayList<>();
                }
                connections.add(y);
                hm.put(x,connections);

                if(hm.containsKey(y)){
                    connections=hm.get(y);

                }else{
                    connections=new ArrayList<>();
                }
                connections.add(x);
                hm.put(y,connections);
            }
            int ans=solve(N,arr,hm);
            System.out.println("Case #"+testCase+": "+ans);
        }
    }
}