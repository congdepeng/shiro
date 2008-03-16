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
package org.jsecurity.web.interceptor.authz;

import org.jsecurity.subject.Subject;
import static org.jsecurity.util.StringUtils.split;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author Les Hazlewood
 * @since 0.9
 */
public class PermissionsAuthorizationWebInterceptor extends AuthorizationWebInterceptor {

    public void processPathConfig(String path, String config) {
        if ( config != null ) {
            String[] values = split(config);
            if ( values != null ) {
                this.appliedPaths.put(path, values);
            }
        }
    }

    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        Subject subject = getSubject(request, response);
        String[] perms = (String[])mappedValue;

        if ( perms != null && perms.length > 0 ) {
            if ( perms.length == 1 ) {
                if ( !subject.isPermitted(perms[0]) ) {
                    issueRedirect(request,response);
                }
            } else {
                if ( !subject.isPermittedAll(perms) ) {
                    issueRedirect(request,response);
                }
            }
        }

        return true;
    }
}
