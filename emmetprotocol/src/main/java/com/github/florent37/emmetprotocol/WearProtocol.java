package com.github.florent37.emmetprotocol;

import java.util.List;

/**
 * Created by florentchampigny on 22/07/15.
 */
public interface WearProtocol {

    void sendRepos(String name, List<AndroidVersion> repos);

}
