package ${project.basePackage}.core.base.enums;

public enum BaseErrorEnum implements BaseEnum {

    SYSTEM_BUSY(99979, "操作太频繁，请稍后再试"), //
    SYSTEM_DUPLICATE_KEY(99989, "数据重复"), //
    SYSTEM_MAINTENANCE(99999, "系统维护中"), //
    ;

    private int code;
    private String name;

    BaseErrorEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }
}
