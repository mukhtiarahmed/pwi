/*
 * Copyright (C) 2017 Van Tibolli, All Rights Reserved.
 */
package com.vantibolli.inventory.annotations;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Target;

/**
 * The annotation marking the need of logging.
 *
 * @author mukhtiar.ahmed
 * @version 1.0
 */
@Target(value = {METHOD})
public @interface LogMethod {
}
