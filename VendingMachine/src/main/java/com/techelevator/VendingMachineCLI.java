package com.techelevator;

import java.math.BigDecimal;
import java.util.Scanner;

public class VendingMachineCLI {
	purchaseLog myLog = new purchaseLog();
	salesReport salesReport= new salesReport();

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };
	private static final String FEED_MONEY_OPTION = "Feed Money";
	private static final String SELECT_PRODUCT = "Select Product";
	private static final String FINISH_TRANSACTION = "Finish Transaction";

	private static final String SALES_REPORT= "Sales report";

	private static final String[] PURCHASE_MENU_OPTIONS = { FEED_MONEY_OPTION, SELECT_PRODUCT, FINISH_TRANSACTION, SALES_REPORT };
	private Menu menu;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		AllSnacks objectOne = new AllSnacks();
		objectOne.populateDisplayItemsMap();
		objectOne.populateItemsWithPriceMap();
		Purchase purchase= new Purchase();
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				System.out.println(objectOne.displayItems());
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				System.out.println("Current Money Provided: $" + purchase.getMoneyInput());
				while(true){
					String secondChoice= (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					if(secondChoice.equals(FEED_MONEY_OPTION)) {
						System.out.println("This vending machine only accepts Bills($1, $5, $10, etc.)");
						System.out.println("How much money would you like to insert?: ");
						Scanner scan = new Scanner(System.in);
						String userMoneyInput = scan.nextLine();
						if (userMoneyInput.contains("$")) {
							System.out.println("Please only enter numbers and no symbols!");

						} else {
							purchase.addMoney(new BigDecimal(userMoneyInput));
						System.out.println("Current Money Provided: $" + purchase.getMoneyInput());

						myLog.addToLogFeedMoney(new BigDecimal(userMoneyInput), purchase);
					}

					} else if (secondChoice.equals(SELECT_PRODUCT)){
						System.out.println(objectOne.itemsForPurchase());
						Scanner scan= new Scanner(System.in);
						String userCodeInput= scan.nextLine();
						objectOne.itemDispense(userCodeInput, purchase);
						salesReport.updateMapQuantity(userCodeInput, objectOne);
						salesReport.addToTotalSales(userCodeInput, objectOne);
						myLog.addToLogItemSelected(purchase, objectOne, userCodeInput);
					}
					else if (secondChoice.equals(FINISH_TRANSACTION)) {
						myLog.addToLogChangeGiven(purchase);
						purchase.returnChange();
						purchase.setMoneyInput(new BigDecimal("0"));
						purchase.setQuarters(new BigDecimal("0"));
						purchase.setDimes(new BigDecimal("0"));
						purchase.setNickels(new BigDecimal("0"));
						break;


					}
					else if(secondChoice.equals(SALES_REPORT)){
						salesReport.getSalesReportFile();
					}
				}
			}else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				break;
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

}
