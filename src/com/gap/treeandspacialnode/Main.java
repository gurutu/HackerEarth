package com.gap.treeandspacialnode;

import java.util.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
 
class FastScanner {
    
    private InputStream mIs;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;
 
    public FastScanner() {
        this(System.in);
    }
 
    public FastScanner(InputStream is) {
        mIs = is;
    }
 
    public int read() {
        if (numChars == -1)
            throw new InputMismatchException();
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = mIs.read(buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }
 
    public String nextLine() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isEndOfLine(c));
        return res.toString();
    }
 
    public String nextString() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = read();
        } while (!isSpaceChar(c));
        return res.toString();
    }
 
    public long nextLong() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        long res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }
 
    public int nextInt() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }
 
    public boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }
 
    public boolean isEndOfLine(int c) {
        return c == '\n' || c == '\r' || c == -1;
    
    }   
 
}
 public class Main implements Runnable{
    Main(){}
    static int n,m;
    static int[][] G;
    static int[] A;
    static boolean ans[];
    static int[] present;
    static void gList(int[] from, int[] to)
    {
		G = new int[n][];
		
		int[] freq = new int[n];
		for(int i=0;i<m;i++)
		{
			freq[from[i]]++;
		}
		for(int i=0;i<n;i++)
			G[i] = new int[freq[i]];
		
		for(int i=0;i<m;i++)
		{
			G[from[i]][--freq[from[i]]] = to[i];
		}
	
    }

static void dfs(int u, int par,boolean flag)
{
    vis[u] = true;
    if(++present[A[u]]>1||flag)
    {
        flag = true;
        ans[u] = true;
    }
    
    for(int v: G[u])
    {
        if(v!=par&&!vis[v])
        {
            if(flag)
                ans[v] = true;
                
           dfs(v,u,flag);
        }
    }
    present[A[u]]--;
    
}
static boolean[] vis;
    public static void main(String args[] ) throws Exception {
     new Thread(null, new Main(), "Main", 1<<26).start();
        
    }public void run(){
        FastScanner s = new FastScanner();
        StringBuilder sb = new StringBuilder();
        n = s.nextInt();
         m = n-1;
         A = new int[n];
         for(int i = 0;i<n;i++)
            A[i] = s.nextInt();
        ans = new boolean[n];
        present = new int[1000*1000+1];    
        int[] from = new int[2*m];
        int[] to = new int[2*m];
        vis = new boolean[n];
        int p = 0;
        for(int i=1;i<=n-1;i++)
        {
            int u = s.nextInt()-1;
            int v = s.nextInt()-1;
            from[p] = u;
            to[p] = v;
            p++;
            from[p] = v;
            to[p] = u;
            p++;
        }
        m = p;
        gList(from,to);
        dfs(0,-1,false);
        int d = 0;
        for(boolean v:ans)
            if(!v)
                d++;
        System.out.println(d);
    }
}
