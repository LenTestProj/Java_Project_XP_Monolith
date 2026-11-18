package com.example.spring_xp_monolith.dto.outlets;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.spring_xp_monolith.Enums.Custom.CustomEnums.OrderType;
import com.example.spring_xp_monolith.Enums.Custom.CustomEnums.Status;
import com.example.spring_xp_monolith.Enums.OutletEnum.OutletSequence;
import com.example.spring_xp_monolith.Enums.OutletEnum.OutletType;
import com.example.spring_xp_monolith.Enums.OutletEnum.PaymentType;
import com.example.spring_xp_monolith.models.Outlets;
import com.example.spring_xp_monolith.models.embedded.outlets.Coordinates;
import com.example.spring_xp_monolith.models.embedded.outlets.PinelabCredentials;
import com.example.spring_xp_monolith.models.embedded.outlets.Platform;

import lombok.Data;

@Data
public class OutletResponseDto {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String pincode;
    private String city;
    private String state;
    private String country;
    private Status status;
    private Coordinates coordinates;
    private Coordinates location;
    private PinelabCredentials pineLabCredentials;
    private PaymentType paymentType;
    private String outletImage;
    private String openingTime;
    private String closingTime;
    private Boolean isOutletClosed;
    private OrderType orderAcceptType;
    private Integer outletNo;
    private OutletSequence outletSequence;
    private List<Platform> platforms;
    private Long menuGroupId;
    private Boolean isEventOutlet;
    private String gstin;
    private String fssai;
    private OutletType type;
    private Long foodUnitId;
    private Long beverageUnitId;
    private Long centralUnitId;
    private String minimumDeliveryRadius;
    private String franchiseName;
    private String franchisePhone;
    private String franchiseEmail;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static OutletResponseDto from(Outlets o) {
        OutletResponseDto dto = new OutletResponseDto();
        dto.setId(o.getId());
        dto.setName(o.getName());
        dto.setPhone(o.getPhone());
        dto.setEmail(o.getEmail());
        dto.setAddress(o.getAddress());
        dto.setPincode(o.getPincode());
        dto.setCity(o.getCity());
        dto.setState(o.getState());
        dto.setCountry(o.getCountry());
        dto.setStatus(o.getStatus());
        dto.setCoordinates(o.getCoordinates());
        dto.setLocation(o.getLocation());
        dto.setPineLabCredentials(o.getPinelabCredentials());
        dto.setPaymentType(o.getPaymentType());
        dto.setOutletImage(o.getOutletImage());
        dto.setOpeningTime(o.getOpeningTime());
        dto.setClosingTime(o.getClosingTime());
        dto.setIsOutletClosed(o.getIsOutletClosed());
        dto.setOrderAcceptType(o.getOrderAcceptType());
        dto.setOutletNo(o.getOutlet_no());
        dto.setOutletSequence(o.getOutlet_sequence());
        dto.setPlatforms(o.getPlatforms() == null ? null : new ArrayList<>(o.getPlatforms()));
        dto.setIsEventOutlet(o.getIsEventOutlet());
        dto.setGstin(o.getGstin());
        dto.setFssai(o.getFssai());
        dto.setType(o.getType());
        dto.setMinimumDeliveryRadius(o.getMinimumDeliveryRadius());
        dto.setFranchiseName(o.getFranchiseName());
        dto.setFranchisePhone(o.getFranchisePhone());
        dto.setFranchiseEmail(o.getFranchiseEmail());
        dto.setCreatedAt(o.getCreatedAt());
        dto.setUpdatedAt(o.getUpdatedAt());

        // getId() on a lazy proxy does not trigger a load
        if (o.getMenuGroup() != null) dto.setMenuGroupId(o.getMenuGroup().getId());
        if (o.getFoodUnit() != null) dto.setFoodUnitId(o.getFoodUnit().getId());
        if (o.getBeverageUnit() != null) dto.setBeverageUnitId(o.getBeverageUnit().getId());
        if (o.getCentralUnit() != null) dto.setCentralUnitId(o.getCentralUnit().getId());

        return dto;
    }
}
