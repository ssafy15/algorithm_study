package main.java.study.a15_hash.d01.서로_다른_부분_문자열의_수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main_조은진 {
    public static final int P1=37;
    public static final int P2=41;
    public static final long M1=10000000007L;
    public static final long M2=10000000009L;

    public static int toInt(char c) {
        return c-'a'+1;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int n=s.length();
        int[] alpha=new int[n];
        for(int i=0; i<n; i++) {
            alpha[i]=toInt(s.charAt(i));
        }
        long res=0;

        long[] nPow1=new long[n];
        long[] nPow2=new long[n];

        nPow1[0]=1; nPow2[0]=1;
        for(int i=1; i<n; i++) {
            nPow1[i]=(nPow1[i-1]*P1)%M1;
            nPow2[i]=(nPow2[i-1]*P2)%M2;
        }

        HashSet<Long> set=new HashSet<>();

        for(int i=1; i<=n; i++) {
            long hash1=0;
            long hash2=0;

            set.clear();

            for(int j=0; j<i; j++) {
                hash1=(hash1* P1 + alpha[j]) % M1;
                hash2=(hash2* P2 + alpha[j]) % M2;
            }
            long combinedHash=(hash1<<32)|(hash2&0xFFFFFFFFFL);

            set.add(combinedHash);

            for(int j=1; j<=n-i; j++) {
                hash1=hash1*P1-alpha[j-1]*nPow1[i]+alpha[j+i-1]%M1;
                if(hash1<0) hash1+=M1;

                hash2=hash2*P2-alpha[j-1]*nPow2[i]+alpha[j+i-1]%M2;
                if(hash2<0) hash2+=M2;

                combinedHash=(hash1<<32)|(hash2&0xFFFFFFFFFL);

                set.add(combinedHash);
            }
            res+=set.size();
        }

        System.out.println(res);
        br.close();
    }
}
