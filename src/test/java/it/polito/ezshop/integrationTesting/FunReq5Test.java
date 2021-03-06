package it.polito.ezshop.integrationTesting;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Test;
/*
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
*/
import it.polito.ezshop.data.EZShop;
import it.polito.ezshop.exceptions.*;


public class FunReq5Test {

	@AfterClass
    public static void clearEzShop(){
        EZShop e = new EZShop();
        e.reset();
    }
	
	
	/*
	 * static EZShop e;
	@BeforeAll
	public static void init() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException{
		e = new EZShop();
	}
	
	@BeforeEach
	public void createCustomers() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, InvalidCustomerNameException, UnauthorizedException {
		
		EZShop e = new EZShop();
		e.reset();
		e.createUser("validUser", "pass", "Cashier");
		e.createUser("validAdministrator", "pass", "Administrator");
		e.createUser("validManager", "pass", "ShopManager");
		e.login("validUser", "pass");
		e.defineCustomer("cus1");
		e.defineCustomer("cus2");
		e.defineCustomer("cus3");
		
	}
	*/
	@Test 
	public void defineCustomerTest() throws InvalidCustomerNameException, UnauthorizedException, InvalidUsernameException, InvalidPasswordException, InvalidRoleException {
		/*start BeforeEach*/
		EZShop e = new EZShop();
		e.reset();
		e.createUser("validUser", "pass", "Cashier");
		e.createUser("validAdministrator", "pass", "Administrator");
		e.createUser("validManager", "pass", "ShopManager");
		e.login("validUser", "pass");
		e.defineCustomer("cus1");
		e.defineCustomer("cus2");
		e.defineCustomer("cus3");
		/*end BeforeEach*/
		
		e.reset();
		e.createUser("validUser", "pass", "Cashier");
		e.createUser("validAdministrator", "pass", "Administrator");
		e.createUser("validManager", "pass", "ShopManager");

		e.logout();
		assertThrows(UnauthorizedException.class, () -> {e.defineCustomer("customerName");});
		
		e.login("validUser", "pass");
		assertThrows(InvalidCustomerNameException.class,() -> {e.defineCustomer(null);});
		assertThrows(InvalidCustomerNameException.class,() -> {e.defineCustomer("");});
		
		
		assertTrue(e.defineCustomer("Name1")==1);
		assertTrue(e.defineCustomer("Name2")==2);
		assertTrue(e.defineCustomer("Name1")==-1);
		e.logout();
		
		e.login("validManager", "pass");
		e.defineCustomer("Name110");
		e.defineCustomer("Name123");
		e.logout();
		
		e.login("validAdministrator","pass");
		e.defineCustomer("qwe");
		e.defineCustomer("qwerty");
		e.logout();
		
	}
	
