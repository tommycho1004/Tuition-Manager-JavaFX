package com.example.smproject3;

/**
 * The profile class that is used to identify students.
 *
 * @author Tommy Cho
 */
public class Profile implements Comparable<Profile> {
    private String name;
    private Major major; //5 majors and 2-character each: CS, IT, BA, EE, ME

    /**
     * A parameterized constructor for the profile object
     *
     * @param name  Name of the student in string form
     * @param major Major of the student in string form
     */
    public Profile(String name, String major) {
        this.name = name;
        this.setMajor(major);
    }

    /**
     * A setter method to set the major of a student in their profile
     *
     * @param major major or a student in String form.
     */
    public void setMajor(String major) {
        switch (major) {
            case "CS", "Cs", "cS", "cs" -> this.major = Major.CS;
            case "IT", "It", "iT", "it" -> this.major = Major.IT;
            case "BA", "Ba", "bA", "ba" -> this.major = Major.BA;
            case "EE", "Ee", "eE", "ee" -> this.major = Major.EE;
            case "ME", "Me", "mE", "me" -> this.major = Major.ME;
            default -> this.major = Major.Unknown;
        }
    }

    /**
     * Converts the information of the student to a string format
     *
     * @return the student's profile in the form of a string
     */
    @Override
    public String toString() {
        return this.name + ":" + this.major.majorString();
    }

    /**
     * A method that compares one profile to another by alphabetical order
     *
     * @param profile Profile of the student being compared with
     * @return negative int if name 2 goes after name 1, positive int if name 1 goes after name 2, 0 if they're the same
     */
    @Override
    public int compareTo(Profile profile) {
        String Name1 = this.name.replace(" ", "").toLowerCase();
        String Name2 = profile.name.replace(" ", "").toLowerCase();
        int compareLength = Math.min(Name1.length(), Name2.length());
        for (int i = 0; i < compareLength; i++) {
            int str1_ch = Name1.charAt(i);
            int str2_ch = Name2.charAt(i);
            if (str1_ch != str2_ch) {
                return str1_ch - str2_ch;
            }
        }
        if (Name1.length() != Name2.length()) {
            return Name1.length() - Name2.length();
        } else {
            return 0;
        }
    }

    /**
     * Compares two students and determines if they are the same
     *
     * @param obj student that is being compared
     * @return true if the two students are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Profile) {
            Profile newProf = (Profile) obj;
            return newProf.name.equals(name) && newProf.major.equals(major);
        }
        return false;
    }
}