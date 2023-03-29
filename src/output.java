import java.io.FileWriter;
import java.io.IOException;
public class output {
    public static void main(String[] args)  throws IOException {// block number 1
       FileWriter writer = new FileWriter("output.txt");
        writer.write("block number " +1+" is visited \n");


        int x = 3;
        if(x==5){// block number 2
        writer.write("block number " +2+" is visited \n");


        }else if (x==8){// block number 3
        writer.write("block number " +3+" is visited \n");


        }else{// block number 4
        writer.write("block number " +4+" is visited \n");


        }

        if(x==3){// block number 5
        writer.write("block number " +5+" is visited \n");


        }
        while (x>0){// block number 6
        writer.write("block number " +6+" is visited \n");


            x--;
        }
        int y = 2;
        for(int i = 0;i<y;i++) {// block number 7
        writer.write("block number " +7+" is visited \n");


        }
    writer.close();
}
}

