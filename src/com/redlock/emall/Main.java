package com.redlock.emall;

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.util.Collections;
import java.util.ArrayList;
import java.io.InputStream;
 
/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * https://www.hackerearth.com/practice/data-structures/advanced-data-structures/fenwick-binary-indexed-trees/practice-problems/algorithm/buy-em-all-d972d5c9/
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskC solver = new TaskC();
        solver.solve(1, in, out);
        out.close();
    }
 
    static class TaskC {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            long k = in.nextLong();
            long a[] = new long[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
            }
            ArrayList<Long> even = new ArrayList<>();
            ArrayList<Long> ect = new ArrayList<>();
            even.add(Long.MIN_VALUE);
            ect.add(0L);
            ArrayList<Long> odd = new ArrayList<>();
            ArrayList<Long> oct = new ArrayList<>();
            odd.add(Long.MIN_VALUE);
            oct.add(0L);
            long ans = 0, sumo = 0, sume = 0;
 
            for (int i = 0; i < n; i++) {
                if (i % 2 == 0) {
                    // complete odd length
                    if (i != 0) {
                        int id = Collections.binarySearch(odd, (sumo + a[i] * a[i - 1] - k) - 1);
                        if (id < 0) {
                            id = -id - 2;
                        }
                        ans += oct.get(oct.size() - 1) - oct.get(id);
                        //System.out.println(i+" "+ans+" "+oct.get(id));
                        id = Collections.binarySearch(even, (sume + a[i] - k) - 1);
                        if (id < 0) {
                            id = -id - 2;
                        }
                        ans += ect.get(ect.size() - 1) - ect.get(id);
                        //System.out.println(i+" "+ans+" "+ect.get(id));
                        sumo += a[i] * a[i - 1];
                    } else {
                        sumo += a[i];
                    }
                    if (sumo == odd.get(odd.size() - 1)) {
                        oct.set(oct.size() - 1, oct.get(oct.size() - 1) + 1);
                    } else {
                        odd.add(sumo);
                        oct.add(oct.get(oct.size() - 1) + 1);
                    }
                    if (sume + a[i] <= k) {
                        ans++;
                    }
 
                } else {
                    // complete even length
 
                    int id = Collections.binarySearch(even, sume + a[i] * a[i - 1] - k - 1);
                    if (id < 0) {
                        id = -id - 2;
                    }
                    ans += ect.get(ect.size() - 1) - ect.get(id);
                    id = Collections.binarySearch(odd, sumo + a[i] - k - 1);
                    if (id < 0) {
                        id = -id - 2;
                    }
                    ans += oct.get(oct.size() - 1) - oct.get(id);
 
                    sume += a[i] * a[i - 1];
                    if (sume == even.get(even.size() - 1)) {
                        ect.set(ect.size() - 1, ect.get(ect.size() - 1) + 1);
                    } else {
                        even.add(sume);
                        ect.add(ect.get(ect.size() - 1) + 1);
                    }
                    if (sume <= k) {
                        ans++;
                    }
                }
            }
            out.println(ans);
        }
 
    }
 
    static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private InputReader.SpaceCharFilter filter;
 
        public InputReader(InputStream stream) {
            this.stream = stream;
        }
 
        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();
 
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
 
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
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
            }
            while (!isSpaceChar(c));
 
            return res * sgn;
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
            }
            while (!isSpaceChar(c));
            return res * sgn;
        }
 
        public boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }
 
        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
 
        }
 
    }
}
