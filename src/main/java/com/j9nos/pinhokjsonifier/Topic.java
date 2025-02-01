package com.j9nos.pinhokjsonifier;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

record Topic(String name, @JsonIgnore String url, List<WordPair> words) {

    static final String XREF =
            "//table/tbody/tr[td[1][contains(@class, 'kbfrom')] and td[2][contains(@class, 'kbto')]]";

    static Topic newFrom(final String name, final String url) {
        return new Topic(name, url, new ArrayList<>());
    }

}
