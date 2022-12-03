import java.util.HashMap;
import java.util.Map;

public class FirstNationalBank implements Bank {
    private Map<String, Long> accounts = new HashMap();

    public FirstNationalBank() {
    }

    public String openAccount() {
        String accountNumber = String.format("34778000-%08d", this.accounts.size() + 1);
        this.accounts.put(accountNumber, new Long(0L));
        return accountNumber;
    }

    public boolean closeAccount(String accountNumber) throws AccountNotExistsException {
        if (!this.accounts.containsKey(accountNumber)) {
            throw new AccountNotExistsException(accountNumber);
        } else if ((Long)this.accounts.get(accountNumber) == 0L) {
            this.accounts.remove(accountNumber);
            return true;
        } else {
            return false;
        }
    }

    public long getBalance(String accountNumber) throws AccountNotExistsException {
        return !this.accounts.containsKey(accountNumber) ? -1L : (Long)this.accounts.get(accountNumber);
    }

    public void deposit(String accountNumber, long amount) throws AccountNotExistsException {
        if (amount <= 0L) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        } else if (!this.accounts.containsKey(accountNumber)) {
            throw new AccountNotExistsException(accountNumber);
        } else {
            this.accounts.put(accountNumber, new Long((Long)this.accounts.get(accountNumber) + amount / 100L * 100L));
        }
    }

    public void withdraw(String accountNumber, long amount) throws AccountNotExistsException, NotEnoughFundsException {
        if (amount <= 0L) {
            throw new IllegalArgumentException("Withdraw amount must be positive");
        } else if (!this.accounts.containsKey(accountNumber)) {
            throw new AccountNotExistsException(accountNumber);
        } else {
            long balance = (Long)this.accounts.get(accountNumber);
            if (balance < amount) {
                throw new NotEnoughFundsException(accountNumber);
            } else {
                this.accounts.put(accountNumber, new Long(balance - amount / 100L * 100L));
            }
        }
    }

    public void transfer(String sourceAccount, String targetAccount, long amount) throws AccountNotExistsException, NotEnoughFundsException {
        if (amount <= 0L) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        } else if (!this.accounts.containsKey(targetAccount)) {
            throw new AccountNotExistsException(targetAccount);
        } else if (!this.accounts.containsKey(sourceAccount)) {
            throw new AccountNotExistsException(sourceAccount);
        } else {
            long sourceBalance = (Long)this.accounts.get(sourceAccount);
            if (sourceBalance < amount) {
                throw new NotEnoughFundsException(sourceAccount);
            } else {
                this.accounts.put(sourceAccount, new Long(sourceBalance - amount));
                long targetBalance = (Long)this.accounts.get(targetAccount);
                this.accounts.put(targetAccount, new Long(targetBalance + amount));
            }
        }
    }
}
