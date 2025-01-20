package com.demo.Xparties.Tinder.Model.Enums;

public enum PersonGender {
    F,
    M;

    // When a function inside an enum or a class it is NON-STATIC means that operates on an instance ( this ), but if it is STATIC means that it is NOT operate on an instance, but on other parameters :)
    public PersonGender getOppositeGender() {
        return this == F ? M : F;
    }
}
