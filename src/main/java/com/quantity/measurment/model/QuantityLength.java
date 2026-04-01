package com.quantity.measurment.model;

import com.quantity.measurment.enums.LengthUnit;

public class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit){

        if (unit==null) throw new IllegalArgumentException("Unit must not be null");
        this.value = value;
        this.unit = unit;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        QuantityLength that = (QuantityLength) obj;

        double thisInFeet = this.unit.toFeet(this.value);
        double otherInFeet = that.unit.toFeet(that.value);

        return Double.compare(thisInFeet, otherInFeet) == 0;

    }
}