	@Test 
	public void createCardTest() throws InvalidUsernameException, InvalidPasswordException, InvalidRoleException, InvalidCustomerNameException, UnauthorizedException {
		/*start BeforeEach*/
		EZShop e = new EZShop();
		e.reset();
		e.createUser("validUser", "pass", "Cashier");
		e.createUser("validAdministrator", "pass", "Administrator");
		e.createUser("validManager", "pass", "ShopManager");
		e.login("validUser", "pass");
		e.defineCustomer("cus1");
		e.defineCustomer("cus2");
		e.defineCustomer("cus3");
		/*end BeforeEach*/
		
		e.logout();
		assertThrows(UnauthorizedException.class, () -> {e.createCard();});
		
		e.login("validUser", "pass");
		e.createCard();
		e.logout();
		
		e.login("validAdministrator", "pass");
		e.createCard();
		e.logout();
		
		e.login("validManager", "pass");
		e.createCard();
		e.logout();
		
	}
	
	
	@Test 
	public void modifyCustomerTest() throws InvalidUsernameException, InvalidPasswordException, UnauthorizedException, InvalidCustomerNameException, InvalidCustomerCardException, InvalidCustomerIdException, InvalidRoleException {
		/*start BeforeEach*/
		EZShop e = new EZShop();
		e.reset();
		e.createUser("validUser", "pass", "Cashier");
		e.createUser("validAdministrator", "pass", "Administrator");
		e.createUser("validManager", "pass", "ShopManager");
		e.login("validUser", "pass");
		e.defineCustomer("cus1");
		e.defineCustomer("cus2");
		e.defineCustomer("cus3");
		/*end BeforeEach*/
		
		e.logout();
		assertThrows(UnauthorizedException.class, () -> {e.modifyCustomer(null, null, null);});
		
		e.login("validUser", "pass");
		assertThrows(InvalidCustomerNameException.class,() -> {e.modifyCustomer(1,null,null);});
		assertThrows(InvalidCustomerNameException.class,() -> {e.modifyCustomer(2,"",null);});
		assertThrows(InvalidCustomerCardException.class,() -> {e.modifyCustomer(1,"cus10","12345678");});
		assertThrows(InvalidCustomerCardException.class,() -> {e.modifyCustomer(1,"cus10","qwerty7890");});

		String customerCard = e.createCard();
		/*assign card to a user and change name*/
		assertTrue(e.modifyCustomer(1,"cus10",customerCard));
		assertTrue(e.getCustomer(1).getCustomerName().equals("cus10"));
		assertTrue(e.getCustomer(1).getCustomerCard().equals(customerCard));
		
		/*try to assign card already assigned to another user*/
		assertFalse(e.modifyCustomer(2,"cus5",customerCard));
		
		/*modify only the name not the card*/
		assertTrue(e.modifyCustomer(1, "cus8", null));
		assertTrue(e.getCustomer(1).getCustomerCard().equals(customerCard));
		
		/*remove the card*/
		assertTrue(e.modifyCustomer(1,"cus8",""));
		assertTrue(e.getCustomer(1).getCustomerCard().equals(""));
		assertTrue(e.getCustomer(1).getCustomerName().equals("cus8"));
		
		/*try index out of range*/
		assertThrows(InvalidCustomerIdException.class, ()-> {e.modifyCustomer(0, "qweqwe", customerCard);}); //it was an assert false
		assertFalse(e.modifyCustomer(10, "qweqwe", customerCard));
		assertThrows(InvalidCustomerIdException.class, ()-> {e.modifyCustomer(-1, "qweqwe", customerCard);}); //it was an assert false
		e.logout();
		
		e.login("validUser", "pass");
		e.modifyCustomer(3,"cus12",customerCard);;
		e.logout();
		
		e.login("validAdministrator", "pass");
		e.modifyCustomer(3,"cus12",customerCard);
		e.logout();
		
		e.login("validManager", "pass");
		e.modifyCustomer(3,"cus12",customerCard);
		e.logout();
	}
	
	@Test 
	public void deleteCustomerTest() throws InvalidUsernameException, InvalidPasswordException, InvalidCustomerIdException, UnauthorizedException, InvalidRoleException, InvalidCustomerNameException {
		/*start BeforeEach*/
		EZShop e = new EZShop();
		e.reset();
		e.createUser("validUser", "pass", "Cashier");
		e.createUser("validAdministrator", "pass", "Administrator");
		e.createUser("validManager", "pass", "ShopManager");
		e.login("validUser", "pass");
		e.defineCustomer("cus1");
		e.defineCustomer("cus2");
		e.defineCustomer("cus3");
		/*end BeforeEach*/
		
		e.logout();
		assertThrows(UnauthorizedException.class, () -> {e.deleteCustomer(null);});
		
		e.login("validUser", "pass");
		assertThrows(InvalidCustomerIdException.class,() -> {e.deleteCustomer(null);});
		assertThrows(InvalidCustomerIdException.class,() -> {e.deleteCustomer(0);});
		assertThrows(InvalidCustomerIdException.class,() -> {e.deleteCustomer(-1);});
		
		assertFalse(e.deleteCustomer(5));
		assertTrue(e.deleteCustomer(1));
		assertTrue(e.deleteCustomer(2));
		assertTrue(e.deleteCustomer(3));

		e.logout();
		
		e.login("validUser", "pass");
		e.deleteCustomer(5);
		e.logout();
		
		e.login("validAdministrator", "pass");
		e.deleteCustomer(6);
		e.logout();
		
		e.login("validManager", "pass");
		e.deleteCustomer(6);
		e.logout();
	}
	
