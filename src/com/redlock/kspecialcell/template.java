package com.redlock.kspecialcell;
import java.util.*;

import java.io.*;

/**
 * https://www.hackerearth.com/practice/math/combinatorics/basics-of-combinatorics/practice-problems/algorithm/shinos-k-special-cells-c8538ebb/
 * @author evfandroid
 *
 */

public class template
{
    static class FastReader
    {
        BufferedReader br;
        StringTokenizer st;
 
        public FastReader()
        {
            br = new BufferedReader(new
                     InputStreamReader(System.in));
        }
 
        String next()
        {
            while (st == null || !st.hasMoreElements())
            {
                try
                {
                    st = new StringTokenizer(br.readLine());
                }
                catch (IOException  e)
                {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
 
        int nextInt()
        {
            return Integer.parseInt(next());
        }
 
        long nextLong()
        {
            return Long.parseLong(next());
        }
 
        double nextDouble()
        {
            return Double.parseDouble(next());
        }
 
        String nextLine()
        {
            String str = "";
            try
            {
                str = br.readLine();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return str;
        }
    }
    static int mod=1000000007;
 
    static void Fact(long a[])
           {
                for(int i=2;i<a.length;i++)
                    a[i]=(i*a[i-1])%mod;
           }
   static long mod_inv(long x)
            {
                   int y=mod-2;
                   long res=1;
                     while(y>0)
                       {
                             if(y%2==1)
                               res=(res*x)%mod;
                             y=y/2;
                             x=(x*x)%mod;
                      }
                 return(res);
            }
 
    static void Fact_inv(long a[])
            {
                for(int i=99999;i>=0;i--)  
                   a[i]=(a[i+1]*(i+1))%mod;             
            }
 
    public static void main(String[] args)
       {
             FastReader sc=new FastReader();
               int t=sc.nextInt();
               long fact[]=new long[200001];
                 fact[0]=1;
                 fact[1]=1;
                 Fact(fact);
              
             long f_i[]=new long[100001];
             f_i[100000]=mod_inv(fact[100000]);
               Fact_inv(f_i);
                          
             while(t-- >0)
               {
                     int n=sc.nextInt();
                     int m=sc.nextInt(); 
                     int k=sc.nextInt();
                  long res=0;
                      for(int i=1;i<=k;i++)
                       {
                                int x=sc.nextInt();
                                int y=sc.nextInt();
                                int h=sc.nextInt();
                             long t1=(fact[x+y-2]*fact[n+m-x-y])%mod;
                                  t1=(t1*f_i[x-1])%mod;
                                  t1=(t1*f_i[y-1])%mod;
                                  t1=(t1*f_i[n-x])%mod;
                                  t1=(t1*f_i[m-y])%mod;
                                  t1=(t1*h)%mod;
                            res=(res+t1)%mod;
                       }
                   System.out.println(res);
             } 
     }
}