/*
 * Copyright (c) 2005, Bobo team
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in
 *       the documentation and/or other materials provided with the
 *       distribution.
 *     * Neither the name of the Bobo project nor the names of its
 *       contributors may be used to endorse or promote products derived
 *       from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 * THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


package org.eu.bobo.rmi.server;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * DOCUMENT ME!
 *
 * @author alex
 * @version $Revision: 1.1 $, $Date: 2005/02/14 16:35:30 $
 */
public final class RmiServer {
    //~ Champs d'instance ------------------------------------------------------

    private AbstractApplicationContext applicationContext;

    //~ Méthodes ---------------------------------------------------------------

    public void start() {
        try {
            doStart();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void stop() {
        try {
            doStop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void doStart() throws Exception {
        final String[] contextPath = {
                "/application-context.xml",
                "/tx-model-hibernate-application-context.xml",
                "/model-hibernate-application-context.xml",
                "/rmi-server-application-context.xml",
            };
        applicationContext = new ClassPathXmlApplicationContext(contextPath);
    }


    private void doStop() throws Exception {
        if (applicationContext == null) {
            return;
        }

        applicationContext.close();
        applicationContext = null;
    }
}
