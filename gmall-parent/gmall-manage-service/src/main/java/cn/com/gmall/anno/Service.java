package cn.com.gmall.anno;

import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@org.apache.dubbo.config.annotation.Service
@Transactional
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
}
