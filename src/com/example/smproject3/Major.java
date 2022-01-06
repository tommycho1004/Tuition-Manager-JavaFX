package com.example.smproject3;

/**
 * This enum class defines the majors that a student can have.
 *
 * @author Tommy Cho
 */
public enum Major {
    CS, IT, BA, EE, ME, Unknown;

    /**
     * A method that converts a major object into a string
     *
     * @return major in string form
     */
    public String majorString() {
        if (this == CS) {
            return "CS";
        } else if (this == IT) {
            return "IT";
        } else if (this == BA) {
            return "BA";
        } else if (this == EE) {
            return "EE";
        } else if (this == ME) {
            return "ME";
        } else {
            return "--"; //error case
        }
    }
}