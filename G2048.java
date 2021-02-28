import java.util.Scanner;
import java.util.Random;
class G2048{
    public static int table[][] = new int [4][4];
    public static boolean merge[][] = new boolean[4][4];
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Random ran = new Random();
        table[ran.nextInt(4)][ran.nextInt(4)] = 2;
        table[ran.nextInt(4)][ran.nextInt(4)] = 2;
        do{
            for (int i = 0 ; i < 4 ; i++)
                for (int j = 0 ; j < 4 ; j++) 
                    merge[i][j] = false; 
            
            printBoard();
            char move = input.next().charAt(0);  
            switch (move){
                case 'w':
                    up();
                    break;
                case 's':
                    down();
                    break;
                case 'd':
                    right();
                    break;
                case 'a':
                    left();
                    break;
            }
            int xhome , yhome;
            do{
                xhome = ran.nextInt(4);
                yhome = ran.nextInt(4);
            }while(table[xhome][yhome] != 0);
            int number = ran.nextInt(14);
            if(number % 13 == 0){
                table[xhome][yhome] = 4;
            }
            else {
                table[xhome][yhome] = 2;
            }
        }while(checkEnd());
        cls();
        printBoard();
        System.out.println("\nGeme Over!"); 
    }
    public static void cls() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    } 
    public static void printBoard(){
        cls();
        for(int i = 0 ; i <= 8 ; i++){
            if(i%2 != 0)
                System.out.print("| ");
            for(int j = 0 ; j < 4 ; j++){
                if(i%2 == 0){
                    System.out.print("------");
                }
                else{
                    if(table[i/2][j] >= 1000){
                        System.out.print(table[i/2][j]+"| ");
                    }
                    else if(table[i/2][j] >= 100){
                        System.out.print(table[i/2][j]+" | ");
                    }
                    else if(table[i/2][j] >= 10){
                        System.out.print(" "+table[i/2][j]+" | ");
                    }
                    else{
                        if (table[i/2][j] != 0)
                            System.out.print(" "+table[i/2][j]+"  | ");
                        else
                            System.out.print("    | ");
                    }
                }
            }
            System.out.println();
        }
    }
    public static boolean checkEnd(){
        for(int i = 0 ; i < 4 ; i++){
            for(int j = 0 ; j < 4 ; j++){
                if(table[i][j] == 2048){
                    cls();
                    printBoard();
                    System.out.println("\nYou Won!");
                    System.exit(0);
                }
                if((i != 3 && table[i][j] == table[i+1][j])
                    ||(j != 3 && table[i][j] == table[i][j+1]))
                    return true;
                else if((i != 3 && table[i+1][j] == 0 )
                    || (j != 3 && table[i][j+1] == 0))
                    return true;
            }
        }
        return false;
    }
    public static void down(){
        for(int j = 0 ; j < 4 ; j++){
            for(int i = 2 ; i >=0 ; i--){
                if(table[i][j] != 0){
                    int k = i;
                    while(k < 3 && table[k+1][j] == 0){
                        table[k+1][j] = table[k][j];
                        table[k][j] = 0;
                        k++;
                    }
                    if(k != 3 && table[k][j] == table[k+1][j] && !merge[k+1][j] && !merge[k][j]){
                        table[k][j] = 0;
                        table[k+1][j] *= 2;
                        merge[k+1][j] = true;
                    }
                }
                if(i!=2 && table[i+1][j] == table[i+2][j] && table[i+1][j] != 0 
                    && !merge[i+1][j] && !merge[i+2][j]){
                    table[i+1][j] = 0;
                    table[i+2][j] *= 2;
                    merge[i+1][j] = true;
                }
            }
        }
    }
    public static void up(){
        for(int j = 0 ; j < 4 ; j++){
            for(int i = 1 ; i < 4 ; i++){
                if(table[i][j] != 0){
                    int k = i;
                    while(k > 0 && table[k-1][j] == 0){
                        table[k-1][j] = table[k][j];
                        table[k][j] = 0;
                        k--;
                    }
                    if(k != 0 && table[k][j] == table[k-1][j] && !merge[k-1][j] && !merge[k][j]){
                        table[k][j] = 0;
                        table[k-1][j] *= 2;
                        merge[k-1][j] = true;
                    }
                }
                if(i!=1 && table[i-1][j] == table[i-2][j] && table[i-1][j] != 0
                     && !merge[i-2][j] && !merge[i-1][j]){
                    table[i-1][j] = 0;
                    table[i-2][j] *= 2;
                    merge[i-2][j] = true;
                }
            }
        }
    }
    public static void right(){
        for(int i = 0 ; i < 4 ; i++){
            for(int j = 2 ; j >= 0 ; j--){
                if(table[i][j] != 0){
                    int k = j;
                    while(k < 3 && table[i][k+1] == 0){
                        table[i][k+1] = table[i][k];
                        table[i][k] = 0;
                        k++;
                    }
                    if(k != 3 && table[i][k] == table[i][k+1] && !merge[i][k+1] && !merge[i][k]){
                        table[i][k] = 0;
                        table[i][k+1] *= 2;
                        merge[i][k+1] = true;
                    }
                }
                if(j!=2 && table[i][j+1] == table[i][j+2] && table[i][j+1] != 0
                    && !merge[i][j+2] && !merge[i][j+1]){
                    table[i][j+1] = 0;
                    table[i][j+2] *= 2;
                    merge[i][j+2] = true;
                }
            }
        }
    }
    public static void left(){
        for(int i = 0 ; i < 4 ; i++){
            for(int j = 1 ; j < 4 ; j++){
                int k = j;
                if(table[i][j] != 0){
                    while(k > 0 && table[i][k-1] == 0){
                        table[i][k-1] = table[i][k];
                        table[i][k] = 0;
                        k--;
                    }
                    if(k != 0 && table[i][k] == table[i][k-1] && !merge[i][k-1] && !merge[i][k]){
                        table[i][k] = 0;
                        table[i][k-1] *= 2;
                        merge[i][k-1] = true;
                    }
                }
                if(j != 1 && table[i][j-1] == table[i][j-2] && table[i][j-1] != 0
                     && !merge[i][j-2] && !merge[i][j-1]){
                    table[i][j-1] = 0;
                    table[i][j-2] *= 2;
                    merge[i][j-2] = true;
                }
            }
        }
    }
} 
