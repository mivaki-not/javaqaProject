package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(0, 5_000, 15);

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test
    public void shouldPayIntoNegativeWithinLimit() {
        CreditAccount account = new CreditAccount(1_000, 5_000, 10);

        account.pay(1_500);

        Assertions.assertEquals(-500, account.getBalance());
    }

    @Test
    public void shouldPayFromPositiveBalance() {
        CreditAccount account = new CreditAccount(1_000, 5_000, 10);

        account.pay(500);

        Assertions.assertEquals(500, account.getBalance());
    }

    @Test
    public void shouldPayExactCreditLimit() {
        CreditAccount account = new CreditAccount(0, 5_000, 10);

        account.pay(5_000);

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void shouldFailForZeroPayment() {
        CreditAccount account = new CreditAccount(1_000, 5_000, 10);

        account.pay(0);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void shouldRejectZeroAmount() {
        CreditAccount account = new CreditAccount(1_000, 5_000, 10);

        account.add(0);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test
    public void yearChangeShouldReturnZeroForZeroBalance() {
        CreditAccount account = new CreditAccount(0, 5_000, 10);

        account.yearChange();

        Assertions.assertEquals(0, account.getBalance());
    }

    @Test
    public void yearChangeShouldCalculateForNegativeBalance() {
        CreditAccount account = new CreditAccount(-1_000, 5_000, 15);

        Assertions.assertEquals(-150, account.yearChange());
    }

    @Test
    public void getCreditLimitShouldReturnCorrectValue() {
        CreditAccount account = new CreditAccount(1_000, 5_000, 10);

        Assertions.assertEquals(5_000, account.getCreditLimit());
    }

    @Test
    public void shouldThrowWhenNegativeRate() {
        Assertions.assertThrows(IllegalArgumentException.class,() ->
                new CreditAccount(1_000, 5_000, -5));
    }
}
