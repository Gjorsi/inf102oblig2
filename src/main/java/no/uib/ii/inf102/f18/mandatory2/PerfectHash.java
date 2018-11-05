package no.uib.ii.inf102.f18.mandatory2;

import java.util.HashSet;

public class PerfectHash {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        final int M = 500;
        int n = io.getInt();
        
        String[] data = new String[n];
        for (int i=0; i<n ; i++) {
            data[i] = io.getWord();
        }
        
        HashSet<Integer> occupied = new HashSet<>();
        boolean collision = false;
        
        //try m from n up to 500
        for (int m=n; m<=M ; m++) {
            
            //try a from 2 to 399
            x:for (int a=2; a<400 ; a++) {
                occupied.clear();
                collision = false;
                
                //hash all strings and check for collisions
                for (int k=0; k<n ; k++) {
                    int c = hash(data[k], a, m);
                    if (occupied.contains(c)) {
                        //collision
                        continue x;
                    } else if (occupied.size() == n) {
                        foundSolution(a, m, io);
                        return;
                    } else {
                        occupied.add(c);
                    }
                }
                
                if (!collision) {
                    foundSolution(a, m, io);
                    return;
                }
            }
        }   
        io.close();
    }
    
    public static void foundSolution(int a, int m, Kattio io) {
        io.print(a);
        io.print(" ");
        io.print(m);
        io.close();
    }
    
    public static int hash(String s, int a, int M) {
        int hash = 0;
        
        for (int i=0; i<s.length() ; i++) {
            hash = (a*hash + s.charAt(i))%M;
        }
        return hash;
    }
}
