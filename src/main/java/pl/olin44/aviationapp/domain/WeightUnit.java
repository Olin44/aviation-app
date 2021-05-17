package pl.olin44.aviationapp.domain;

public enum WeightUnit {
    kg("kg"),
    lb("lb");

    private final String unit;

    WeightUnit(final String unit) {
        this.unit = unit;
    }

    public String unit() {
        return unit;
    }
}

