package it.polito.ezshop.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import it.polito.ezshop.data.BalanceOperation;
import it.polito.ezshop.data.ProductType;
import it.polito.ezshop.data.User;

public class FileReaderAndWriter {

    // reads the users actually registered in the systems and return them in a Map
    // one user per-line
    // user fields are the following, in the specified order
    // <id>;<username>;<password>;<role>
    // field separator is ;
    static public Map<Integer, User> UsersReader() {
        Map<Integer, User> users = new HashMap<Integer, User>();

        File inputFile = new File("./src/main/java/it/polito/ezshop/model/txt/users.txt");
        Scanner s = null;
        try {
            s = new Scanner(inputFile);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] res = line.split(";");
                User u = new UserClass(Integer.parseInt(res[0]), res[1], res[2], res[3]);
                users.put(Integer.parseInt(res[0]), u);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }
        return users;
    }

    // writes the user list received as parameter in the users.txt file
    // one user per-line
    // user fields are the following, in the specified order:
    // <id>;<username>;<password>;<role>
    // field separator is ;
    static public Boolean UsersWriter(Map<Integer, User> users) {
        String x = "";
        for (User u : users.values()) {
            x = x + u.getId() + ";" + u.getUsername() + ";" + u.getPassword() + ";" + u.getRole() + "\n";
        }
        File outputFile = new File("./src/main/java/it/polito/ezshop/model/txt/users.txt");
        PrintWriter out = null;
        try {
            out = new PrintWriter(outputFile);
            out.print(x);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (out != null)
                out.close();
        }

        return true;
    }

    // reads the users actually registered in the systems and return them in a Map
    // one user per-line
    // user fields are the following, in the specified order
    // <id>;<customerName>;<customerCard>;<points>
    // field separator is ;
    static public HashMap<Integer, it.polito.ezshop.model.Customer> CustomersReader() {
        HashMap<Integer, it.polito.ezshop.model.Customer> customers = new HashMap<Integer, it.polito.ezshop.model.Customer>();

        File inputFile = new File("./src/main/java/it/polito/ezshop/model/txt/customers.txt");
        Scanner s = null;
        try {
            s = new Scanner(inputFile);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] res = line.split(";");
                it.polito.ezshop.model.Customer c = new it.polito.ezshop.model.Customer(Integer.parseInt(res[0]),
                        res[1], res[2], Integer.parseInt(res[3]));
                customers.put(Integer.parseInt(res[0]), c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }
        return customers;

    }

    /* writing customers to file */
    static public Boolean CustomersWriter(HashMap<Integer, it.polito.ezshop.model.Customer> customers) {
        String x = "";
        for (it.polito.ezshop.model.Customer c : customers.values()) {
            x = x + c.getId() + ";" + c.getCustomerName() + ";" + c.getCustomerCard() + ";" + c.getPoints() + "\n";
        }
        File outputFile = new File("./src/main/java/it/polito/ezshop/model/txt/customers.txt");
        PrintWriter out = null;
        try {
            out = new PrintWriter(outputFile);
            out.print(x);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (out != null)
                out.close();
        }

        return true;
    }

    // reads the balanceOperations actually registered in the system and return them
    // in a Map
    // one balanceOperation per-line
    // balanceOperation fields are the following, in the specified order
    // <balanceId>;<date>;<money>;<type>
    // field separator is ;
    static public Map<Integer, BalanceOperation> BalanceOperationsReader() {
        Map<Integer, BalanceOperation> bos = new HashMap<Integer, BalanceOperation>();

        File inputFile = new File("./src/main/java/it/polito/ezshop/model/txt/balanceoperations.txt");
        Scanner s = null;
        try {
            s = new Scanner(inputFile);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] res = line.split(";");
                BalanceOperation bo = new BalanceOperationClass(Integer.parseInt(res[0]), LocalDate.parse(res[1]),
                        Double.parseDouble(res[2]), res[3]);
                bos.put(Integer.parseInt(res[0]), bo);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }
        return bos;
    }

    // writes the balanceOperations Map received as parameter in the
    // balanceoperations.txt file
    // one balance operation per-line
    // balanceOperation fields are the following, in the specified order:
    // <balanceId>;<date>;<money>;<type>
    // field separator is ;
    static public Boolean balanceOperationsWriter(Map<Integer, BalanceOperation> balanceOperations) {
        String x = "";
        for (BalanceOperation bo : balanceOperations.values()) {
            x = x + bo.getBalanceId() + ";" + bo.getDate().toString() + ";" + bo.getMoney() + ";" + bo.getType() + "\n";
        }
        File outputFile = new File("./src/main/java/it/polito/ezshop/model/txt/balanceoperations.txt");
        PrintWriter out = null;
        try {
            out = new PrintWriter(outputFile);
            out.print(x);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (out != null)
                out.close();
        }
        return true;
    }

    // reads the saletransactions actually registered in the system and return them
    // in a Map
    // one saletransaction per-line
    // saletransaction fields are the following, in the specified order
    // <ticketNumber>;<price>;<discountrate>
    // field separator is ;
    static public Map<Integer, SaleTransactionClass> SaleTransactionsReader() {
        Map<Integer, SaleTransactionClass> st = new HashMap<Integer, SaleTransactionClass>();

        File inputFile = new File("./src/main/java/it/polito/ezshop/model/txt/saletransactions.txt");
        Scanner s = null;
        try {
            s = new Scanner(inputFile);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] res = line.split(";");
                SaleTransactionClass t = new SaleTransactionClass(Integer.parseInt(res[0]), Double.parseDouble(res[1]),
                        Double.parseDouble(res[2]), res[3]);
                st.put(Integer.parseInt(res[0]), t);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }
        return st;
    }

    // writes the salestransaction Map received as parameter in the
    // salestransactions.txt file
    // one saletransaction per-line
    // sale transaction fields are the following, in the specified order:
    // <ticketNumber>;<price>;<discountrate>
    // field separator is ;
    // writes ONLY CLOSED AND PAID transactions
    static public Boolean saletransactionsWriter(Map<Integer, SaleTransactionClass> salesTransactions) {
        String x = "";
        for (SaleTransactionClass s : salesTransactions.values()) {
            if (!s.getState().equals("Open"))
                x = x + s.getTicketNumber() + ";" + s.getPrice() + ";" + s.getDiscountRate() + ";" + s.getState()
                        + "\n";
        }
        File outputFile = new File("./src/main/java/it/polito/ezshop/model/txt/saletransactions.txt");
        PrintWriter out = null;
        try {
            out = new PrintWriter(outputFile);
            out.print(x);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (out != null)
                out.close();
        }
        return true;
    }

    // reads the ticketentries actually registered in the system and return them in
    // a List
    // one ticketentry per-line
    // ticketentry fields are the following, in the specified order
    // <transactionid>;<barcode>;<productdescription>;<amount>;<priceperunit>;<discountrate>
    // field separator is ;
    static public List<TicketEntryClass> ticketEntriesReader() {
        List<TicketEntryClass> te = new ArrayList<TicketEntryClass>();

        File inputFile = new File("./src/main/java/it/polito/ezshop/model/txt/ticketentries.txt");
        Scanner s = null;
        try {
            s = new Scanner(inputFile);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] res = line.split(";");
                TicketEntryClass t = new TicketEntryClass(Integer.parseInt(res[0]), res[1], res[2],
                        Integer.parseInt(res[3]), Double.parseDouble(res[4]), Double.parseDouble(res[5]));
                te.add(t);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }
        return te;
    }

    // writes the ticketentries list received as parameter in the tciketentries.txt
    // file
    // one ticketentry per-line
    // ticket entries fields are the following, in the specified order:
    // <transactionid>;<barcode>;<productdescription>;<amount>;<priceperunit>;<discountrate>
    // field separator is ;
    static public Boolean ticketEntriesWriter(List<TicketEntryClass> ticketEntries) {
        String x = "";
        for (TicketEntryClass t : ticketEntries) {
            x = x + t.getTransactionId() + ";" + t.getBarCode() + ";" + t.getProductDescription() + ";" + t.getAmount()
                    + ";" + t.getPricePerUnit() + ";" + t.getDiscountRate() + "\n";
        }
        File outputFile = new File("./src/main/java/it/polito/ezshop/model/txt/ticketentries.txt");
        PrintWriter out = null;
        try {
            out = new PrintWriter(outputFile);
            out.print(x);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (out != null)
                out.close();
        }
        return true;
    }

    // reads the orders actually registered in the systems and return them in a Map
    // one order per-line
    // Order fields are the following, in the specified order
    // <orderId>;<balanceId>;<ProductCode>;<pricePerUnit>;<quantity>;<status>
    // field separator is ;
    static public HashMap<Integer, OrderClass> OrdersReader() {
        HashMap<Integer, OrderClass> orders = new HashMap<Integer, OrderClass>();

        File inputFile = new File("./src/main/java/it/polito/ezshop/model/txt/orders.txt");
        Scanner s = null;
        try {
            s = new Scanner(inputFile);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] res = line.split(";");
                OrderClass order = new OrderClass(Integer.parseInt(res[0]), Integer.parseInt(res[1]), res[2],
                        Double.parseDouble(res[3]), Integer.parseInt(res[4]), res[5]);
                orders.put(Integer.parseInt(res[0]), order);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }
        return orders;
    }

    /* writing orders to file */
    static public Boolean OrdersWriter(Map<Integer, OrderClass> orders) {
        String x = "";
        for (OrderClass o : orders.values()) {
            x = x + o.getOrderId() + ";" + o.getBalanceId() + ";" + o.getProductCode() + ";" + o.getPricePerUnit() + ";"
                    + o.getQuantity() + ";" + o.getStatus() + "\n";
        }
        File outputFile = new File("./src/main/java/it/polito/ezshop/model/txt/orders.txt");
        PrintWriter out = null;
        try {
            out = new PrintWriter(outputFile);
            out.print(x);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (out != null)
                out.close();
        }

        return true;
    }

    static public Map<Integer, ProductType> InventoryReader() {
        Map<Integer, ProductType> inventory = new HashMap<Integer, ProductType>();

        File inputFile = new File("./src/main/java/it/polito/ezshop/model/txt/producttypes.txt");
        Scanner s = null;
        try {
            s = new Scanner(inputFile);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] res = line.split(";");
                ProductTypeClass product = new ProductTypeClass(Integer.parseInt(res[0]), Integer.parseInt(res[1]),
                        res[2], res[3], res[4], Double.parseDouble(res[5]), res[6]);
                inventory.put(Integer.parseInt(res[0]), product);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }
        return inventory;
    }

    /*
     * reading credit cards from file. Credit cards are stored with the following
     * format: <creditCardNumber>;<creditCardBalance>
     */
    static public HashMap<String, CreditCardClass> CreditCardsReader() {
        HashMap<String, CreditCardClass> creditCards = new HashMap<String, CreditCardClass>();

        File inputFile = new File("./src/main/java/it/polito/ezshop/model/txt/creditcards.txt");
        Scanner s = null;
        try {
            s = new Scanner(inputFile);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (!line.contains("#")) {
                    String[] res = line.split(";");
                    CreditCardClass cc = new CreditCardClass(res[0], Double.parseDouble(res[1]));
                    creditCards.put(cc.getCreditCardId(), cc);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }
        return creditCards;

    }
    
    /* writing creditcards to file */
    static public Boolean CreditCardsWriter(Map<String, CreditCardClass> creditCards) {
        String x = "#Do not delete the lines preceded by an \"#\" and do not modify the first three credit cards\r\n"
        		+ "#since they will be used in the acceptance tests.\r\n"
        		+ "#The lines preceded by an \"#\" must be ignored.\r\n"
        		+ "#Here you can add all the credit card numbers you need with their balance. The format MUST be :\r\n"
        		+ "#<creditCardNumber>;<balance>\r\n"
                + "4485370086510891;150.00\r\n"
                + "5100293991053009;10.00\r\n"
                + "4716258050958645;0.00";
        for (CreditCardClass c : creditCards.values()) {
            x = x + c.getCreditCardId() + ";" + c.getBalance() + "\n";
        }
        File outputFile = new File("./src/main/java/it/polito/ezshop/model/txt/creditcards.txt");
        PrintWriter out = null;
        try {
            out = new PrintWriter(outputFile);
            out.print(x);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (out != null)
                out.close();
        }

        return true;
    }

    static public Boolean InventoryWriter(Map<Integer, ProductType> inventory) {
        String x = "";
        for (ProductType p : inventory.values()) {
            x = x + p.getId() + ";" + p.getQuantity() + ";" + p.getLocation() + ";" + p.getProductDescription() + ";"
                    + p.getBarCode() + ";" + p.getPricePerUnit() + ";" + p.getNote() + "\n";
        }
        File outputFile = new File("./src/main/java/it/polito/ezshop/model/txt/producttypes.txt");
        PrintWriter out = null;
        try {
            out = new PrintWriter(outputFile);
            out.print(x);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (out != null)
                out.close();
        }

        return true;
    }


    static public Map<Integer, ReturnTransaction> ReturnsReader() {
        Map<Integer, ReturnTransaction> returns = new HashMap<Integer, ReturnTransaction>();

        File inputFile = new File("./src/main/java/it/polito/ezshop/model/txt/returns.txt");
        Scanner s = null;

        try {
            s = new Scanner(inputFile);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] res = line.split(";");
                String[] map = res[3].replace("{", "").replace("}", "").replace("=", ",").split(",");
                Map<Integer, Integer> returnedProducts = new HashMap<Integer, Integer>();
                for (int i = 0; i < map.length; i = i + 2) {
                    if(!map[i].equals("") && !map[i+1].equals(""))
                        returnedProducts.put(Integer.parseInt(map[i]), Integer.parseInt(map[i + 1]));
                }
                ReturnTransaction rt = new ReturnTransaction(Integer.parseInt(res[0]), Integer.parseInt(res[1]),
                        returnedProducts, res [2]);
                returns.put(Integer.parseInt(res[0]), rt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }

        return returns;
    }

    public static boolean ReturnsWriter(Map<Integer, ReturnTransaction> returns) {

        String x = "";
        for (ReturnTransaction rt : returns.values()) {
            String y = "{";
            for (Integer key : rt.getReturnedProduct().keySet()) {
                y = y + key + "=" + rt.getReturnedProduct().get(key) + ",";
            }
            y = y.substring(0, y.length() - 1); // Remove the ',' at the end of y
            y = y + "}";

            x = x + rt.getID() + ";" + rt.getSaleTransactionID() + ";" + rt.getStatus() + ";" + y + "\n";
        }

        File outputFile = new File("./src/main/java/it/polito/ezshop/model/txt/returns.txt");
        PrintWriter out = null;

        try {
            out = new PrintWriter(outputFile);
            out.print(x);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (out != null)
                out.close();
        }

        return true;
    }

    static public Boolean RFIDWriter(Map<String, Product> products) {
        String x = "";
        for (Product p : products.values()) {
            x = x + p.getRFID() + ";" + p.getProductId() + "\n";
        }
        File outputFile = new File("./src/main/java/it/polito/ezshop/model/txt/products.txt");
        PrintWriter out = null;
        try {
            out = new PrintWriter(outputFile);
            out.print(x);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (out != null)
                out.close();
        }

        return true;
    }

    static public Map<String, Product> RFIDReader() {
        Map<String, Product> products = new HashMap<String, Product>();

        File inputFile = new File("./src/main/java/it/polito/ezshop/model/txt/products.txt");
        Scanner s = null;
        try {
            s = new Scanner(inputFile);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] res = line.split(";");
                Product p = new Product(res[0], Integer.parseInt(res[1]));
                products.put(res[0], p);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (s != null) {
                s.close();
            }
        }
        return products;
    }
}
