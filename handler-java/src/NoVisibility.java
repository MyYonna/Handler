public class NoVisibility {

	public static boolean ready;
	public static int number;
	
	public static  class ReaderThread extends Thread{

		@Override
		public void run() {
			// TODO Auto-generated method stub
				
				while(!ready){
					System.out.println(System.currentTimeMillis());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(number);
		}
		
	}
	public static void main(String[] args){
		System.out.println("ѭ����ʼ"+System.currentTimeMillis());
		for(int i=0;i<1000;i++){
			
			new ReaderThread().start();;
		}
		ready = true;
		number = 900;
		System.out.println("ѭ����ֲ"+System.currentTimeMillis());
	}
	

}
