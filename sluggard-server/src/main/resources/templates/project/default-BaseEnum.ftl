package ${project.basePackage}.core.base.enums;

import java.io.Serializable;

public interface BaseEnum extends Serializable {

    int getCode();

    String getName();
}
