package org.example.usecases.mapping;

import java.time.OffsetDateTime;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;

@MapperConfig(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unexpectedValueMappingException = IllegalMappingException.class,
    implementationPackage = "<PACKAGE_NAME>.impl",
    imports = OffsetDateTime.class
)
public interface CentralConfig {

}
