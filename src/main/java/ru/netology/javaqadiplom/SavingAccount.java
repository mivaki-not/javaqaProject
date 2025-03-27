package ru.netology.javaqadiplom;

public class SavingAccount extends Account {
    protected int minBalance;
    protected int maxBalance;

    public SavingAccount(int initialBalance, int minBalance, int maxBalance, int rate) {
        if (rate < 0) {
            throw new IllegalArgumentException("Накопительная ставка не может быть отрицательной, а у вас: " + rate);
        }

        this.balance = initialBalance;
        this.minBalance = minBalance;
        this.maxBalance = maxBalance;
        this.rate = rate;
    }

    @Override
    public boolean pay(int amount) {
        if (amount <= 0) {
            return false; // Нельзя списывать отрицательные суммы
        }
        if (balance - amount < minBalance) {
            return false; // Не хватает средств для проведения операции
        }
        balance -= amount; // Уменьшаем баланс
        return true; // Операция прошла успешно
    }

    @Override
    public boolean add(int amount) {
        if (amount <= 0) {
            return false; // Нельзя добавлять отрицательные суммы
        }
        if (balance + amount > maxBalance) {
            return false; // Превышение максимального баланса
        }
        balance += amount; // Увеличиваем баланс
        return true; // Операция прошла успешно
    }

    @Override
    public int yearChange() {
        return (balance * rate) / 100; // Рассчитываем проценты на положительный баланс
    }

    public int getMinBalance() {
        return minBalance;
    }

    public int getMaxBalance() {
        return maxBalance;
    }
}
