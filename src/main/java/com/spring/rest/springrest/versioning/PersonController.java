package com.spring.rest.springrest.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    //URI versioning
    @GetMapping("/person/V1")
    PersonV1 getPersonV1() {
        return new PersonV1("Amarjeet Malik");
    }

    @GetMapping("/person/V2")
    PersonV2 getPersonV2() {
        return new PersonV2(new Name("Amarjeet", "Malik"));
    }

    //parameter Versioning
    @GetMapping(value = "/person/param", params = "version=1")
    PersonV1 getPersonParam1() {
        return new PersonV1("Amarjeet Malik");
    }

    @GetMapping(value = "/person/param", params = "version=2")
    PersonV2 getPersonParam2() {
        return new PersonV2(new Name("Amarjeet", "Malik"));
    }

    //Header Versioning
    @GetMapping(value = "/person/header", headers = "API-VERSION=1")
    PersonV1 getPersonHeader1() {
        return new PersonV1("Dolly Singh");
    }

    @GetMapping(value = "/person/header",headers = "API-VERSION=2")
    PersonV2 getPersonHeader2() {
        return new PersonV2(new Name("Dolly","Singh"));
    }

    //Mime Type/Content Negotiation/Accept Header Versioning
    //Accept application/app-v1+json header required with request
    @GetMapping(value = "/person/produces",produces = "application/app-v1+json")
    PersonV1 getPersonProducer1() {
        return new PersonV1("Peter Parker");
    }

    @GetMapping(value = "/person/produces",produces = "application/app-v2+json")
    PersonV2 getPersonProducer2() {
        return new PersonV2(new Name("Peter","Parker"));
    }
}
