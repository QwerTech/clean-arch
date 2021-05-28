package org.example.usecases.mapping;

import java.time.OffsetDateTime;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unexpectedValueMappingException = IllegalMappingException.class,
    unmappedTargetPolicy = ReportingPolicy.ERROR,
    implementationPackage = "<PACKAGE_NAME>.impl",
    imports = OffsetDateTime.class
)
public interface CentralConfig {

}
