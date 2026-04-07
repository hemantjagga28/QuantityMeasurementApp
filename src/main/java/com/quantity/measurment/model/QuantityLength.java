package com.quantity.measurment.model;

import com.quantity.measurment.enums.LengthUnit;

public class QuantityLength {

    private static final double EPSILON = 1e-6;

    private final double value;
    private final LengthUnit unit;

    // Constructor
    public QuantityLength(double value, LengthUnit unit) {

        if (unit == null) {
            throw new IllegalArgumentException("Unit should not be null");
        }

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value!");
        }

        this.value = value;
        this.unit = unit;
    }

    // Getter for value
    public double getValue() {
        return value;
    }

    // Getter for unit
    public LengthUnit getUnit() {
        return unit;
    }

    // Instance method → returns converted numeric value
    public double toConvert(LengthUnit targetUnit) {
        return convert(this.value, targetUnit, this.unit);
    }

    // Instance method → returns converted QuantityLength object
    public QuantityLength convert(LengthUnit targetUnit) {

        double convertedValue = convert(this.value, targetUnit, this.unit);

        return new QuantityLength(convertedValue, targetUnit);
    }

    // Static conversion logic
    public static double convert(double value,
                                 LengthUnit targetUnit,
                                 LengthUnit sourceUnit) {

        if (sourceUnit == null || targetUnit == null) {
            throw new IllegalArgumentException("Unit should not be null");
        }

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid numeric value!");
        }

        double valueInFeet = sourceUnit.toFeet(value);

        return targetUnit.fromFeet(valueInFeet);
    }

    // Equality check with tolerance comparison
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        QuantityLength other = (QuantityLength) obj;

        double thisInFeet = this.unit.toFeet(this.value);
        double otherInFeet = other.unit.toFeet(other.value);

        return Math.abs(thisInFeet - otherInFeet) < EPSILON;
    }
}