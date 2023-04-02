package co.ptit.domain.entity;

import co.ptit.utils.Constant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * project: library_springboot
 * date:    4/2/2023
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Bill extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = Constant.Sequence.SEQ_BILL, sequenceName = Constant.Sequence.SEQ_BILL, allocationSize = 1)
    @Column(name = "BILL_ID")
    private Long billId;

    @Basic
    @Column(name = "BOOK_ID")
    private Long bookId;

    @Basic
    @Column(name = "USER_ID")
    private Long userId;

    @Basic
    @Column(name = "QUANTITY")
    private Integer quantity;

    @Basic
    @Column(name = "TOTAL_PAYMENT")
    private BigDecimal totalPayment;

    @Basic
    @Column(name = "DELIVERY_ADDRESS")
    private String deliveryAddress;

    @Basic
    @Column(name = "DELIVERY_PHONE_NUMBER")
    private String deliveryPhoneNumber;

    @Basic
    @Column(name = "STATUS")
    protected Integer status;
}
