import java.util.HashMap;
import java.util.Map;

/**
 * A számla mûveleteket megvalósító másik bank osztály.
 */
public class GreatSavingsBank implements Bank {
	
	/**
	 * A bankban vezetett számlát megvalósító osztály.
	 */
	private class Account {
		
		/**
		 * A számla száma.
		 */
		private String accountNumber;
		
		/**
		 * A számla egyenlege. 
		 */
		private long balance;
		
		/**
		 * Adott számlaszámú számla létrehozása. A számla nyitóegyenlege nulla. 
		 * @param accountNumber A számlaszám.
		 */
		public Account(String accountNumber) {
			this.accountNumber = accountNumber;
			this.balance = 0;
		}
		
		/**
		 * A számlaszám lekérdezése.
		 * @return A számla száma.
		 */
		public String getAccountNumber() {
			return accountNumber;
		}
		
		/**
		 * Az egyenleg lekérdezése.
		 * @return A számla egyenlege.
		 */
		public long getBalance() {
			return balance;
		}

		/**
		 * A megadott összeg befizetése a számlára.
		 * @param amount A befizetendõ összeg, tetszõleges szám lehet.
		 */
		public void deposit(long amount) {
			balance += amount;
		}

		/**
		 * A megadott összeg kifizetése a számláról.
		 * @param amount A kifizetendõ összeg, tetszõleges szám lehet.
		 * @throws NotEnoughFundsException Ha a számla egyenlege alacsonyabb mint a megadott összeg.
		 */
		public void withdraw(long amount) throws NotEnoughFundsException {
			if(amount > balance) {
				throw new NotEnoughFundsException(accountNumber);
			}
			balance -= amount;
		}
	}

	/**
	 * A bankban vezetett számlák kollekciója.
	 */
	private Map<String, Account> accounts = new HashMap<String, Account>();
	
	/**
	 * Elõkeresi a megadott számú számlát, ha létezik.
	 * @param accountNumber A keresett számla száma.
	 * @return A keresett számla objektum.
	 * @throws AccountNotExistsException Ha nem létezik az adott számú számla.
	 */
	private Account getAccount(String accountNumber) throws AccountNotExistsException {
		if(accounts.containsKey(accountNumber)) {
			return accounts.get(accountNumber);
		} else {
			throw new AccountNotExistsException(accountNumber);
		}
	}
	
	/**
	 * A megadott összeget 100-zal oszthatóvá kerekíti.
	 * @param amount Tetszõleges pozitív összeg.
	 * @return A megadott összeg a kerekítés szabályainak alkalmazása után.
	 */
	private long roundAmount(long amount) {
		if(amount <= 0) {
			throw new IllegalArgumentException("Amount must be positive");
		}
		long remainder = amount % 100;
		if(remainder < 50) {
			return amount - remainder;
		} else {
			return amount + 100 - remainder;
		}
	}

	@Override
	public String openAccount() {
		Account newAccount = new Account(String.format("47328000-%08d", accounts.size() + 1));
		accounts.put(newAccount.getAccountNumber(), newAccount);
		return newAccount.getAccountNumber();
	}

	@Override
	public boolean closeAccount(String accountNumber) throws AccountNotExistsException {
		if(getAccount(accountNumber).getBalance() == 0) {
			accounts.remove(accountNumber);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public long getBalance(String accountNumber) throws AccountNotExistsException {
		return getAccount(accountNumber).getBalance();
	}

	@Override
	public void deposit(String accountNumber, long amount) throws AccountNotExistsException {
		getAccount(accountNumber).deposit(roundAmount(amount));
	}

	@Override
	public void withdraw(String accountNumber, long amount) throws AccountNotExistsException, NotEnoughFundsException {
		getAccount(accountNumber).withdraw(roundAmount(amount));
	}

	@Override
	public void transfer(String sourceAccount, String targetAccount, long amount) throws AccountNotExistsException, NotEnoughFundsException {
		if(amount <= 0) {
			throw new IllegalArgumentException("Amount must be positive");
		}
		Account source = getAccount(sourceAccount);
		Account target = getAccount(targetAccount);
		source.withdraw(amount);
		target.deposit(amount);
	}
}
