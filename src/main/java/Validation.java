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
}