	@Test 
	public void getCustomerTest() throws InvalidUsernameException, InvalidPasswordException, InvalidCustomerIdException, UnauthorizedException, InvalidRoleException, InvalidCustomerNameException {
		/*start BeforeEach*/
		EZShop e = new EZShop();
		e.reset();
		e.createUser("validUser", "pass", "Cashier");
		e.createUser("validAdministrator", "pass", "Administrator");
		e.createUser("validManager", "pass", "ShopManager");
		e.login("validUser", "pass");
		e.defineCustomer("cus1");
		e.defineCustomer("cus2");
		e.defineCustomer("cus3");
		/*end BeforeEach*/
		
		e.logout();
		assertThrows(UnauthorizedException.class, () -> {e.getCustomer(5);});
		
		e.login("validUser", "pass");
		assertThrows(InvalidCustomerIdException.class,() -> {e.getCustomer(null);});
		assertThrows(InvalidCustomerIdException.class,() -> {e.getCustomer(0);});
		assertThrows(InvalidCustomerIdException.class,() -> {e.getCustomer(-1);});
		
		assertNull(e.getCustomer(5));
		assertTrue(e.getCustomer(1).getId()==1);
		assertTrue(e.getCustomer(2).getId()==2);
		
		e.login("validUser", "pass");
		e.getCustomer(5);
		e.logout();
		
		e.login("validAdministrator", "pass");
		e.getCustomer(6);
		e.logout();
		
		e.login("validManager", "pass");
		e.getCustomer(6);
		e.logout();
	}
	
	@Test 
	public void getAllCustomersTest() throws InvalidUsernameException, InvalidPasswordException, UnauthorizedException, InvalidRoleException, InvalidCustomerNameException {
		/*start BeforeEach*/
		EZShop e = new EZShop();
		e.reset();
		e.createUser("validUser", "pass", "Cashier");
		e.createUser("validAdministrator", "pass", "Administrator");
		e.createUser("validManager", "pass", "ShopManager");
		e.login("validUser", "pass");
		e.defineCustomer("cus1");
		e.defineCustomer("cus2");
		e.defineCustomer("cus3");
		/*end BeforeEach*/
		
		e.logout();
		assertThrows(UnauthorizedException.class, () -> {e.getAllCustomers();});
		
		e.login("validUser", "pass");
		assertTrue(e.getAllCustomers().size()==3);
		e.logout();
		
		e.login("validUser", "pass");
		e.getAllCustomers();
		e.logout();
		
		e.login("validAdministrator", "pass");
		e.getAllCustomers();
		e.logout();
		
		e.login("validManager", "pass");
		e.getAllCustomers();
		e.logout();
	}
	
