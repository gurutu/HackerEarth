package com.gap.parkingslot;

import java.io.*;
import java.util.*;
import java.math.*;
 

/**
 * https://www.hackerearth.com/practice/algorithms/graphs/shortest-path-algorithms/practice-problems/algorithm/the-parking-slot-9fac40d6/
 * @author evfandroid
 *
 */

public class Parking {
    public static void main(String args[]) {
        try {
            InputReader in = new InputReader(System.in);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
            int n = in.readInt();
            int m = in.readInt();
            int f = in.readInt();
            Vector<Edge> graph[] = new Vector[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new Vector<>();
            }
            int capacity[] = new int[n];
            for (int i = 0; i < n; i++) {
                capacity[i] = in.readInt();
            }
            for (int i = 0; i < m; i++) {
                int u = in.readInt() - 1;
                int v = in.readInt() - 1;
                long c = in.readLong();
                graph[u].add(new Edge(u, v, c));
                graph[v].add(new Edge(v, u, c));
            }
            long cost[] = new long[n];
            Arrays.fill(cost, Long.MAX_VALUE);
            Queue<IndexValue> pq = new PriorityQueue<IndexValue>(100000, new Comparator<IndexValue>() {
                @Override
                public int compare(IndexValue o1, IndexValue o2) {
                    return Long.compare(o1.v, o2.v);
                }
            });
            pq.add(new IndexValue(0, 0));
            cost[0] = 0;
            boolean vis[] = new boolean[n];
            while(!pq.isEmpty()) {
                IndexValue indexValue = pq.poll();
                int ver = indexValue.index;
                vis[ver] = true;
                for (Edge e: graph[ver]) {
                    if (!vis[e.v] && cost[e.v] > cost[e.u] + e.c) {
                        cost[e.v] = cost[e.u] + e.c;
                        pq.add(new IndexValue(e.v, cost[e.v]));
                    }
                }
            }
            CostIndex[] costIndices = new CostIndex[n];
            for (int i = 0; i < n; i++) {
                costIndices[i] = new CostIndex(i, cost[i]);
            }
            Arrays.sort(costIndices);
            int index = 0;
            int k = in.readInt();
            long answers[] = new long[k];
            for (int i = 0; i < k; i++) {
                if (index >= costIndices.length) {
                    answers[i] = -1;
                }
                else {
                    long ans = costIndices[index].cost + f;
                    answers[i] = ans;
                    capacity[costIndices[index].index]--;
                    if (capacity[costIndices[index].index] == 0) {
                        index++;
                    }
                }
            }
            for (int i = 0; i < k; i++) {
                out.write(Long.toString(answers[i]) + " ");
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    private static class Edge {
        int u, v;
        long c;
 
        public Edge(int u, int v, long c) {
            this.u = u;
            this.v = v;
            this.c = c;
        }
    }
 
    private static class CostIndex implements Comparable<CostIndex> {
        int index;
        long cost;
        public CostIndex(int index, long cost) {
            this.index = index;
            this.cost = cost;
        }
 
        @Override
        public int compareTo(CostIndex o) {
            return Long.compare(this.cost, o.cost);
        }
    }
 
    private static class IndexValue {
        int index;
        long v;
 
        public IndexValue(int index, long v) {
            this.index = index;
            this.v = v;
        }
    }
}
class InputReader {
    private boolean finished = false;
 
    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;
 
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
 
    public int peek() {
        if (numChars == -1)
            return -1;
        if (curChar >= numChars) {
            curChar = 0;
            try {
                numChars = stream.read(buf);
            } catch (IOException e) {
                return -1;
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar];
    }
 
    public int readInt() {
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
 
    public long readLong() {
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
 
    public String readString() {
        int length = readInt();
        if (length < 0)
            return null;
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++)
            bytes[i] = (byte) read();
        try {
            return new String(bytes, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return new String(bytes);
        }
    }
 
    public static boolean isSpaceChar(int c) {
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }
 
    private String readLine0() {
        StringBuffer buf = new StringBuffer();
        int c = read();
        while (c != '\n' && c != -1) {
            if (c != '\r')
                buf.appendCodePoint(c);
            c = read();
        }
        return buf.toString();
    }
 
    public String readLine() {
        String s = readLine0();
        while (s.trim().length() == 0)
            s = readLine0();
        return s;
    }
 
    public String readLine(boolean ignoreEmptyLines) {
        if (ignoreEmptyLines)
            return readLine();
        else
            return readLine0();
    }
 
    public BigInteger readBigInteger() {
        try {
            return new BigInteger(readString());
        } catch (NumberFormatException e) {
            throw new InputMismatchException();
        }
    }
 
    public char readCharacter() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        return (char) c;
    }
 
    public double readDouble() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        double res = 0;
        while (!isSpaceChar(c) && c != '.') {
            if (c == 'e' || c == 'E')
                return res * Math.pow(10, readInt());
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        }
        if (c == '.') {
            c = read();
            double m = 1;
            while (!isSpaceChar(c)) {
                if (c == 'e' || c == 'E')
                    return res * Math.pow(10, readInt());
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                m /= 10;
                res += (c - '0') * m;
                c = read();
            }
        }
        return res * sgn;
    }
 
    public boolean isExhausted() {
        int value;
        while (isSpaceChar(value = peek()) && value != -1)
            read();
        return value == -1;
    }
 
    public String next() {
        return readString();
    }
 
    public boolean readBoolean() {
        return readInt() == 1;
    }
}
