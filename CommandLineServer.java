public class CommandLineServer { 
    public static void main(String [] args) { 
	StockDataBase allowedUsers = new StockDataBase("stocks.csv","Symbol","Security Name","Price");
	MainServer mainServer = new MainServer(MainServer.BASE_PORT,
					       allowedUsers); 
	mainServer.server_loop(); 
    }
}
	
