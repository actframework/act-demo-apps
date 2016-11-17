package demo.transaction.ebean;

import act.db.ebean.EbeanDao;
import com.avaje.ebean.annotation.Transactional;
import org.osgl.util.E;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * The account with a certain amount of money
 */
@Entity(name = "acc")
public class Account {
    @Id
    String id;

    int amount;

    public Account(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        E.illegalArgumentIf(amount < 0);
        this.amount = amount;
    }

    public void deposit(int amount) {
        E.illegalArgumentIf(amount < 0);
        this.amount += amount;
    }

    public void credit(int amount) throws AccountException {
        E.illegalArgumentIf(amount < 0);
        if (this.amount < amount) {
            throw new AccountException("Cannot credit on account[%s]: balance not enough");
        }
        this.amount -= amount;
    }

    public static class Dao extends EbeanDao<String, Account> {

        protected Dao() {
            super(String.class, Account.class);
        }

        public boolean transfer(int amount, String fromId, String toId) {
            try {
                doTransfer(amount, fromId, toId);
                return true;
            } catch (AccountException e) {
                return false;
            }
        }

        @Transactional
        private void doTransfer(int amount, String fromId, String toId) throws AccountException {
            Account to = findById(toId);
            to.deposit(amount);
            save(to);
            Account from = findById(fromId);
            from.credit(amount);
            save(from);
        }

    }

}
