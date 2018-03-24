package com.company.webshop.common.aspects.validation;

public class ValidationMessage {

    public static final String FIELD_LENGTH_EXCEEDED = "Field length exceeded";

    public static final String FIRSTNAME_CANNOT_BE_NULL_OR_BLANK = "Firstname cannot be null or blank";
    public static final String LASTNAME_CANNOT_BE_NULL_OR_BLANK = "Lastname cannot be null or blank";
    public static final String EMAIL_ADDRESS_CANNOT_BE_NULL_OR_BLANK = "Email address cannot be null or blank";
    public static final String EMAIL_ADDRESS_HAS_AN_INCORRECT_FORMAT = "Email address has an incorrect format";
    public static final String PASSWORD_CANNOT_BE_NULL = "Password cannot be null";
    public static final String PASSWORD_MUST_CONTAIN_AT_LEAST_8_CHARACTERS = "Password must contain at least 8 characters";
    public static final String ADDRESS_CANNOT_BE_NULL_OR_BLANK = "Address cannot be null or blank";

    public static final String ITEM_NAME_CANNOT_BE_NULL_OR_BLANK = "Item name cannot be null or blank";
    public static final String INVALID_PRICE = "Price cannot be null, zero, negative or larger than allowed";

}
