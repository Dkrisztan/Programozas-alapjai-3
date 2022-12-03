import java.util.HashMap;
import java.util.Map;

/**
 * A sz�mla m�veleteket megval�s�t� m�sik bank oszt�ly.
 */
public class GreatSavingsBank implements Bank {
	
	/**
	 * A bankban vezetett sz�ml�t megval�s�t� oszt�ly.
	 */
	private class Account {
		
		/**
		 * A sz�mla sz�ma.
		 */
		private String accountNumber;
		
		/**
		 * A sz�mla egyenlege. 
		 */
		private long balance;
		
		/**
		 * Adott sz�mlasz�m� sz�mla l�trehoz�sa. A sz�mla nyit�egyenlege nulla. 
		 * @param accountNumber A sz�mlasz�m.
		 */
		public Account(String accountNumber) {
			this.accountNumber = accountNumber;
			this.balance = 0;
		}
		
		/**
		 * A sz�mlasz�m lek�rdez�se.
		 * @return A sz�mla sz�ma.
		 */
		public String getAccountNumber() {
			return accountNumber;
		}
		
		/**
		 * Az egyenleg lek�rdez�se.
		 * @return A sz�mla egyenlege.
		 */
		public long getBalance() {
			return balance;
		}

		/**
		 * A megadott �sszeg befizet�se a sz�ml�ra.
		 * @param amount A befizetend� �sszeg, tetsz�leges sz�m lehet.
		 */
		public void deposit(long amount) {
			balance += amount;
		}

		/**
		 * A megadott �sszeg kifizet�se a sz�ml�r�l.
		 * @param amount A kifizetend� �sszeg, tetsz�leges sz�m lehet.
		 * @throws NotEnoughFundsException Ha a sz�mla egyenlege alacsonyabb mint a megadott �sszeg.
		 */
		public void withdraw(long amount) throws NotEnoughFundsException {
			if(amount > balance) {
				throw new NotEnoughFundsException(accountNumber);
			}
			balance -= amount;
		}
	}

	/**
	 * A bankban vezetett sz�ml�k kollekci�ja.
	 */
	private Map<String, Account> accounts = new HashMap<String, Account>();
	
	/**
	 * El�keresi a megadott sz�m� sz�ml�t, ha l�tezik.
	 * @param accountNumber A keresett sz�mla sz�ma.
	 * @return A keresett sz�mla objektum.
	 * @throws AccountNotExistsException Ha nem l�tezik az adott sz�m� sz�mla.
	 */
	private Account getAccount(String accountNumber) throws AccountNotExistsException {
		if(accounts.containsKey(accountNumber)) {
			return accounts.get(accountNumber);
		} else {
			throw new AccountNotExistsException(accountNumber);
		}
	}
	
	/**
	 * A megadott �sszeget 100-zal oszthat�v� kerek�ti.
	 * @param amount Tetsz�leges pozit�v �sszeg.
	 * @return A megadott �sszeg a kerek�t�s szab�lyainak alkalmaz�sa ut�n.
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
