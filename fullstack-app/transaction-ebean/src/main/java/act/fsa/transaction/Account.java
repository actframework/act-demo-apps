package act.fsa.transaction;

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

}
