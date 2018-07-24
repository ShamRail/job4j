package ru.job4j.bank;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class BankTest {
    @Test
    public void whenGetUserAccounts() {
        Bank bank = new Bank();
        User user = new User("Kiril", "8013");
        Account account1 = new Account(123, "req1");
        Account account2 = new Account(124, "req2");
        bank.addUser(user);
        bank.addAccountToUser(user.getPassport(), account1);
        bank.addAccountToUser(user.getPassport(), account2);
        List<Account> result = bank.getUserAccounts(user.getPassport());
        Arrays.asList(result.get(0).getValue() + result.get(1).getValue(), Is.is(247));
    }

    @Test
    public void whenAddAccountsToUser() {
        Bank bank = new Bank();
        User user = new User("Kiril", "8013");
        Account account1 = new Account(123, "req1");
        Account account2 = new Account(124, "req2");
        bank.addUser(user);
        bank.addAccountToUser(user.getPassport(), account1);
        bank.addAccountToUser(user.getPassport(), account2);
        List<Account> result = bank.getUserAccounts(user.getPassport());
        Assert.assertThat(result.get(1), Is.is(account2));
    }
    @Test
    public void whenDeleteAccountFromUserAccounts() {
        Bank bank = new Bank();
        User user = new User("Kiril", "8013");
        Account account1 = new Account(123, "req1");
        Account account2 = new Account(124, "req2");
        bank.addUser(user);
        bank.addAccountToUser(user.getPassport(), account1);
        bank.addAccountToUser(user.getPassport(), account2);
        bank.deleteAccountFromUser(user.getPassport(), account1);
        List<Account> result = bank.getUserAccounts(user.getPassport());
        List<Account> expect = Arrays.asList(account2);
        Assert.assertThat(result, Is.is(expect));
    }

    @Test
    public void whenTransferMoneyTrue() {
        Bank bank = new Bank();
        User user1 = new User("Kiril", "8013");
        User user2 = new User("Masha", "8015");
        Account account1 = new Account(123, "req1");
        Account account2 = new Account(124, "req2");
        bank.addUser(user1);
        bank.addUser(user2);
        bank.addAccountToUser(user1.getPassport(), account1);
        bank.addAccountToUser(user2.getPassport(), account2);
        bank.transferMoney(user1.getPassport(), account1.getRequisites(), user2.getPassport(), account2.getRequisites(), 123);
        List<Account> result = bank.getUserAccounts(user2.getPassport());
        Assert.assertThat(result.get(0).getValue(), Is.is(247.0));
    }
    @Test
    public void whenTransferMoneyFalse() {
        Bank bank = new Bank();
        User user1 = new User("Kiril", "8013");
        User user2 = new User("Masha", "8015");
        Account account1 = new Account(123, "req1");
        Account account2 = new Account(124, "req2");
        bank.addUser(user1);
        bank.addUser(user2);
        bank.addAccountToUser(user1.getPassport(), account1);
        bank.addAccountToUser(user2.getPassport(), account2);
        boolean result = bank.transferMoney(user1.getPassport(), account1.getRequisites(),
                user2.getPassport(), account2.getRequisites(), 200);
        Assert.assertThat(result, Is.is(false));
    }
}
