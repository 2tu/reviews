package com.tu.review.di.scope;

import java.lang.annotation.Retention;
import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author tu enum@foxmail.com.
 */

@Scope @Retention(RUNTIME) public @interface ReviewScope {
}
