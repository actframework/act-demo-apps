package demo.transaction.ebean;

import act.Act;
import act.Version;
import act.app.ActionContext;
import act.job.OnAppStart;
import org.osgl.$;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.result.Result;
import org.osgl.util.IntRange;

import javax.inject.Inject;

import static act.controller.Controller.Util.redirect;
import static act.controller.Controller.Util.render;

/**
 * A Simple Todo application controller
 */
public class TransactionEbeanApp {

    public static final String ACC_A = "A";
    public static final String ACC_B = "B";

    @Inject
    private Account.Dao dao;

    @GetAction
    public Result home() {
        Account accA = dao.findById(ACC_A);
        Account accB = dao.findById(ACC_B);
        return render(accA, accB);
    }

    @PostAction("/transfer")
    public Result transfer(int amount, boolean btnA2B, boolean btnB2A, ActionContext context) {
        boolean success;
        if (btnA2B) {
            success = dao.transfer(amount, ACC_A, ACC_B);
        } else {
            success = dao.transfer(amount, ACC_B, ACC_A);
        }
        if (!success) {
            context.flash().error("Transaction failed. Possible reason: no enough balance in the credit account");
        } else {
            context.flash().success("Transaction committed successfully");
        }
        return redirect("/");
    }

    @OnAppStart
    public void ensureTestingData() {
        Account a = dao.findById(TransactionEbeanApp.ACC_A);
        if (null == a) {
            a = new Account(TransactionEbeanApp.ACC_A);
            a.setAmount($.random(IntRange.of(100, 2000)));
            dao.save(a);
        }
        Account b = dao.findById(TransactionEbeanApp.ACC_B);
        if (null == b) {
            b = new Account(TransactionEbeanApp.ACC_B);
            b.setAmount($.random(IntRange.of(200, 300)));
            dao.save(b);
        }
    }


    public static void main(String[] args) throws Exception {
        Act.start("Transaction Demo", Version.appVersion(), TransactionEbeanApp.class);
    }


}
