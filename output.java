import java.io.FileWriter;
import java.io.IOException;
public class output {
    public static void main(String[] args)  throws IOException {
     FileWriter writer = new FileWriter("./output.txt");
// block number 1
        writer.write("1\n");


        int x = 3;
        if(x==3 || x==5){// block number 2
        writer.write("2\n");

            System.out.println("block");
        }
        if (x==5 || x==3){// block number 3
        writer.write("3\n");

            System.out.println("block");

        }
        if(x==5 || x==8 ){// block number 4
        writer.write("4\n");

            System.out.println("block");
        }else {// block number 5
        writer.write("5\n");
System.out.println("ff");}

        if(x==5) {// block number 6
        writer.write("6\n");
System.out.println("done");	
}

        while (x>0){// block number 7
        writer.write("7\n");

            System.out.println("block");

            x--;
        }
        int y = 5;
        for(int i = 0;i < y;i++) {// block number 8
        writer.write("8\n");

            System.out.println("block");

        }
        for(int i = 0;i < y;i++) {// block number 9
        writer.write("9\n");
System.out.println("say hi");	
}

        x = 3;
        int z = 4;
        if(x==3){// block number 10
        writer.write("10\n");

            if (z==4){// block number 11
        writer.write("11\n");

                System.out.println("yes");
                if(y!=5){// block number 12
        writer.write("12\n");

                    System.out.println("no");
                }else{// block number 13
        writer.write("13\n");

                    System.out.println("yes");
                }
            }else{// block number 14
        writer.write("14\n");

                System.out.println("no");
            }

        }

    writer.close();
}

}

