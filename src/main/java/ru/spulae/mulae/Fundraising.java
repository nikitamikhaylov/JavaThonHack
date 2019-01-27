package ru.spulae.mulae;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Fundraising {

    private String name;
    private List<Person> participants;
    private Map<Person, List<Payment>> payments;
    private Map<Person, Boolean> isBlocked;
    private Map<Person, Boolean> isAgreedToCalculate;

    public Fundraising(String name, List<Person> participants) {
        this.name = name;
        this.participants = participants;
        this.payments = new HashMap<Person, List<Payment>>();
        this.isBlocked = new HashMap<Person, Boolean>();
        this.isAgreedToCalculate = new HashMap<Person, Boolean>();

        for (Person current: participants) {
            this.payments.put(current, new ArrayList<Payment>());
            this.isBlocked.put(current, false);
            this.isAgreedToCalculate.put(current, false);
        }
    }

    public String getName() {
        return name;
    }

    public List<Person> getParticipants() {
        return participants;
    }

    public void setBlock(Person person) {
        this.isBlocked.put(person, true);
    }

    public void getReceiptUpdates(){
        Boolean flag = true;
        for (Person current: participants) {
            if (!this.isBlocked.get(current)) {
                flag = false;
            }
        }
        if (flag) {
            //всем разослать пуши
        }
    }

    public void setAgreement(Person person) {
        this.isAgreedToCalculate.put(person, true);

        Boolean flag = true;
        for (Person current: participants) {
            if (!this.isAgreedToCalculate.get(current)) {
                flag = false;
            }
        }
        if (flag) { //завершение
            recalculation();
        }
    }

    //для приложения
    public boolean addPayment(Person person, Payment payment) {
        if (this.isBlocked.get(person)) {
            return false;
        }
        this.payments.get(person).add(payment);
        return true;
    }

    private double round(double value) {
        long tmp = Math.round(value * 100);
        return (double) tmp / 100;
    }

    private void recalculation() {
        int totalAmount = 0;
        for (Person person: participants) {
            List<Payment> persons_payments = payments.get(person);
            for (Payment payment: persons_payments) {
                totalAmount += payment.getAmount();
            }
        }

        double flactional_amount = round((double) totalAmount / participants.size() );

        for (Person person: participants) {
            int persons_amount = 0;
            List<Payment> persons_payments = payments.get(person);
            for (Payment payment: persons_payments) {
                persons_amount += payment.getAmount();
            }

            double diff = persons_amount - flactional_amount;
            double prev_amount = person.getTotalAmount();
            double new_amount = prev_amount + diff;
            person.setTotalAmount(new_amount) ;
        }
    }
}
