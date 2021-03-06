package application.actions;

import action.Action;
import application.BankActionContext;
import bank.Account;
import bank.BankAgency;
import bank.exception.AccountException;

import java.io.PrintStream;
import java.util.Scanner;

public class Deposit implements Action<BankActionContext> {
    private String message;
    private String code;

    public Deposit(String code, String message) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String actionMessage() {
        return this.message;
    }

    @Override
    public String actionCode() {
        return this.code;
    }

    @Override
    public void execute(BankActionContext context) throws Exception {
        PrintStream printStream = context.getPrintStream();
        Scanner scanner = context.getScanner();
        BankAgency ag = context.getBankAgency();
        printStream.print("Account Number -> ");
        String number = scanner.next();
        printStream.print("Deposit amount -> ");
        double amount = scanner.nextDouble();
        depositOnAccount(ag, number, amount, printStream);
    }

    private static void depositOnAccount(BankAgency ag, String accountNumber, double amount, PrintStream printStream) {
        Account c;

        c = ag.getAccount(accountNumber);
        if (c==null) {
            printStream.println("Account not existing ...");
        } else {
            printStream.println("Balance before deposit: "+c.balance());
            try {
                c.deposit(amount);
                printStream.println("Balance after deposit: "+c.balance());
            } catch (AccountException e) {
                printStream.println("Deposit error, Balance unchanged: " + c.balance());
                printStream.println(e.getMessage());
            }
        }
    }
}