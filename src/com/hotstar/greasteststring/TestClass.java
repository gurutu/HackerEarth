package com.hotstar.greasteststring;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
 
/**
 * https://www.hackerearth.com/practice/basic-programming/implementation/basics-of-implementation/practice-problems/algorithm/greatest-string-fcf3e37c/
 * @author evfandroid
 *2
abcde
3
xyzwu
0
 */
 
public class TestClass {
    public static void main(String args[] ) throws Exception {
        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            solve(br.readLine(), Integer.parseInt(br.readLine()));
        }
        
        // Write your code here
 
    }
    
    public static void solve(String s, int ops) {
        char a[] = s.toCharArray();
        for (int i = 0; i < a.length && ops > 0; i++) {
            switch(a[i]) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                    a[i] += 1;
                    ops--;
                    break;
            }
        }
        System.out.println(new String(a));
        
    }
}