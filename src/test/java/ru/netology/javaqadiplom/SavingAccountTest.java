package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SavingAccountTest {

    @Test
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                5_000,
                5
        );

        account.add(3_000);

        assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test
    public void testConstructor_ValidParameters() {
        SavingAccount account = new SavingAccount(2000, 1000, 5000, 5);
        assertNotNull(account);
        assertEquals(2000, account.getBalance());
        assertEquals(1000, account.getMinBalance());
        assertEquals(5000, account.getMaxBalance());
        assertEquals(5, account.getRate());
    }

    @Test
    public void testConstructor_InvalidRate() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(2000, 1000, 5000, -1);
        });
        assertEquals("Накопительная ставка не может быть отрицательной, а у вас: -1", exception.getMessage());
    }

    @Test
    public void testPay_ValidPayment() {
        SavingAccount account = new SavingAccount(2000, 1000, 5000, 5);
        assertTrue(account.pay(500));
        assertEquals(1500, account.getBalance());
    }

    @Test
    public void testPay_InsufficientFunds() {
        SavingAccount account = new SavingAccount(2000, 1000, 5000, 5);
        assertFalse(account.pay(1500)); // Списываем больше, чем остаток
        assertEquals(2000, account.getBalance()); // Баланс не изменился
    }

    @Test
    public void testPay_NegativeAmount() {
        SavingAccount account = new SavingAccount(2000, 1000, 5000, 5);
        assertFalse(account.pay(-100)); // Нельзя списывать отрицательные суммы
        assertEquals(2000, account.getBalance()); // Баланс не изменился
    }

    @Test
    public void testAdd_ValidDeposit() {
        SavingAccount account = new SavingAccount(2000, 1000, 5000, 5);
        assertTrue(account.add(2000));
        assertEquals(4000, account.getBalance());
    }

    @Test
    public void testAdd_ExceedMaxBalance() {
        SavingAccount account = new SavingAccount(2000, 1000, 5000, 5);
        assertFalse(account.add(4000)); // Превышение максимального баланса
        assertEquals(2000, account.getBalance()); // Баланс не изменился
    }

    @Test
    public void testAdd_NegativeAmount() {
        SavingAccount account = new SavingAccount(2000, 1000, 5000, 5);
        assertFalse(account.add(-500)); // Нельзя добавлять отрицательные суммы
        assertEquals(2000, account.getBalance()); // Баланс не изменился
    }


    @Test
    public void testYearChange_ZeroBalance() {
        SavingAccount account = new SavingAccount(2000, 1000, 2000, 15);
        assertEquals(300, account.yearChange()); // Проценты не начисляются
    }
}

