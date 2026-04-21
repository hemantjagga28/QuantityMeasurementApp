package com.quantity.measurement.model;

import com.quantity.measurement.enums.LengthUnit;

public class QuantityLength {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit should not be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Invalid value");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {

        if (other == null || targetUnit == null)
            throw new IllegalArgumentException("Invalid input");

        double sumInFeet =
                this.unit.convertToBaseUnit(this.value)
                        + other.unit.convertToBaseUnit(other.value);

        double result =
                targetUnit.convertFromBaseUnit(sumInFeet);

        return new QuantityLength(result, targetUnit);
    }

    public QuantityLength add(QuantityLength other) {
        return add(other, this.unit);
    }

    public QuantityLength toConvert(LengthUnit targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit should not be null");

        double baseValue =
                unit.convertToBaseUnit(this.value);

        double convertedValue =
                targetUnit.convertFromBaseUnit(baseValue);

        return new QuantityLength(convertedValue, targetUnit);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass())
            return false;

        QuantityLength other = (QuantityLength) obj;

        double thisFeet =
                this.unit.convertToBaseUnit(this.value);

        double otherFeet =
                other.unit.convertToBaseUnit(other.value);

        return Math.abs(thisFeet - otherFeet) < EPSILON;
    }

    @Override
    public int hashCode() {

        double normalized =
                Math.round(unit.convertToBaseUnit(value) / EPSILON) * EPSILON;

        return Double.hashCode(normalized);
    }
}