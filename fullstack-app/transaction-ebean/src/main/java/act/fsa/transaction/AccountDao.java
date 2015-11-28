package act.fsa.transaction;

import act.db.ebean.EbeanDao;
import act.job.OnAppStart;
import com.avaje.ebean.EbeanServer;
import com.avaje.ebean.annotation.Transactional;
import org.osgl.$;
import org.osgl.util.IntRange;

import static act.fsa.transaction.TransactionEbeanApp.ACC_A;
import static act.fsa.transaction.TransactionEbeanApp.ACC_B;

public class AccountDao extends EbeanDao<String, Account, AccountDao> {
    protected AccountDao() {
        super(Account.class);
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

    @OnAppStart
    public void ensureTestingData() {
        Account a = findById(ACC_A);
        if (null == a) {
            a = new Account(ACC_A);
            a.setAmount($.random(IntRange.of(100, 200)));
            save(a);
        }
        Account b = findById(ACC_B);
        if (null == b) {
            b = new Account(ACC_B);
            b.setAmount($.random(IntRange.of(200, 300)));
            save(b);
        }
    }

}
