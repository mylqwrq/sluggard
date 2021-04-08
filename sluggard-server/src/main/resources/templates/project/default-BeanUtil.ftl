package ${project.basePackage}.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import net.sf.cglib.beans.BeanCopier;

public class BeanUtil {

    private BeanUtil() {
    }

    /**
     * 拷贝源对象相同名称和类型的字段到目标对象中
     *
     * @param source 源对象
     * @param clazz 目标对象的运行时类
     * @param <T> 目标对象类型
     * @param <M> 源对象类型
     * @return 目标对象
     */
    public static <T, M> T copy(M source, Class<T> clazz) {
        if (Objects.isNull(source) || Objects.isNull(clazz)) {
            return null;
        }
        BeanCopier copier = BeanCopier.create(source.getClass(), clazz, false);
        return copy(source, clazz, copier);
    }


    /**
     * 拷贝源对象列表相同名称和类型的字段到目标对象列表中
     *
     * @param sources 源对象列表
     * @param clazz 目标对象列表的运行时类
     * @param <T> 目标对象类型
     * @param <M> 源对象类型
     * @return 目标对象列表
     */
    public static <T, M> List<T> copyBatch(Collection<M> sources, Class<T> clazz) {
        if (Objects.isNull(sources) || Objects.isNull(clazz)) {
            return null;
        }
        if (sources.isEmpty()) {
            return new ArrayList<>();
        }
        BeanCopier copier = BeanCopier.create(sources.stream().findFirst().get().getClass(), clazz, false);
        return sources.stream().map(source -> copy(source, clazz, copier)).collect(Collectors.toList());
    }

    private static <T, M> T copy(M source, Class<T> clazz, BeanCopier copier) {
        if (Objects.isNull(source) || Objects.isNull(clazz)) {
            return null;
        }
        if (Objects.isNull(copier)){
            copier = BeanCopier.create(source.getClass(), clazz, false);
        }
        T target;
        try {
            target = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        copier.copy(source, target, null);
        return target;
    }
}
