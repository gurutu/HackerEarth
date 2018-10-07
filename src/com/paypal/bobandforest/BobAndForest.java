package com.paypal.bobandforest;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
 
/**
 * https://www.hackerearth.com/practice/algorithms/dynamic-programming/2-dimensional/practice-problems/algorithm/bob-and-forest-2571cd1f/
 * @author evfandroid
 *
4 4
*..*
.***
****
.***
3
1
2
3
 */
public class BobAndForest {
	public static void main(String args[] ) {
		try(Reader br = new Reader()) {
			int n = br.nextInt();
			int m = br.nextInt();
			char[][] forest = new char[n][m];
			for(int i=0; i<n; i++) {
				forest[i] = br.readLine().toCharArray();
			}
			int[][] squareMatrix = findSquares(forest);
			int[] squares = new int[Math.min(n, m)+1];
			for(int i=0; i < n; i++) {
				for(int j=0; j < m; j++) {
					if(squareMatrix[i][j] > 0) {
						squares[squareMatrix[i][j]]++;
					}
				}
			}
			for(int i=squares.length-1; i>=2; i--) {
				squares[i-1] += squares[i];
			}
			for(int i=1; i<squares.length; i++) {
				squares[i] += squares[i-1];
			}
			int numberOfQueries = br.nextInt();
			StringBuilder sb = new StringBuilder();
			int length = squares.length -1;
			int query;
			for(int i=0; i<numberOfQueries; i++) {
				query = br.nextInt();
				sb.append(squares[query > length ? length: query]).append("\n");
			}
			System.out.println(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
 
	}
 
	private static int[][] findSquares(char[][] forest) {
		int i,j;
		int rows = forest.length;
		int columns = forest[0].length;
		int[][] squares = new int[rows][columns];
 
		for(i = 0; i < rows; i++) {
			if(forest[i][0] == '*') {
				squares[i][0] = 1;
			} else {
				squares[i][0] = 0;
			}
		}
		
		for(j = 0; j < columns; j++) {
			if(forest[0][j] == '*') {
				squares[0][j] = 1;
			} else {
				squares[0][j] = 0;
			}
		}
		
		for(i = 1; i < rows; i++) {
			for(j = 1; j < columns; j++) {
				if(forest[i][j] == '*') {
					squares[i][j] = Math.min(squares[i][j-1],Math.min(squares[i-1][j], squares[i-1][j-1])) + 1;
				} else {
					squares[i][j] = 0;
				}	
			}    
		}
 
		return squares;
	}
 
	static class Reader implements AutoCloseable
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
			byte[] buf = new byte[2048]; // line length
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
}