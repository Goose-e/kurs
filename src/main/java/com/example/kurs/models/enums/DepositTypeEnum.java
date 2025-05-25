package com.example.kurs.models.enums;


public enum DepositTypeEnum {
    DEMAND(1L, "DEMAND", "До востребования", 0.5, 0, true, true),
    FIXED_TERM(2L, "FIXED_TERM", "Срочный вклад", 6.0, 12, false, false),
    SAVINGS(3L, "SAVINGS", "Сберегательный вклад", 4.0, 6, true, true);

    public String getDescription() {
        return description;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getTermMonths() {
        return termMonths;
    }

    public boolean isCanWithdraw() {
        return canWithdraw;
    }

    public boolean isCanAddFunds() {
        return canAddFunds;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private final String description;
    private final double interestRate;
    private final int termMonths;
    private final boolean canWithdraw;
    private final boolean canAddFunds;
    private final Long id;
    private final String name;

    DepositTypeEnum(Long id, String name, String description, double interestRate, int termMonths, boolean canWithdraw, boolean canAddFunds) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.interestRate = interestRate;
        this.termMonths = termMonths;
        this.canWithdraw = canWithdraw;
        this.canAddFunds = canAddFunds;
    }

    public static DepositTypeEnum fromString(String desc) {
        for (DepositTypeEnum depEnum : DepositTypeEnum.values()) {
            if (depEnum.getDescription().equalsIgnoreCase(desc)) {
                return depEnum;
            }
        }
        throw new IllegalArgumentException("Unknown dep type: " + desc);
    }

    public static DepositTypeEnum fromId(int id) {
        for (DepositTypeEnum depId : DepositTypeEnum.values()) {
            if (depId.getId() == id) {
                return depId;
            }
        }
        throw new IllegalArgumentException("Unknown sentiment id: " + id);
    }

    public static String toString(DepositTypeEnum depositTypeEnum) {
        return depositTypeEnum != null ? depositTypeEnum.getName() : null;
    }

}
