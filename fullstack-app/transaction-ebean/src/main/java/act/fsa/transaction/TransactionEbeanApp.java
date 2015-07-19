package act.fsa.transaction;

import act.app.ActionContext;
import act.boot.app.RunApp;
import act.job.OnAppStart;
import org.avaje.agentloader.AgentLoader;
import org.osgl._;
import org.osgl.logging.L;
import org.osgl.logging.Logger;
import org.osgl.mvc.annotation.DeleteAction;
import org.osgl.mvc.annotation.GetAction;
import org.osgl.mvc.annotation.PostAction;
import org.osgl.mvc.annotation.PutAction;
import org.osgl.mvc.result.Result;
import org.osgl.util.C;
import org.osgl.util.IntRange;

import javax.inject.Inject;
import java.util.List;
import java.util.Random;

import static act.controller.Controller.Util.ok;
import static act.controller.Controller.Util.redirect;
import static act.controller.Controller.Util.render;

/**
 * A Simple Todo application controller
 */
public class TransactionEbeanApp {

    public static final String ACC_A = "A";
    public static final String ACC_B = "B";

    @Inject
    private AccountDao dao;

    @GetAction("/")
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

    public static void main(String[] args) throws Exception {
        RunApp.start(TransactionEbeanApp.class);
    }


}
