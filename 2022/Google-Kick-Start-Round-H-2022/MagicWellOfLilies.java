import java.util.Scanner;

class Solution{
    public static int solve(int n){
        if(n<=13)
            return n;
        int x=n/2;
        int ans=n;
        for(int i=x;i>=3;i--){
            if((i+6)>n)
                continue;
            int count=i+6,rem=n-(i*2);
            while(rem>0){
                if(rem<i){
                    count+=rem;
                    rem=0;
                }else{
                    count+=2;
                    rem-=i;
                }
            }
            ans=Math.min(ans,count);
        }
        return ans;
    }
    public static void main(String[] agrs){
        Scanner sc=new Scanner(System.in);
        int testSize=sc.nextInt();
        for(int testCase=1;testCase<=testSize;testCase++){
            int n=sc.nextInt();
            int ans=solve(n);
            System.out.println("Case #"+testCase+": "+ans);
        }
    }
}