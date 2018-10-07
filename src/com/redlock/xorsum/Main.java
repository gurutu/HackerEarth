package com.redlock.xorsum;

import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.io.IOException;
import java.io.InputStream;
 
/**
 * Built using CHelper plug-in
 * Actual solution is at the top
 * https://www.hackerearth.com/challenge/hiring/redlock-developer-hiring-challenge/algorithm/xor-sum-1-af648068/
 */
public class Main {
    public static void main(String[] args) {
        InputStream inputStream = System.in;
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        TaskB solver = new TaskB();
        solver.solve(1, in, out);
        out.close();
    }
 
    static class TaskB {
        public void solve(int testNumber, InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int bit1[][] = new int[n + 1][40];
            int bit0[][] = new int[n + 1][40];
            for (int i = 1; i <= n; i++) {
                long temp = in.nextLong();
                for (int j = 0; j < 40; j++) {
                    if ((temp & 1) == 1) {
                        bit1[i][j]++;
                    } else {
                        bit0[i][j]++;
                    }
                    temp = temp >> 1;
                }
            }
 
            for (int i = 1; i <= n; i++) {
                for (int j = 0; j < 40; j++) {
                    bit1[i][j] += bit1[i - 1][j];
                    bit0[i][j] += bit0[i - 1][j];
                }
            }
            int q = in.nextInt();
            in.nextInt();
            int m = (int) 1e9 + 7;
            while (q-- > 0) {
                int l = in.nextInt() - 1, r = in.nextInt();
                long sum = 0;
                for (int i = 0; i < 40; i++) {
                    long noOfZero = bit0[r][i] - bit0[l][i];
                    long noOfOne = bit1[r][i] - bit1[l][i];
                    long bit = (1 << i) % m;
                    sum = (sum + (((((noOfZero * (noOfZero - 1)) / 2) * noOfOne) % m * bit)) % m) % m;
                    sum = (sum + ((noOfOne * (noOfOne - 1) * (noOfOne - 2)) / 6) % m * bit) % m;
                }
                out.println(sum);
            }
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
