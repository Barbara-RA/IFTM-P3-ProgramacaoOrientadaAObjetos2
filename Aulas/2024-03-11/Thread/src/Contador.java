public class Contador implements Runnable {
    @Override
    public void run(){
        int i=0;
        while (true) {
            i++;
            System.out.println(i);
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}



// public class Contador extends Thread {
//     @Override
//     public void run(){
//         int i=0;
//         while (true) {
//             i++;
//             System.out.println(i);
//             try{
//                 Thread.sleep(1000);
//             }catch(InterruptedException e){
//                 e.printStackTrace();
//             }
//         }
//     }
// }
