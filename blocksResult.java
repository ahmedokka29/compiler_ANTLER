public class input {
    public static void main(String[] args) {

        int x = 3;
        if(x==3 || x==5){
            System.out.println("block");
        }
        if (x==5 || x==3){
            System.out.println("block");

        }
        if(x==5 || x==8 ){
            System.out.println("block");
        }else {System.out.println("ff");}

        if(x==5) {System.out.println("done");	
}

        while (x>0){
            System.out.println("block");

            x--;
        }
        int y = 5;
        for(int i = 0;i < y;i++) {
            System.out.println("block");

        }
        for(int i = 0;i < y;i++) {System.out.println("say hi");	
}

        x = 3;
        int z = 4;
        if(x==3){
            if (z==4){
                System.out.println("yes");
                if(y!=5){
                    System.out.println("no");
                }else{
                    System.out.println("yes");
                }
            }else{
                System.out.println("no");
            }

        }

    }

}