	@Test 
	public void attachCardToCustomerTest() throws InvalidUsernameException, InvalidPasswordException, UnauthorizedException, InvalidCustomerIdException, InvalidCustomerCardException, InvalidRoleException, InvalidCustomerNameException {
		/*start BeforeEach*/
		EZShop e = new EZShop();
		e.reset();
		e.createUser("validUser", "pass", "Cashier");
		e.createUser("validAdministrator", "pass", "Administrator");
		e.createUser("validManager", "pass", "ShopManager");
		e.login("validUser", "pass");
		e.defineCustomer("cus1");
		e.defineCustomer("cus2");
		e.defineCustomer("cus3");
		/*end BeforeEach*/
		
		e.logout();
		assertThrows(UnauthorizedException.class, () -> {e.attachCardToCustomer(null,null);});
		
		e.login("validUser", "pass");
		
		String card = e.createCard();
		assertThrows(InvalidCustomerIdException.class,() -> {e.attachCardToCustomer(card,null);});
		assertThrows(InvalidCustomerIdException.class,() -> {e.attachCardToCustomer(card,0);});
		assertThrows(InvalidCustomerIdException.class,() -> {e.attachCardToCustomer(card,-1);});
		assertThrows(InvalidCustomerCardException.class,() -> {e.attachCardToCustomer("12345678",1);});
		assertThrows(InvalidCustomerCardException.class,() -> {e.attachCardToCustomer("qwerty7890",1);});
		assertThrows(InvalidCustomerCardException.class,() -> {e.attachCardToCustomer(null,1);});
		assertThrows(InvalidCustomerCardException.class,() -> {e.attachCardToCustomer("",1);});
		
		assertFalse(e.attachCardToCustomer(card, 5));
		
		String card2 = e.createCard();

		assertTrue(e.attachCardToCustomer(card, 1));
		assertTrue(e.attachCardToCustomer(card2, 2));
		assertFalse(e.attachCardToCustomer(card, 2));
		assertTrue(e.attachCardToCustomer(card, 1));
				
		e.logout();
		
		e.login("validUser", "pass");
		e.attachCardToCustomer(card, 1);
		e.logout();
		
		e.login("validAdministrator", "pass");
		e.attachCardToCustomer(card,1);
		e.logout();
		
		e.login("validManager", "pass");
		e.attachCardToCustomer(card,3);
		e.logout();
		
	}
	
	@Test 
	public void modifyPointsOnCardTest() throws InvalidUsernameException, InvalidPasswordException, UnauthorizedException, InvalidCustomerIdException, InvalidCustomerCardException, InvalidRoleException, InvalidCustomerNameException {
		/*start BeforeEach*/
		EZShop e = new EZShop();
		e.reset();
		e.createUser("validUser", "pass", "Cashier");
		e.createUser("validAdministrator", "pass", "Administrator");
		e.createUser("validManager", "pass", "ShopManager");
		e.login("validUser", "pass");
		e.defineCustomer("cus1");
		e.defineCustomer("cus2");
		e.defineCustomer("cus3");
		/*end BeforeEach*/
		
		e.logout();
		assertThrows(UnauthorizedException.class, () -> {e.modifyPointsOnCard("random", 4);});
		
		e.login("validUser", "pass");
		String card = e.createCard();
		String card2 = e.createCard();
		assertThrows(InvalidCustomerCardException.class,() -> {e.modifyPointsOnCard(null, 4);});
		assertThrows(InvalidCustomerCardException.class,() -> {e.modifyPointsOnCard("12345678", 4);});
		assertThrows(InvalidCustomerCardException.class,() -> {e.modifyPointsOnCard("qwerty7890", 4);});
		assertThrows(InvalidCustomerCardException.class,() -> {e.modifyPointsOnCard("", 4);});
		
		e.attachCardToCustomer(card, 1);
		
		assertFalse(e.modifyPointsOnCard(card2, 50));
		
		assertFalse(e.modifyPointsOnCard(card, -1));
		assertTrue(e.getAllCustomers().stream()
				.filter((c) ->c.getCustomerCard().equals(card))
				.findFirst().get().getPoints() == 0);
		
		assertTrue(e.modifyPointsOnCard(card, 50));
		assertTrue(e.getAllCustomers().stream()
				.filter((c) ->c.getCustomerCard().equals(card))
				.findFirst().get().getPoints() == 50);
		
		assertFalse(e.modifyPointsOnCard(card, -100));
		assertTrue(e.getAllCustomers().stream()
				.filter((c) ->c.getCustomerCard().equals(card))
				.findFirst().get().getPoints() == 50);
		
		
		e.login("validUser", "pass");
		e.attachCardToCustomer(card, 1);
		e.logout();
		
		e.login("validAdministrator", "pass");
		e.attachCardToCustomer(card,1);
		e.logout();
		
		e.login("validManager", "pass");
		e.attachCardToCustomer(card,3);
		e.logout();
	}
	
}
