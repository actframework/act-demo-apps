package demo.transaction.ebean;

/*-
 * #%L
 * actframework app demo - Transaction (ebean)
 * %%
 * Copyright (C) 2018 ActFramework
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import act.db.ebean2.EbeanDao;
import io.ebean.annotation.Transactional;
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
