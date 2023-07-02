package billPaymentSystem_05;

import jakarta.persistence.*;

@Entity
@Table(name = "credit_cards")
public class CreditCard extends BillingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "expiration_month", nullable = false)
    private String expirationMonth;

    @Column(name = "expiration_year", nullable = false)
    private String expirationYear;

    public CreditCard() {
    }

    @ManyToOne(optional = false)
    private BillingDetail billingDetails;

    public BillingDetail getBillingDetails() {
        return billingDetails;
    }

    public void setBillingDetails(BillingDetail billingDetails) {
        this.billingDetails = billingDetails;
    }
}
