/*
 * Copyright 2005-2008 Les Hazlewood
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jsecurity.authz.aop;

import org.jsecurity.aop.MethodInterceptorSupport;
import org.jsecurity.aop.MethodInvocation;
import org.jsecurity.authz.AuthorizationException;

/**
 * @since 0.9
 * @author Les Hazlewood
 */
public abstract class AuthorizingMethodInterceptor extends MethodInterceptorSupport {

    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        assertAuthorized( methodInvocation );
        return methodInvocation.proceed();
    }

    protected abstract void assertAuthorized( MethodInvocation methodInvocation ) throws AuthorizationException;

}
