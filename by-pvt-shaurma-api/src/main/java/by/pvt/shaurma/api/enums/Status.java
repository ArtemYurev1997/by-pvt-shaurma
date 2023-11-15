package by.pvt.shaurma.api.enums;

public enum Status {
    UNFORMED("Не оформлен"),
    WAITING("Ожидает подтверждения"),
    ON_THE_WAY("В пути"),
    DONE("Оформлен"),
    PAY("Оплачен");

    private String name;

    Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
