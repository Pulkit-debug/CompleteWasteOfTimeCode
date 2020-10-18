import java.util.*;
import java.io.*;

public class sol2 {


    static class FastReader 
    { 
        BufferedReader br; 
        StringTokenizer st; 
  
        public FastReader() 
        { 
            br = new BufferedReader(new
                     InputStreamReader(System.in)); 
        } 

        public FastReader(String giveMeFile) throws IOException 
        { 
            File file = new File("input.txt");
            br = new BufferedReader(new FileReader(file));
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

    
    public static boolean[] SES(int n) {
        // SES Stands for SieveOfEratoSthenes
        boolean isPrime[] = new boolean[n+1];
        Arrays.fill(isPrime, true);

        isPrime[0] = false;
        isPrime[1] = false;
        for(int i = 2;i*i<=n;i++) {
            for(int j = 2*i;j<=n;j+=i) {
                isPrime[j] = false; 
            }

        }
        return isPrime;
    }

    public static int gcd(int a, int b) {
        return a % b == 0 ? b : gcd(b, a%b);
    }

    public static long fastPower(long a, long b, long n) {
        long res = 1;
        while(b > 0) {
            if( (b&1) != 0) {
                res = (res % n * a % n) % n;
            }

            a = (a % n * a % n) % n;
            b = b >> 1; // dividing by 2
        }
        return res;
    }


    // static int R = 4; 
    // static int C = 4; 
    
    static int countPaths(int maze[][], int n) 
    { 
        int R = n;
        int C = n;
        if (maze[0][0]==-1) 
            return 0; 
      
        for (int i = 0; i < R; i++) 
        { 
            if (maze[i][0] == 0) 
                maze[i][0] = 1; 
      
           
            else
                break; 
        } 
      
        
        for (int i =1 ; i< C ; i++) 
        { 
            if (maze[0][i] == 0) 
                maze[0][i] = 1; 
      
           
            else
                break; 
        } 
      
     
        for (int i = 1; i < R; i++) 
        { 
            for (int j = 1; j <C ; j++) 
            { 
                
                if (maze[i][j] == -1) 
                    continue; 
      
                if (maze[i - 1][j] > 0) 
                    maze[i][j] = (maze[i][j] +  
                                 maze[i - 1][j]); 
      
                if (maze[i][j - 1] > 0) 
                    maze[i][j] = (maze[i][j] +  
                                  maze[i][j - 1]); 
            } 
        } 
      
       
        return (maze[R - 1][C - 1] > 0) ?  
                maze[R - 1][C - 1] : 0; 
    } 

    public static void main(String[] args) throws IOException {

        // ************** Providing input File
        // File file = new File("input.txt");
        // BufferedReader br = new BufferedReader(new FileReader(file));



        // *************** Input for Online Judges with Buffered Reader
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));




        // ************* Input for Online Judges with Scanner class
        // Reading with Scanner class
        // Scanner sc = new Scanner(file);
        // sc.useDelimiter("\\Z");


        // ***************** Printing Outout to output file.
        // PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter("output.txt")));
        // printWriter.println("something here");
        // printWriter.close();    --->>  // only this close method will flush the output from the buffer to the output file.

        FastReader read = new FastReader("input.txt");


        int t = read.nextInt();

        while(t-- != 0) {
            int n = read.nextInt();

            int wall[][] = new int[n][n];
            int wall2[][] = new int[n][n];
            int wall3[][] = new int[n][n];

            ArrayList<Integer> wall2al = new ArrayList<>();
            ArrayList<Integer> wall3al = new ArrayList<>();


            // int useChar[] = new int[n];
            // int useCharAgain[] = new int[n];

            char useChar[] = new char[n];
            char useCharAgain[] = new char[n];
            String use = "";
            String useAgain = "";
            for(int i = 0;i<n;i++) {
                String s = read.nextLine();
                if(i == n-2) {
                    useChar = s.toCharArray();
                    useCharAgain = s.toCharArray();
                }
                String ss[] = s.split("");
                // char ch[] = s.toCharArray();
                for(int j = 0;j<n;j++) {
                    wall[i][j] = Integer.parseInt(ss[j]);
                }
            }

            
            int wall2Inversion = 0;
            int wall3Inversion = 0;

            for(int i = 0;i<useChar.length;i++) {
                if(useChar[i] == '0') {
                    useChar[i] = '1';
                    wall2Inversion++;
                    wall2al.add(i);
                }
                if(useCharAgain[i] == '1') {
                    useCharAgain[i] = '0';
                    wall3Inversion++;
                    wall3al.add(i);
                }
            }


            for(int i = 0;i<n;i++) {
                for(int j = 0;j<n;j++) {
                    if(i == n-2) {
                        wall2[i][j] = useChar[j];
                        wall3[i][j] = useCharAgain[j];
                        // continue;
                    }
                    else {
                    wall2[i][j] = wall[i][j];
                    wall3[i][j] = wall[i][j];
                }
                    
                }
            }

            for(int i = 0;i<n;i++) {
                for(int j = 0;j<n;j++) {
                    System.out.print(wall2[i][j] + " ");
                }
                System.out.println();
            }


            int wallPath = countPaths(wall, n);
            int wall2Path = 0;
            int wall3Path = 0;
            int with = 0;
            
            if(wallPath > 0) {
                // now transform the wall
                wall2Path = countPaths(wall2, n);
            }

            if(wall2Path > 0) {
                wall3Path = countPaths(wall3, n);
            }


            if(wallPath == 0) System.out.println("0");
            else if(wall2Path == 0) {
                System.out.println(wall2Inversion);
                for(int i = 0;i<wall2Inversion;i++) {
                    System.out.println((n-2) + " " + (wall2al.get(i) + 1));
                }

            }
            else if(wall3Path == 0) {
                System.out.println(wall3Inversion);
                for(int i = 0;i<wall3Inversion;i++) {
                    System.out.println((n-2) + " " + (wall3al.get(i) + 1));
                }
            }

        }
    }

    }
