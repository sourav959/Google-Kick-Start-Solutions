import java.util.Scanner;

class Solution {
    public static long solve(long[] arr, char[] direction, int N, long L) {
        long current = 0;
        long ans = 0;
        char oldDirection = direction[0];
        for (int i = 0; i < N; i++) {
            char curDirection = direction[i];
            if (oldDirection == curDirection) {
                if (oldDirection == 'C') {
                    long diff = L - current;
                    if (arr[i] >= diff) {
                        ans += 1;
                        ans += ((arr[i] - diff) / L);
                        current = (current + arr[i]) % L;
                    } else {
                        current = (current + arr[i]) % L;
                    }
                } else {
                    long curPos = Math.abs(current);
                    long diff = L - curPos;
                    if (arr[i] >= diff) {
                        ans += 1;
                        ans += ((arr[i] - diff) / L);
                        current = (current - arr[i]) % L;
                    } else {
                        current = (current - arr[i]) % L;
                    }
                }
            } else {
                long curPos = Math.abs(current);
                long diff = curPos;
                if (arr[i] >= diff) {
                    long x = arr[i] - diff;
                    ans += (x / L);
                    oldDirection = curDirection;
                }
                if (curDirection == 'C')
                    current = (current + arr[i]) % L;
                else
                    current = (current - arr[i]) % L;
            }

            current = current % L;
        }

        return ans;


    }

    public static void main(String[] agrs) {
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        for (int testCase = 1; testCase <= test; test++) {
            long L = sc.nextLong();
            int N = sc.nextInt();
            long[] arr = new long[N];
            char[] direction = new char[N];
            for (int i = 0; i < N; i++) {
                arr[i] = sc.nextLong();
                direction[i] = sc.next().charAt(0);
            }
            long ans = solve(arr, direction, N, L);
            System.out.println("Case #" + testCase + ": " + ans);
        }
    }
}