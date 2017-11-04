package com.n26.statistics.util;

import java.lang.annotation.*;

/**
 * Annotation used to Log execution time.
 * @author Ajay
 *
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogExecutionTime {
 
}
