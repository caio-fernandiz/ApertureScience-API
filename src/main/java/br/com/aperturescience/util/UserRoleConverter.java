package br.com.aperturescience.util;

import br.com.aperturescience.infra.security.UserRole;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRole, String> {

    @Override
    public String convertToDatabaseColumn(UserRole userRole) {
        if (userRole == null) return null;
        return userRole.getCargo();
    }

    @Override
    public UserRole convertToEntityAttribute(String cargo) {
        if (cargo == null) return null;
        for (UserRole role : UserRole.values()) {
            if (role.getCargo().equals(cargo)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Cargo desconhecido: " + cargo);
    }
}

