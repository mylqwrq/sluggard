package ${project.basePackage}.core.base.vo;

import java.io.Serializable;

public class ComboboxVO implements Serializable {

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ComboboxVO create(int code, String name) {
        return create(String.valueOf(code), name);
    }

    public static ComboboxVO create(long code, String name) {
        return create(String.valueOf(code), name);
    }

    public static ComboboxVO create(String code, String name) {
        ComboboxVO comboboxVO = new ComboboxVO();
        comboboxVO.setCode(code);
        comboboxVO.setName(name);
        return comboboxVO;
    }
}
