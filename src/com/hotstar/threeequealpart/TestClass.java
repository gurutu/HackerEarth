package com.hotstar.threeequealpart;

//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes
import java.util.*;
/**https://www.hackerearth.com/practice/algorithms/greedy/basics-of-greedy-algorithms/practice-problems/algorithm/threequal-parts-ecf91f02/
 * 2
5
1 0 1 0 1
5
1 1 0 1 1
 * @author evfandroid
 *
 */
public class TestClass {
  
  public static void main(String args[] ) throws Exception {
      
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int T = Integer.parseInt(br.readLine());        
      int N;
      while(T-- > 0) {
          N = Integer.parseInt(br.readLine());
          String[] parts = br.readLine().split(" ");
          
          int total1s = 0;
          boolean binArray[] = new boolean[N];
          for(int i = 0; i<N; i++) {
              binArray[i] = parts[i].equals("1");
              if(binArray[i]) {
                  total1s++;
              }
          }
          
          long equalInteger = -1;
          if(N < 3) {
              // Not possible
          } else if(total1s == 0) {
            equalInteger = 0;  
          } else if(total1s % 3 == 0) {
              // It's possible to make 3 sub-arrays
              
              int onesInEachSubArray = total1s / 3;
              int starts[] = new int[3]; // Index of first 1 in each sub array
              int ends[] = new int[3]; // Index of last 1 in each sub array
              int which = 0; // which sub array
              int index = 0; 
              
              
              // This while loop fills starts[], ends[]
              while(which < 3) {
                  // Ignore preceding 0s
                  while(binArray[index] == false) {
                      index++;
                  }
                  
                  int count = 1;
                  starts[which] = index;
                  ends[which] = index;
                  index++;
                  while(count < onesInEachSubArray) {
                      if(binArray[index] == true) {
                          count++;
                          ends[which] = index;
                      }
                      index++;
                  }
                  which++; // Next sub array
              }
              
              // Before compairing actual arrays. Check for zeros.
              int requiredZeros = N-1 - ends[2];
              
              if(starts[1] - ends[0] - 1 >= requiredZeros && starts[2] - ends[1] - 1 >= requiredZeros) {
                  
                  // Now check for length of 1's 
                  if(ends[0] - starts[0] == ends[1] - starts[1] && ends[1] - starts[1] == ends[2] - starts[2]) {
                      // Now check for sequential
                      int len = ends[0] - starts[0] + 1;
                      boolean matched = true;
                      for(int i = 0; i < len; i++) {
                          if (false == (binArray[starts[0] + i] == binArray[starts[1] + i] 
                                          && binArray[starts[1] + i] == binArray[starts[2] + i])) {
                              matched = false;
                              break;
                          }
                      }
                      
                      // All 1's sub arrays matched
                      if(matched) {
                          // Calculate
                          int start = starts[2], end = N-1;
                          long answer = 0;
                          long pow2 = 1;
                          for(int i = end; i >= start; i--) {
                              if(binArray[i]) {
                                  answer = (answer + pow2) % 1000000007;
                              }
                              pow2 = (pow2 * 2) % 1000000007;
                          }
                          equalInteger = answer;
                      }
                  }
              } 
              
          }
          System.out.println(equalInteger);
          
      }
  }
}
