package ru.job4j.bank;

import java.util.*;

/**
 * Bank.
 * Класс банк, имитирующий банковские операции.
 * @version 1.0.
 * @author Rail Shamsemukhametov.
 * @since 24.07.2018.
 * */

public class Bank {
    /**Список пользователей по принципу пользователь - список счетов*/
    private Map<User, List<Account>> bank = new HashMap<>();
    /**
     * addUser.
     * Добавляет пользователя, если такого нет еще.
     * @param user - пользователь.
     * */
    public void addUser(User user) {
        this.bank.putIfAbsent(user, new ArrayList<>());
    }
    /**
     * deleteUser.
     * Удаляет пользователя.
     * @param user - пользователь.
     * */
    public void deleteUser(User user) {
        this.bank.remove(user);
    }
    /**
     * addAccountToUser.
     * Добавляет счет по паспорту.
     * @param passport - паспорт.
     * @param account - счет.
     * */
    public void addAccountToUser(String passport, Account account) {
        if (passport != null && account != null) {
            for (User user : this.bank.keySet()) {
                if (user.getPassport().equals(passport)) {
                    this.bank.get(user).add(account);
                    break;
                }
            }
        }
    }
    /**
     * deleteAccountFromUser.
     * Удаляет счет пользователя по паспорту.
     * @param passport - паспорт.
     * @param account - счет.
     * */
    public void deleteAccountFromUser(String passport, Account account) {
        if (passport != null && account != null) {
            for (User user : this.bank.keySet()) {
                if (user.getPassport().equals(passport)) {
                    this.bank.get(user).remove(account);
                    break;
                }
            }
        }
    }
    /**
     * getUserAccounts.
     * Возвращет счета пользователя по паспорту, если их нет null.
     * @param passport - паспорт пользователя.
     * @return счета.
     * */
    public List<Account> getUserAccounts(String passport) {
        List<Account> accounts = null;
        if (passport != null) {
            for (User user : this.bank.keySet()) {
                if (user.getPassport().equals(passport)) {
                    accounts = this.bank.get(user);
                    break;
                }
            }
        }
        return accounts;
    }

    /**
     * transferMoney.
     * Переводит деньги с одного счета на другой, если это получает сделать возвращает true, иначе false.
     * @param destPassport - паспорт пользоватля, которому переводим.
     * @param srcPassport - паспорт пользователя, от которого переводим.
     * @param dstRequisite - реквизит счета, куда переводим.
     * @param srcRequisite - реквизит счета, от которого переводим.
     * @return результат.
     * */

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                  String destPassport, String dstRequisite, double amount) {
        boolean result = false;
        if (srcPassport != null && srcRequisite != null && destPassport != null && dstRequisite != null
                && amount > 0) {

            Account srcAccount = this.findAccountByRequisiteAndPassport(srcPassport, srcRequisite);
            Account dstAccount = this.findAccountByRequisiteAndPassport(destPassport, dstRequisite);

            if (srcAccount != null && dstAccount != null && srcAccount.getValue() - amount >= 0) {
                    srcAccount.setValue(srcAccount.getValue() - amount);
                    dstAccount.setValue(dstAccount.getValue() + amount);
                    result = true;
                }
            }
        return result;
    }
    /**
     * findAccountByRequisite.
     * Находит счет по паспорту и реквизитам, если его нет то возвращает null.
     * @param passport - паспорт.
     * @param requisite - реквизиты.
     * @return счет.
     * */
    public Account findAccountByRequisiteAndPassport(String passport, String requisite) {
        Account result = null;
        List<Account> accounts = this.getUserAccounts(passport);
        if (passport != null && requisite != null && accounts != null) {
            for (Account account : accounts) {
                if (account.getRequisites().equals(requisite)) {
                    result = account;
                    break;
                }
            }
        }
        return result;
    }
}
