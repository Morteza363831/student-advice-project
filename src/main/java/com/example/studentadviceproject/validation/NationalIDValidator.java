package com.example.studentadviceproject.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NationalIDValidator implements ConstraintValidator<ValidNationalID, String> {
    @Override
    public boolean isValid(String nationalID, ConstraintValidatorContext context) {
        return isValidNationalID(nationalID);
    }

    public static boolean isValidNationalID(String nationalID) {
        // The national ID must be exactly 10 digits
        if (nationalID.length() != 10) {
            return false;
        }

        // Convert the national ID to an array of digits
        int[] digits = new int[10];
        for (int i = 0; i < 10; i++) {
            digits[i] = Integer.parseInt(nationalID.substring(i, i + 1));
        }

        // Calculate the sum of the products of the digits and the numbers 10 to 2
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += (10 - i) * digits[i];
        }

        // Calculate the control digit
        int controlDigit = sum % 11;

        // If the control digit is less than 2, it must be equal to the last digit of the national ID
        if (controlDigit < 2 && controlDigit == digits[9]) {
            return true;
        }
        // Otherwise, the difference between the control digit and the last digit of the national ID must be 11
        else if (controlDigit >= 2 && (11 - controlDigit) == digits[9]) {
            return true;
        }
        else {
            return false;
        }
    }

}

