public class Validation {

    public boolean isNameValid(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }

        if (name.length() < 2 || name.length() > 20) {
            return false;
        }

        for (char ch : name.toCharArray()) {
            if (!Character.isLetter(ch)) {
                return false;
            }
        }

        return true;
    }

    public boolean isEmailValid(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        int atIndex = email.indexOf('@');
        if (atIndex <= 0) {
            return false;
        }

        if (atIndex == email.length() - 1) {
            return false;
        }

        String domainPart = email.substring(atIndex + 1);
        int dotIndex = domainPart.lastIndexOf('.');

        if (dotIndex <= 0 || dotIndex == domainPart.length() - 1) {
            return false;
        }

        String extension = domainPart.substring(dotIndex + 1);
        return extension.length() >= 2 && extension.chars().allMatch(Character::isLetter);
    }
    public boolean isPasswordValid(String password) {

        if(password == null || password.isEmpty()) return false;

        if(password.length() < 6) return false;

        if(!password.matches(".*[A-Z].*")) return false;

        if(!password.matches(".*[a-z].*")) return false;

        if(!password.matches(".*\\d.*")) return false;

        if(!password.matches(".*[!@#$%^&*].*")) return false;

        return true;
    }
    public boolean passwordsMatch(String p1, String p2){
        return p1 != null && p1.equals(p2);
    }
    public boolean isDOBValid(String dob){
        return dob != null && dob.matches("\\d{2}/\\d{2}/\\d{4}");
    }
}
