/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.agoncal.fascicle.quarkus.load;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.client.Entity;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import static org.agoncal.fascicle.quarkus.load.Endpoint.endpoint;
import static org.agoncal.fascicle.quarkus.load.Endpoint.endpointWithEntity;
import static org.agoncal.fascicle.quarkus.load.Endpoint.endpointWithTemplates;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static javax.ws.rs.client.Entity.json;

public class ScenarioBook extends ScenarioInvoker {

  private static final int NB_BOOKS = 100;

  private static final String targetUrl = "http://localhost:8082";

  private static final String contextRoot = "/api/books";

  @Override
  protected String getTargetUrl() {
    return targetUrl;
  }

  @Override
  // tag::adocSnippet[]
  protected List<Endpoint> getEndpoints() {
    return Stream.of(
      endpoint(contextRoot, "GET"),
      endpoint(contextRoot + "/ping", "GET"),
      endpoint(contextRoot + "/random", "GET"),
      endpointWithTemplates(contextRoot + "/{id}", "GET", this::idParam),
      endpointWithTemplates(contextRoot + "/{id}", "DELETE", this::idParam),
      endpointWithEntity(contextRoot, "POST", this::createBook)
    )
      .collect(collectingAndThen(toList(), Collections::unmodifiableList));
  }
  // end::adocSnippet[]

  private Entity createBook() {
    JsonObject json;
    if (Math.random() * 100 < 95) {
      // Valid book
      json = Json.createObjectBuilder()
        .add("title", faker.book().title())
        .add("author", faker.book().author())
        .add("yearOfPublication", faker.number().numberBetween(1950, 2020))
        .add("nbOfPages", faker.number().numberBetween(50, 800))
        .add("rank", faker.number().numberBetween(1, 10))
        .add("price", faker.number().numberBetween(1, 120))
        .add("smallImageUrl", faker.internet().url())
        .add("mediumImageUrl", faker.internet().url())
        .add("description", faker.lorem().characters())
        .build();
    } else {
      // Invalid book
      json = Json.createObjectBuilder()
        .add("author", faker.book().author())
        .add("yearOfPublication", faker.number().numberBetween(1950, 2020))
        .add("nbOfPages", faker.number().numberBetween(50, 800))
        .add("rank", faker.number().numberBetween(100, 200))
        .add("price", faker.number().numberBetween(1, 120))
        .add("smallImageUrl", faker.internet().url())
        .add("mediumImageUrl", faker.internet().url())
        .add("description", faker.lorem().characters())
        .build();
    }
    return json(json.toString());
  }

  private Map<String, Object> idParam() {
    final HashMap<String, Object> templates = new HashMap<>();
    templates.put("id", ThreadLocalRandom.current().nextInt(1000, 1000 + NB_BOOKS));
    return templates;
  }
}
