/*
 * Licensed to DuraSpace under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.
 *
 * DuraSpace licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fcrepo.kernel.impl.operations;

import static org.fcrepo.kernel.api.operations.ResourceOperationType.UPDATE;

import java.io.InputStream;
import java.net.URI;

import org.fcrepo.kernel.api.identifiers.FedoraId;
import org.fcrepo.kernel.api.operations.ResourceOperationType;

/**
 * Operation for updating a non-rdf source
 *
 * @author bbpennel
 */
public class UpdateNonRdfSourceOperation extends AbstractNonRdfSourceOperation {

    /**
     * Constructor for internal binaries.
     *
     * @param rescId the internal identifier.
     * @param content the stream of the content.
     */
    protected UpdateNonRdfSourceOperation(final FedoraId rescId, final InputStream content) {
        super(rescId, content);
    }

    /**
     * Constructor for external content.
     *
     * @param rescId the internal identifier.
     * @param externalContentURI the URI of the external content.
     * @param externalHandling the type of external content handling (REDIRECT, PROXY)
     */
    protected UpdateNonRdfSourceOperation(final FedoraId rescId, final URI externalContentURI,
            final String externalHandling) {
        super(rescId, externalContentURI, externalHandling);
    }

    @Override
    public ResourceOperationType getType() {
        return UPDATE;
    }
}
