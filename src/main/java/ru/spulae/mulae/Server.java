package ru.spulae.mulae;

import java.util.LinkedList;
import java.util.List;

public class Server {
    public static void test() {
        Person p1 = new Person(1, "a", 0);
        Person p2 = new Person(2, "b", 0);
        Payment payment = new Payment(100, "taxi");
        List participants = new LinkedList();
        participants.add(p1);
        participants.add(p2);
        Fundraising fund = new Fundraising("kaliiik", participants);
        fund.addPayment(p1, payment);
        fund.setAgreement(p1);
        fund.setAgreement(p2);
        System.out.println(p1.getTotalAmount());
        System.out.println(p2.getTotalAmount());
    }
}
