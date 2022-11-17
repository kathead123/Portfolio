package com.techelevator.tenmo;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.UserCredentials;
import com.techelevator.tenmo.services.AccountService;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.ConsoleService;
import com.techelevator.tenmo.services.TransferService;

import java.math.BigDecimal;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();

    private final AccountService accountService= new AccountService();

    private final TransferService transferService= new TransferService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);

    private AuthenticatedUser currentUser;

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        } else{
            accountService.setToken(currentUser.getToken());
            transferService.setToken(currentUser.getToken());
            transferService.setUsername(currentUser.getUser().getUsername());
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

	private void viewCurrentBalance() {
		// TODO Auto-generated method stub
        System.out.println("Your current account balance is: $" + accountService.getBalance());
		
	}

	private void viewTransferHistory() {
		// TODO Auto-generated method stub
		transferService.getTransfers();
        int userInputOfTransferId = consoleService.promptForInt("Please enter transfer ID to view details (press 0 to cancel): ");
        transferService.getTransferDetails(userInputOfTransferId);
	}

	private void viewPendingRequests() {
		// TODO Auto-generated method stub
        transferService.getMyPendingTransfers();
        int userIdInput = consoleService.promptForInt("Please enter transfer ID to approve/ reject (0 to cancel): ");
        consoleService.printApproveRejectMenu();
        int userApproveRejectSelection= consoleService.promptForInt("Please choose an option: ");
        if(userApproveRejectSelection==1){
            transferService.transferApproval(userIdInput);
        } else if (userApproveRejectSelection == 2){
            transferService.rejectTransfer(userIdInput);
        }


		
	}

	private void sendBucks() {
		// TODO Auto-generated method stub
        accountService.getUsers();
        int userInputOfIdToSendTo= consoleService.promptForInt("Enter ID of user you are sending TE bucks to (0 to cancel): ");
        BigDecimal amount= consoleService.promptForBigDecimal("Enter amount you would like to send: ");
        accountService.sendTransfer(amount, userInputOfIdToSendTo);
		
	}

	private void requestBucks() {
		// TODO Auto-generated method stub
        accountService.getUsers();
        int userIdRequestingFrom= consoleService.promptForInt("Enter ID of user you are requesting from (0 to cancel): ");
        BigDecimal amount= consoleService.promptForBigDecimal("Enter amount: ");
        transferService.requestMoney(amount, userIdRequestingFrom);
	}

}


