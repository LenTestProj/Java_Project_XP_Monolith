package com.example.spring_xp_monolith.models;

import java.util.ArrayList;
import java.util.List;

import com.example.spring_xp_monolith.models.embedded.outlets.Coordinates;
import com.example.spring_xp_monolith.models.embedded.outlets.PinelabCredentials;
import com.example.spring_xp_monolith.models.embedded.outlets.Platform;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "outlets")
public class Outlets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String address;

    @Column(nullable = false)
    private String pincode;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private Status status = Status.INACTIVE;

    //coordinates
    @Embedded
    private Coordinates coordinates;

    //pine lab credentials object
    @Embedded
    private PinelabCredentials pinelabCredentials;

    @Column(nullable = false)
    private PaymentType paymentType;

    @AttributeOverrides({
        @AttributeOverride(
            name="longitude",
            column = @Column(name = "location_longitude")
        ),
        @AttributeOverride(
            name="latitude",
            column = @Column(name="location_latitude")
        )
    })
    private Coordinates location;

    private String outletImage;
    
    private String openingTime;

    private String closingTime;

    private Boolean isOutletClosed = false;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderType orderAcceptType;

    @Column(nullable = false)
    private Integer outlet_no;

    @Enumerated(EnumType.STRING)
    private OutletSequence outlet_sequence;

    private List<Platform> platforms = new ArrayList<>(); 

    private Boolean isEventOutlet = false;

    private String gstin;

    private String fssai;

    @Enumerated(EnumType.STRING)
    private OutletType type;

    private String minimumDeliveryRadius;

    private String franchiseName;

    private String franchisePhone;

    private String franchiseEmail;

    private Boolean isDelete = false;

    //enum fields
    public enum Status {
        ACTIVE,
        INACTIVE,
        COMING_SOON
    }

    public enum PaymentType {
        PAYMENT_GATEWAY,
        CARD_SWIPE
    }

    public enum OrderType {
        PICKUP,
        DELIVERY,
        ALL
    }

    public enum OutletType {
        COMPANY_OWNED,
        FRANCHISE,
        EVENT
    }

    public enum OutletSequence {
        A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z
    }

}
