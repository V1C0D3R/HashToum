import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private static ArrayList<Integer> weights;

    private static ArrayList<Warehouse> warehouses;
    private static ArrayList<Order> orders;
    private static ArrayList<Drone> drones;

	public static void parseInput() {

		BufferedReader br = null;

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader("/Users/victor/Downloads/busy_day.in"));

			sCurrentLine = br.readLine();

            //First Line
            String[] firstParams = sCurrentLine.split(" ");
            int rows = Integer.parseInt(firstParams[0]);
            int columns = Integer.parseInt(firstParams[1]);
            int dronesNum = Integer.parseInt(firstParams[2]);
            int turns = Integer.parseInt(firstParams[3]);
            int maxPayload = Integer.parseInt(firstParams[4]);

            sCurrentLine = br.readLine();
            //Number of product types
            int numProdTypes = Integer.parseInt(sCurrentLine.split(" ")[0]);

            sCurrentLine = br.readLine();
            //product weights

            String[] prodWeights = sCurrentLine.split(" ");
            for(int i=0; i<prodWeights.length; i++){
                weights.add(Integer.parseInt(prodWeights[i]));
            }

            sCurrentLine = br.readLine();
            //Number Warehouse
            int numWH = Integer.parseInt(sCurrentLine.split(" ")[0]);

            //Warehouses creation
            for (int whid=0; whid< numWH; whid++)   {
                //Create WH with coords
                sCurrentLine = br.readLine();
                int row = Integer.parseInt(sCurrentLine.split(" ")[0]);
                int column = Integer.parseInt(sCurrentLine.split(" ")[1]);
                Warehouse wh = new Warehouse(row, column, numProdTypes);

                //Add items to WH
                sCurrentLine = br.readLine();
                String[] itemsString = sCurrentLine.split(" ");
                for(int i=0; i<numProdTypes; i++){
                    wh.addItems(i,Integer.parseInt(itemsString[i]));
                }

                warehouses.add(whid, wh);
            }

            sCurrentLine = br.readLine();
            //Orders number
            int numOrders = Integer.parseInt(sCurrentLine.split(" ")[0]);

            //Order creation
            for (int orderId=0; orderId<numOrders; orderId++) {
                //Coords
                sCurrentLine = br.readLine();
                int row = Integer.parseInt(sCurrentLine.split(" ")[0]);
                int column = Integer.parseInt(sCurrentLine.split(" ")[1]);

                //Num Items in Order
                sCurrentLine = br.readLine();
                int numOrderItems = Integer.parseInt(sCurrentLine.split(" ")[0]);

                //Types of Items
                sCurrentLine = br.readLine();
                String[] itemsString = sCurrentLine.split(" ");
                ArrayList<Integer> items = new ArrayList<Integer>(numProdTypes);
                for(int i=0; i<numOrderItems; i++){
                    int itemId = Integer.parseInt(itemsString[i]);
                    items.add(itemId, items.get(itemId)+1);
                }

                Order order = new Order(row, column, items);
            }

            //Init Drones
            for (int idDrone = 0; idDrone<dronesNum; idDrone++) {
                drones.add(new Drone());
            }

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println("ERROR : input line not used -> "+sCurrentLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

    public static ArrayList<Warehouse> getWareHouses()  {
        return warehouses;
    }

    public static ArrayList<Order> getOrders()  {
        return orders;
    }

    public static ArrayList<Drone> getDrones()  {
        return drones;
    }
}