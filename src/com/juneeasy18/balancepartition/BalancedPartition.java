package com.juneeasy18.balancepartition;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Stack;
 
/**
 *https://www.hackerearth.com/practice/algorithms/sorting/bubble-sort/practice-problems/algorithm/balanced-partition-818edecd/
 * @author evfandroid
 */
 public class BalancedPartition{
 
    
    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;
 
        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
 
        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
 
        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }
 
        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');
 
            if (neg)
                return -ret;
            return ret;
        }
 
        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }
 
        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
 
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
 
            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }
 
            if (neg)
                return -ret;
            return ret;
        }
 
        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }
 
        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
 
        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }
    
    public static void main(String[] args) throws IOException {
           Reader s=new Reader();
          int t=s.nextInt();
        StringBuilder sb=new StringBuilder();
        outer:for(int i=0;i<t;i++){
            int temp[]=new int[4001];
            int n=s.nextInt();
            for(int j=0;j<n;j++){
                int x=s.nextInt();
                int y=s.nextInt();
                int h=s.nextInt();
                
                temp[2000+(y-x)]+=h;
            }
            
            
            int sumTemp[]=new int[4001];
            int lasttermsSum=0;
            for(int j=0;j<=4000;j++){
                sumTemp[j]=temp[j]+lasttermsSum;
                lasttermsSum=sumTemp[j];
            }
            int total=sumTemp[4000];
            boolean ans=fun(0,4000,temp,sumTemp,total);
            System.out.println(ans?"YES":"NO");
            
            
        }       
    }
    
      private static boolean fun(int first, int last, int[] temp, int[] sumTemp, int total) {
          int mid=(first+last)/2;
          int j=mid;
          int peehceKa=sumTemp[j]-temp[j];
                int aageKa=total-sumTemp[j];
                if(peehceKa==aageKa){
                    //System.out.println("YES");
                    return true;
                }
               
                //jB center ko include kiya peeche vale m
                int peehceKa1=sumTemp[j];
                int aageKa1=total-sumTemp[j];
                if(peehceKa1==aageKa1){
                    //System.out.println("YES");
                    return true;
                }
                
                //jb line vale ko include kiya aage vale m
                 int peehceKa2=sumTemp[j]-temp[j];
                int aageKa2=total-sumTemp[j]+temp[j];
                if(peehceKa2==aageKa2){
                    //System.out.println("YES");
                    return true;
                }
                
                if(first==last){
                    return false;
                }
          
             
                if(peehceKa<aageKa){
                return fun(mid+1, last, temp, sumTemp, total);
                }
                else{
                    return fun(first, mid, temp, sumTemp, total);
                }
          
    }
  
}
