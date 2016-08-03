package org.fcrepo.kernel.modeshape.identifiers;

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
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.fcrepo.kernel.api.exception.RepositoryRuntimeException;
import org.fcrepo.kernel.api.functions.CompositeConverter;
import org.fcrepo.kernel.api.functions.Converter;

/**
 * 
 * @author barmintor
 *
 */
public class PathToNodeConverter extends IdentifierConverter<String, Node> {

    private final Session session;
    /**
     * 
     * @param session
     */
    public PathToNodeConverter(final Session session) {
        this.session = session;
    }
    @Override
    public <C> Converter<String, C> andThen(final Converter<Node, C> after) {
        return new CompositeConverter<>(this, after);
    }

    @Override
    public <C> Converter<C, Node> compose(final Converter<C, String> before) {
        return new CompositeConverter<>(before, this);
    }

    @Override
    public String toDomain(final Node rangeValue) {
        try {
            return rangeValue.getPath();
        } catch (RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }

    @Override
    public Node apply(final String absPath) {
        try {
            return session.getNode(absPath);
        } catch (RepositoryException e) {
            throw new RepositoryRuntimeException(e);
        }
    }

    @Override
    public String asString(final String resource) {
        return resource;
    }

}